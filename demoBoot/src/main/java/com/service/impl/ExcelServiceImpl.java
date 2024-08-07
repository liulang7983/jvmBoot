package com.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.excel.ExcelDataListener;
import com.service.ExcelService;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author ming.li
 * @date 2023/5/11 19:19
 */
@Service
public class ExcelServiceImpl implements ExcelService {
    @Override
    public String upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        System.out.println(file.getName());
        String substring = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        System.out.println();
        int index = originalFilename.lastIndexOf(".");
        if (index == -1 || !("xlsx".equalsIgnoreCase(substring) || "xls".equalsIgnoreCase(substring))) {
            return "文件格式非execl格式";
        }
        /*
         初始化一个监听器
         */
        ExcelDataListener excelDataListener = new ExcelDataListener();
        /*
         读取文件数据
         */

        EasyExcelFactory.read(file.getInputStream(), excelDataListener).sheet().doRead();
        List<Map<Integer, String>> headList = excelDataListener.getHeadList();
        Map<Integer, String> stringMap = headList.get(0);
        int size1 = stringMap.size();
        Collection<String> values = stringMap.values();
        if (!stringMap.get(0).contains("发票代码")) {
            return "第一列非发票代码";
        }

        List<Map<Integer, String>> dataList = excelDataListener.getDataList();
        for (int i = 0; i < dataList.size(); i++) {
            Map<Integer, String> map = dataList.get(i);
            int size = map.size();
            String s = map.get(4);
            for (int j = 0; j < size; j++) {
                System.out.println(map.get(j));
            }
            System.out.println("获取完一行");

        }
        return "成功";
    }

    @Override
    public void downloadExcel(HttpServletResponse response) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = null;
        sheet = wb.createSheet("发票维度");
        XSSFRow row = sheet.createRow((short) 0);
        XSSFCell cell = row.createCell((short) 0);
        String s = "st\r\n" + "nn";
        System.out.println(s);
        cell.setCellValue(s);
        //这三行代表换行
        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setWrapText(true);
        cell.setCellStyle(cellStyle);
        try {
            // 输出Excel文件
            OutputStream output = response.getOutputStream();
            response.reset();
            // 设置文件头
            response.setHeader("Content-Disposition",
                    "attchement;filename=" + new String(("发票下载" + ".xls").getBytes("gb2312"), "ISO8859-1"));
            response.setContentType("application/msexcel");
            wb.write(output);
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String importExecl(MultipartFile file) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        for (int j = 0; j < workbook.getNumberOfSheets(); j++) {
            XSSFSheet xssfSheet = workbook.getSheetAt(j);
            String sheetName = xssfSheet.getSheetName();
            System.out.println("名称:" + sheetName);
            String substring = sheetName.substring(0, sheetName.lastIndexOf("-"));
            System.out.println(substring);
            System.out.println(sheetName.substring(sheetName.indexOf("-") + 1));
            if (xssfSheet == null) {
                continue;
            }
            for (int k = 0; k <= xssfSheet.getLastRowNum(); k++) {
                List<String> rowDataList = new ArrayList<>();
                XSSFRow aRow = xssfSheet.getRow(k);
                if (aRow == null) {
                    continue;
                }
                System.out.println("行数:" + k);
                System.out.println(xssfSheet.getRow(k).getLastCellNum());
                if (k == 1) {
                    for (int l = 0; l < xssfSheet.getRow(k).getLastCellNum(); l++) {
                        XSSFCell cell = xssfSheet.getRow(k).getCell(l);
                        if (cell != null) {
                            String s = xssfSheet.getRow(k).getCell(l).toString();
                            System.out.println(s);
                        }
                    }
                }
                if (k > 3) {
                    for (int l = 0; l < xssfSheet.getRow(k).getLastCellNum(); l++) {
                        XSSFCell cell = xssfSheet.getRow(k).getCell(l);
                        if (cell != null) {
                            String s = xssfSheet.getRow(k).getCell(l).toString();
                            System.out.println(s);
                        }
                    }

                }

            }
        }

        return null;
    }
}
