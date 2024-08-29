package com.test.pdfTest;

/**
 * @Author ming.li
 * @Date 2024/8/28 9:44
 * @Version 1.0
 */
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PDFTableParser extends PDFTextStripper {

    private List<String> tableData = new ArrayList<>();

    public PDFTableParser() throws IOException {
    }

    public List<String> parseTable(File file) throws IOException {
        PDDocument document = PDDocument.load(file);
        setSortByPosition(true);
        setStartPage(0);
        setEndPage(document.getNumberOfPages());

        for (int i = 0; i < document.getPages().getCount(); i++) {
            tableData.clear();
            processPage(document.getPage(i));
        }
        document.close();

        return tableData;
    }

    @Override
    protected void processTextPosition(TextPosition text) {
        String txt = text.getUnicode();
        // 处理换行符
        if (txt.contains("\n")) {
            String[] lines = txt.split("\n");
            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    tableData.add(line.trim());
                }
            }
        } else {
            tableData.add(txt.trim());
        }
    }
}
