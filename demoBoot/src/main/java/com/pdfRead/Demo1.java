package com.pdfRead;

import cn.hutool.core.text.CharSequenceUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hex.table.engine.pdf.common.model.CellItem;
import com.hex.table.engine.pdf.common.model.HexTable;
import com.hex.table.engine.pdf.v1.model.HexRectangle;
import com.hex.table.pdfextract.TableExtract;
import com.model.NlpRegFile;
import com.model.constant.LerisConstants;
import com.model.json.HexCell;
import com.model.json.HexSection;
import com.util.LeaseUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author ming.li
 * @Date 2025/1/6 16:37
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args) {
        String pdfPath="C:\\liming\\租赁物\\矢量问题汇总\\第四页错列\\01141162000138952100\\20201030181538345.pdf";
        getPdfRead(pdfPath,"xx",new ArrayList<>());
    }
    //PDF矢量读取
    public static void getPdfRead(String path, String name, List<NlpRegFile> regFiles){
        try {
            List<HexTable> tables = TableExtract.Extract(path, TableExtract.getLiuShuiConfig(), "", "");
            //读取出了表格的需要超前处理
            if (tables!=null&&tables.size()>0){

                //找出同pdf名字的页码的文件(供坐标等比例缩放)
                if (regFiles.size()>0){
                    Map<Integer, List<Integer>> zdMap=new HashMap<>();
                    Map<Integer,NlpRegFile> jpgMap=new HashMap<>();
                    jpgMap.put(1,new NlpRegFile());
                  /*  for (int i = 0; i < regFiles.size(); i++) {
                        String fmsFilePath = this.fmsFileService.getFmsFilePath(regFiles.get(i).getPicFileId());
                        FileInputStream is = new FileInputStream(fmsFilePath);
                        BufferedImage read = ImageIO.read(is);
                        int height = read.getHeight();
                        int width = read.getWidth();
                        List<Integer> list=new ArrayList<>();
                        list.add(height);
                        list.add(width);
                        zdMap.put(i+1,list);
                        jpgMap.put(i+1,regFiles.get(i));
                    }*/
                    Map<Integer,List<HexTable>> map=new HashMap<>();
                    for (int i = 0; i < tables.size(); i++) {
                        HexTable hexTable = tables.get(i);
                        int pageNumber = hexTable.getPageNumber();
                        if (map.containsKey(pageNumber)){
                            List<HexTable> hexTables = map.get(pageNumber);
                            hexTables.add(hexTable);
                        }else {
                            List<HexTable> hexTables =new ArrayList<>();
                            hexTables.add(hexTable);
                            map.put(pageNumber,hexTables);
                        }
                    }
                    Set<Integer> integers = map.keySet();
                    for (Integer index:integers){
                        NlpRegFile nlpRegFile1 = jpgMap.get(index);
                        if (nlpRegFile1==null){
                            continue;
                        }
                        JSONObject jsonObject = new JSONObject();
                        JSONArray body = new JSONArray();
                        List<HexTable> hexTables = map.get(index);
                        Double h=null;
                        Double w=null;
                        if (zdMap.containsKey(index)){
                            List<Integer> list1 = zdMap.get(index);
                            float pageHeight = hexTables.get(0).getPageHeight();
                            float pageWidth = hexTables.get(0).getPageWidth();
                            h=((double) list1.get(0))/pageHeight;
                            w=((double) list1.get(1))/pageWidth;
                        }
                        for (int i = 0; i < hexTables.size(); i++) {
                            HexTable hexTable = hexTables.get(i);
                            List<CellItem> cellItemList = hexTable.getCellItemList();
                            JSONObject boy = new JSONObject();
                            JSONArray cells = new JSONArray();
                            for (int j = 0; j < cellItemList.size(); j++) {
                                JSONObject cell = new JSONObject();
                                CellItem cellItem = cellItemList.get(j);
                                int colNo = cellItem.getColNo();
                                int rowNo = cellItem.getRowNo();
                                int colSize = cellItem.getColSize();
                                int rowSize = cellItem.getRowSize();
                                cell.put("tl_col",colNo);
                                cell.put("tl_row",rowNo);
                                cell.put("br_col",colNo+colSize);
                                cell.put("br_row",rowNo+rowSize);
                                HexRectangle hexRectangle=(HexRectangle)cellItem.getRectangle();
                                if (h!=null&&w!=null){
                                    JSONObject itemcoord = new JSONObject();
                                    itemcoord.put("x",hexRectangle.x*w);
                                    itemcoord.put("y",hexRectangle.y*h);
                                    itemcoord.put("width",hexRectangle.width*w);
                                    itemcoord.put("height",hexRectangle.height*h);
                                    cell.put("itemcoord",itemcoord);
                                }
                                cell.put("splitCell",false);
                                cell.put("itemstring",hexRectangle.getText().replaceAll("\\r\\n", ""));
                                cells.add(cell);
                            }
                            boy.put("cells",cells);
                            boy.put("type",1);
                            body.add(boy);
                        }
                        jsonObject.put("body",body);
                        jsonObject.put("type",1);
                        String jsonFilePath = path.substring(0, path.lastIndexOf("/") + 1) + LeaseUtil.getUuid() + ".json";
                        FileUtils.writeStringToFile(new File(jsonFilePath), jsonObject.toJSONString(), LerisConstants.CHARSET_UTF8);
                        for (int i = 0; i < body.size(); i++) {
                            JSONObject jsonObject1 = body.getJSONObject(i);
                            HexSection hexSection = JSONObject.parseObject(jsonObject1.toJSONString(), HexSection.class);
                            if (hexSection!=null&& StringUtils.isNotBlank(nlpRegFile1.getTableHead())){
                                getTableHeadByTableOcrResult(hexSection,"xx");
                            }

                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 根据表格识别结果获取表头信息
     *
     * @param hexSection
     * @param tenantId
     * @return 表头(若有) 例："序号,租赁物名称,型号/规格,发票号"
     */
    private static String getTableHeadByTableOcrResult(HexSection hexSection, String tenantId) {
        // 开关
        Boolean isAddTableHead = true;
        if (!isAddTableHead) {
            return null;
        }
        // 判断起始行是否是表头的正则 ".*(?=(序号)|(名称)|(型号)).*"
        String tableHeadRegix = ".*(?=(序号)|(名称)|(型号)).*";
        // 表头信息
        StringBuilder result = new StringBuilder("");
        try {
            ArrayList<HexCell> cells = hexSection.getCells();
            /*
             获取行总数
             */
            List<Integer> rowCount = cells.stream().map(HexCell::getTl_row).distinct().sorted().collect(Collectors.toList());
            int matchRowNo=-1;
            Map<Integer,String> tableHeadChacheMap=new HashMap<>();
            for (Integer rowNo : rowCount) {
                List<HexCell> rowCellList = cells.stream().filter(cell -> rowNo >= cell.getTl_row() && rowNo < cell.getBr_row()).sorted(Comparator.comparing(HexCell::getTl_col)).collect(Collectors.toList());
                result.setLength(0);
                for (int i = 0; i < rowCellList.size(); i++) {
                    HexCell cell=rowCellList.get(i);
                    String itemstring = cell.getItemstring();
                    itemstring= CharSequenceUtil.replace(itemstring,"\n","");
                    itemstring=CharSequenceUtil.replace(itemstring,",","");
                    if (CharSequenceUtil.isNotEmpty(itemstring)&&itemstring.matches(tableHeadRegix)) {
                        matchRowNo=rowNo;
                    }
                    result.append(itemstring);
                    if (i!=rowCellList.size()-1){
                        result.append(",");
                    }
                }
                if (rowNo==matchRowNo){
                    tableHeadChacheMap.put(rowNo,result.toString());
                }else if (matchRowNo>=0){
                    return tableHeadChacheMap.get(matchRowNo);
                }
            }
            return tableHeadChacheMap.get(matchRowNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
