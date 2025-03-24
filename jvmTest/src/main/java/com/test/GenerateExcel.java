package com.test;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class GenerateExcel {
    private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int ROW_COUNT = 14000;
    private static final int COLUMN_COUNT = 400;
    private static final int MAX_LENGTH = 10;

    public static void main(String[] args) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        Random random = new Random();

        for (int rowIndex = 0; rowIndex < ROW_COUNT; rowIndex++) {
            Row row = sheet.createRow(rowIndex);
            System.out.println("行数:"+rowIndex);
            for (int colIndex = 0; colIndex < COLUMN_COUNT; colIndex++) {
                Cell cell = row.createCell(colIndex);
                String randomText = generateRandomText(random);
                cell.setCellValue(randomText);
            }
        }

        // 获取当前类的类加载器资源路径
        String classPath = GenerateExcel.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        // 向上查找直到找到当前 module 的根目录
        File moduleDir = new File(classPath).getParentFile();
        System.out.println("路径:"+moduleDir);
        while (moduleDir != null &&!new File(moduleDir, "src").exists()) {
            moduleDir = moduleDir.getParentFile();
        }

        if (moduleDir != null) {
            File resourceDirectory = new File(moduleDir, "src/main/resources/file");
            if (!resourceDirectory.exists()) {
                resourceDirectory.mkdirs();
            }
            File outputFile = new File(resourceDirectory, "large_excel.xlsx");

            try (FileOutputStream fileOut = new FileOutputStream(outputFile)) {
                workbook.write(fileOut);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String generateRandomText(Random random) {
        StringBuilder text = new StringBuilder();
        int length = random.nextInt(MAX_LENGTH) + 1;
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHAR_LIST.length());
            text.append(CHAR_LIST.charAt(index));
        }
        return text.toString();
    }
}
