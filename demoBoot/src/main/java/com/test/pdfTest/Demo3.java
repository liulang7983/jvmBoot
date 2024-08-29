package com.test.pdfTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author ming.li
 * @Date 2024/8/28 9:40
 * @Version 1.0
 */

public class Demo3 {

    public static void main(String[] args)throws Exception {
        File pdfFile = new File("C:\\liming\\租赁物\\报告\\远东\\33199826004256469185\\FEHPH24FL070870-L-01-抵押物清单.pdf");
        PDFTableParser tableParser = new PDFTableParser();
        try {
            List<String> tableData = tableParser.parseTable(pdfFile);
            for (String row : tableData) {
                System.out.println(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
