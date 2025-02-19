package com.util;

import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Author ming.li
 * @Date 2025/2/17 8:55
 * @Version 1.0
 */
public class PdfToImage {
    public static void pdfToImage(String pdfPath){
        try {
            String savePath="C:\\Users\\14307\\Desktop\\22";
            InputStream in = new BufferedInputStream(new FileInputStream(pdfPath));
            PDFParser pdfParser = new PDFParser(new RandomAccessBuffer(in));
            pdfParser.parse();
            PDDocument pdDocument = pdfParser.getPDDocument();
            PDFRenderer renderer = new PDFRenderer(pdDocument);
            int numberOfPages = pdDocument.getNumberOfPages();
            for (int i = 0; i <numberOfPages ; i++) {
                String savePathName=savePath+ File.separator+(i+1)+".jpg";
                BufferedImage bufferedImage = renderer.renderImageWithDPI(i, 200);
                ImageIO.write(bufferedImage,"jpg",new File(savePathName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
