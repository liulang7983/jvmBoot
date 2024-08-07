package com.util;

import com.alibaba.fastjson.JSON;
import com.bean.ApiResult;
import com.test.enumUtil.ERetCode;
import com.model.chatMort.ChatMortDetResult;
import com.model.chatMort.Collateral;
import com.model.constant.LerisConstants;
import com.model.json.*;
import com.model.qcc.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class JsonUtil {

    private final static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * QCC结果转标准json
     *
     * @param qccResponseList
     * @return
     */
    public static ApiResult jsonToList(List<QCCResponse> qccResponseList) {
        ArrayList<Map<String, String>> ret = new ArrayList<>();
        HashSet<String> lstValid = new HashSet<>();
        HashSet<String> lstInvalid = new HashSet<>();
        for (QCCResponse data : qccResponseList) {
            if (!"200".equals(data.getStatus())) {
                return dealQCCError(data);
            }

            QCCData qccData = data.getResult();
            if (qccData.getVerifyResult() != 1) {
                continue;
            }
            List<QCCRegister> registers = qccData.getData();
            for (QCCRegister register : registers) {
                if ("有效".equals(register.getStatus())) {
                    continue;
                }
                String key = register.getRegisterNo().trim() + register.getRegisterDate().trim();
                if (!lstInvalid.contains(key)) {
                    lstInvalid.add(key);
                }
            }

            for (QCCRegister register : registers) {
                if (!"有效".equals(register.getStatus())) {
                    continue;
                }

                String key = register.getRegisterNo().trim() + register.getRegisterDate().trim();
                if (lstValid.contains(key)) continue;
                if (lstInvalid.contains(key)) continue;

                HashMap<String, String> map = new HashMap<>();
                map.put("RegisterNo", register.getRegisterNo());
                map.put("RegisterDate", register.getRegisterDate());
                map.put("DebtSecuredAmount", register.getDebtSecuredAmount());
                if (register.getDetail() == null) {
                    continue;
                }
                if (register.getDetail().getGuaranteeList() == null) {
                    continue;
                }
                if (register.getDetail().getGuaranteeList().size() == 0) {
                    continue;
                }
                HexOCRResult ocrResult = new HexOCRResult();
                ocrResult.setType(2);
                HexSection section = new HexSection();
                ArrayList<HexSection> body = new ArrayList<>();
                body.add(section);
                ocrResult.setBody(body);
                section.setType(0);
                ArrayList<HexRow> rows = new ArrayList<>();
                section.setRows(rows);

                for (QCCGuarantee qccGuarantee : register.getDetail().getGuaranteeList()) {
                    HexRow row = new HexRow();
                    ArrayList<Item> items = new ArrayList<>();
                    Item item = new Item();
                    item.setItemstring(qccGuarantee.getName());
                    items.add(item);
                    row.setRow(items);
                    rows.add(row);

                    row = new HexRow();
                    items = new ArrayList<>();
                    item = new Item();
                    item.setItemstring(qccGuarantee.getOwnership());
                    items.add(item);
                    row.setRow(items);
                    rows.add(row);

                    row = new HexRow();
                    items = new ArrayList<>();
                    item = new Item();
                    item.setItemstring(qccGuarantee.getOther());
                    items.add(item);
                    row.setRow(items);
                    rows.add(row);

                    row = new HexRow();
                    items = new ArrayList<>();
                    item = new Item();
                    item.setItemstring(qccGuarantee.getRemark());
                    items.add(item);
                    row.setRow(items);
                    rows.add(row);
                }

                map.put("json", JSON.toJSONString(ocrResult));
                SecuredClaim securedClaim = register.getDetail().getSecuredClaim();
                if (securedClaim != null) {
                    /*
                    获取备注中的抵押合同号
                     */
                    map.put("remark", securedClaim.getRemark());
                }
                ArrayList<QCCPledgee> pledgeeList = register.getDetail().getPledgeeList();
                map.put("registerOrg", pledgeeList == null ? "" : pledgeeList.get(0).getName());

                ret.add(map);
                lstValid.add(key);
            }
        }
        return new ApiResult(ret);
    }
    public static String jsonToDCList(ChatMortDetResult chatMortDetResult) {
        HexOCRResult ocrResult = new HexOCRResult();
        ocrResult.setType(2);
        HexSection section = new HexSection();
        ArrayList<HexSection> body = new ArrayList<>();
        body.add(section);
        ocrResult.setBody(body);
        section.setType(0);
        ArrayList<HexRow> rows = new ArrayList<>();
        section.setRows(rows);
        List<Collateral> collateralList = chatMortDetResult.getCollateralList();
        for (Collateral collateral:collateralList){
            HexRow row = new HexRow();
            ArrayList<Item> items = new ArrayList<>();
            Item item = new Item();
            item.setItemstring(collateral.getGuaName().trim());
            items.add(item);
            row.setRow(items);
            rows.add(row);

            row = new HexRow();
            items = new ArrayList<>();
            item = new Item();
            item.setItemstring(collateral.getOwn());
            items.add(item);
            row.setRow(items);
            rows.add(row);

            row = new HexRow();
            items = new ArrayList<>();
            item = new Item();
            item.setItemstring(collateral.getGuaDes());
            items.add(item);
            row.setRow(items);
            rows.add(row);
        }
        return JSON.toJSONString(ocrResult);
    }


    private static ApiResult dealQCCError(QCCResponse data) {
        if ("201".equals(data.getStatus())) {
            return new ApiResult(ERetCode.FAIL.getCode(), "企查查无登记信息");
        } else if ("207".equals(data.getStatus()) || "222".equals(data.getStatus()) || "102".equals(data.getStatus())
                || "109".equals(data.getStatus()) || "110".equals(data.getStatus()) || "112".equals(data.getStatus())) {
            return new ApiResult(ERetCode.FAIL.getCode(), "企查查" + data.getMessage());
        } else {
            return new ApiResult(ERetCode.FAIL.getCode(), "企查查查询失败{" + data.getMessage() + "}");
        }
    }

    /**
     * 表格识别结果转标准json
     *
     * @param jsonStr
     * @return
     */
    public static HexOCRResult jsonToHexOCRResult(String jsonStr, String imageFilePath) {
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        }
        TableOCRResp tableOCRResp = JSON.parseObject(jsonStr, TableOCRResp.class);
        if (tableOCRResp.getErrorcode() != 0) {
            return null;
        }
        if (tableOCRResp.getTableRes().getTables().size() == 0) {
            return null;
        }
        HexOCRResult hexOCRResult = new HexOCRResult();
        /*
        逆时针旋转90识别，定位出现问题
         */
        Float angle = tableOCRResp.getAngle();
      /*  if (angle != null) {
            hexOCRResult.setAngle(angle);
            try {
                ImageTools.rotateImage(new File(imageFilePath), Integer.parseInt(Float.compare(angle, 0.0f) != 0 ? angle.toString().substring(0, angle.toString().lastIndexOf(".")) : "0"));
            } catch (Exception e) {
                logger.error("rotateImage exception [{}]", e);
            }
        }*/
        hexOCRResult.setType(LerisConstants.CHECK_TYPE_ZDW_FJ);
        for (Table table : tableOCRResp.getTableRes().getTables()) {
            if (table.getType() == 0) {
                dealTextType(table, hexOCRResult);
            } else {
                dealTableType(table, hexOCRResult);
            }
        }
        return hexOCRResult;
    }

    /*
    处理文本
     */
    public static void dealTextType(Table table, HexOCRResult hexOCRResult) {
        HexSection hexSection = new HexSection();
        hexSection.setType(0);

        ArrayList<Item> lstItems = new ArrayList<>();
        for (Cell cell : table.getCells()) {
            lstItems.addAll(cell.getContents());
        }

        if (lstItems.size() > 0) {
            if (lstItems.size() == 1) {
                HexRow newRow = new HexRow();
                newRow.setRow(lstItems);
                hexSection.getRows().add(newRow);
            } else {
                HexRow curRow = LeaseUtil.findRow(lstItems);
                while (curRow != null) {
                    hexSection.getRows().add(curRow);
                    curRow = LeaseUtil.findRow(lstItems);
                }
            }
        }
        hexOCRResult.getBody().add(hexSection);
    }

    /*
    处理表格
     */
    public static void dealTableType(Table table, HexOCRResult hexOCRResult) {
        HexSection hexSection = new HexSection();
        hexSection.setType(1);
        ArrayList<HexCell> hexCells = new ArrayList<>();
        for (Cell cell : table.getCells()) {
            hexCells.add(LeaseUtil.cell2HexCell(cell));
        }
        hexSection.getCells().addAll(hexCells);
        hexOCRResult.getBody().add(hexSection);
    }

    /**
     * 表格识别结果转标准json
     *
     * @param jsonStr
     * @return
     */
    public static HexOCRResult jsonToHexOCRResult(String jsonStr, boolean wordFlag) {
        logger.info("enter jsonToHexOCRResult function ");
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        }
        TableOCRResp data = JSON.parseObject(jsonStr, TableOCRResp.class);
        if (data.getErrorcode() != 0) {
            return null;
        }
        if (CollectionUtils.isEmpty(data.getTableRes().getTables())) {
            return null;
        }

        HexOCRResult hexOCRResult = new HexOCRResult();
        hexOCRResult.setType(1);
        for (Table table : data.getTableRes().getTables()) {

            if (table.getType() == 0 || table.getType() == 2) {     //暂时把无线表格当做文本处理。
                HexSection hexSection = new HexSection();
                hexSection.setType(0);

                ArrayList<Item> lstItems = new ArrayList<>();
                for (Cell cell : table.getCells()) {
                    lstItems.addAll(cell.getContents());
                }

                if (!CollectionUtils.isEmpty(lstItems)) {
                    if (lstItems.size() == 1) {
                        HexRow newRow = new HexRow();
                        newRow.setRow(lstItems);
                        hexSection.getRows().add(newRow);
                    } else {
                        if (wordFlag) {
                            HexRow curRow = LeaseUtil.findRow(lstItems);
                            while (curRow != null) {
                                hexSection.getRows().add(curRow);
                                curRow = LeaseUtil.findRow(lstItems);
                            }
                        } else {
                            HexRow curRow = LeaseUtil.findParage(lstItems);
                            while (curRow != null) {
                                hexSection.getRows().add(curRow);
                                curRow = LeaseUtil.findParage(lstItems);
                            }
                        }
                    }
                }
                hexOCRResult.getBody().add(hexSection);
            } else {
                HexSection hexSection = new HexSection();
                hexSection.setType(1);
                ArrayList<HexCell> hexCells = new ArrayList<>();
                for (Cell cell : table.getCells()) {
                    hexCells.add(LeaseUtil.cell2HexCell(cell));
                }

                hexSection.getTitle().addAll(table.getTitle());
                hexSection.getCells().addAll(hexCells);
                hexOCRResult.getBody().add(hexSection);
            }
        }
        return hexOCRResult;
    }

    /**
     * 读取标准json文件文件并转成对象
     *
     * @param filePath
     * @return
     */
    public static <T> T readJsonFile(String filePath, Class<T> clazz) {
        T jsonResult = null;
        File file = new File(filePath);
        if (!file.exists()) {
            return jsonResult;
        }
        String jsonStr;
        try (FileInputStream inputStream = new FileInputStream(file)) {
            int length = inputStream.available();
            byte bytes[] = new byte[length];
            while (inputStream.read(bytes) > 0) {
                /*
                 ....
                 */
            }
            jsonStr = new String(bytes, StandardCharsets.UTF_8);
            jsonResult = JSON.parseObject(jsonStr, clazz);
        } catch (Exception e) {
            logger.error("Exception:{}", e);
        }
        return jsonResult;
    }

    /**
     * 优图高精度表格识别结果转标准json
     *
     * @param jsonStr
     * @return
     */
    public static HexOCRResult jsonToHexOCRHighResult(String jsonStr, String imageFilePath) {
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        }
        TableOCRHighResp tableOCRResp = JSON.parseObject(jsonStr, TableOCRHighResp.class);
        if (tableOCRResp.getError_code() != 0) {
            return null;
        }
        if (tableOCRResp.getRecognize_list().size()==0) {
            return null;
        }
        HexOCRResult hexOCRResult = new HexOCRResult();
        /*
        逆时针旋转90识别，定位出现问题
         */
        Float angle = tableOCRResp.getRecognize_list().get(0).getAngle();
        if (angle != null) {
            hexOCRResult.setAngle(angle);
            try {
                ImageTools.rotateImage(new File(imageFilePath), Integer.parseInt(Float.compare(angle, 0.0f) != 0 ? angle.toString().substring(0, angle.toString().lastIndexOf(".")) : "0"));
            } catch (Exception e) {
                logger.error("rotateImage exception [{}]", e);
            }
        }
        hexOCRResult.setType(LerisConstants.CHECK_TYPE_ZDW_FJ);

        return hexOCRResult;
    }
}
