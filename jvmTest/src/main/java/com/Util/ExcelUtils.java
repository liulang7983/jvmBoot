package com.Util;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @Author ming.li
 * @Date 2025/3/24 10:06
 * @Version 1.0
 */
public class ExcelUtils {
    public static void autoColWidth(String filePath){
        try {
            FileInputStream in = new FileInputStream(filePath);
            Workbook sheets = WorkbookFactory.create(in);
            int sheetNum = sheets.getNumberOfSheets();
            for (int i = 0; i <sheetNum ; i++) {
                Sheet sheet = sheets.getSheetAt(i);
                Integer colNum = getMaxColNumberOfSheet(sheet);
                processCol(sheet,colNum);
            }
            FileOutputStream out = new FileOutputStream(filePath);
            sheets.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Integer getMaxColNumberOfSheet(Sheet sheet){
        int colNum=0;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            if (sheet.getRow(i)==null){
                continue;
            }
            if (sheet.getRow(i).getLastCellNum()>colNum){
                colNum=sheet.getRow(i).getLastCellNum();
            }

        }

        return colNum;
    }
    public static void processCol(Sheet sheet,Integer colNum){
        for (int col = 0; col < colNum; col++) {
            int columnWidth = sheet.getColumnWidth(col);
            if (col>=256*256){
                continue;
            }
            boolean flag=true;
            int newColumnWidth=columnWidth;
            for (int row = 0; row < sheet.getLastRowNum(); row++) {
                Row currentRow;
                if (sheet.getRow(row)==null||sheet.getRow(row).getCell(col)==null){
                    continue;
                }
                currentRow = sheet.getRow(row);
                Cell cell = currentRow.getCell(col);
                if (cell.getCellType()==CellType.STRING){
                    String value = cell.getStringCellValue();
                    String[] split = value.split("\n");
                    int length=0;
                    for (String s:split){
                        length=s.trim().getBytes().length*256;
                        if (newColumnWidth<length&&length<256*256){
                            newColumnWidth=length;
                        }
                    }
                }
                if (newColumnWidth>columnWidth){
                    sheet.autoSizeColumn(col);
                }
                sheet.setColumnWidth(col,newColumnWidth);
                columnWidth=newColumnWidth;
            }
        }

    }
}
