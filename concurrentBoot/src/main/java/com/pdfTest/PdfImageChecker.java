package com.pdfTest;

/**
 * @Author ming.li
 * @Date 2025/2/27 17:46
 * @Version 1.0
 */

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class PdfImageChecker {

    //判断是否存在图片
    public static boolean hasImages(String filePath) {
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            // 遍历 PDF 的每一页
            for (PDPage page : document.getPages()) {
                PDResources resources = page.getResources();
                if (resources != null) {
                    Iterable<COSName> xObjectNames = resources.getXObjectNames();
                    Iterator<COSName> iterator = xObjectNames.iterator();
                    while (iterator.hasNext()){
                        COSName next = iterator.next();
                        PDXObject xObject = resources.getXObject(next);
                        if (xObject instanceof PDImageXObject) {
                            return true;
                        }
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean hasImageInPDF(String filePath) {
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                PDPage page = document.getPage(i);
                PDResources resources = page.getResources();
                Iterable<COSName> xObjectNames = resources.getXObjectNames();
                for (COSName xObjectName : resources.getXObjectNames()) {
                    if (resources.isImageXObject(xObjectName)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        //String filePath = "C:\\Users\\14307\\Desktop\\1\\37750623004920154436\\租赁物清单-用印版本.pdf";
        //String filePath = "C:\\Users\\14307\\Desktop\\1\\37750623004920154436\\37750623004920154436.pdf";
        //String filePath = "C:\\liming\\租赁物\\矢量\\2\\存在合并行与列\\24DH1N6RMP-租赁物件清单.pdf";
        //String filePath = "C:\\Users\\14307\\Desktop\\pdf查重\\WXL32201025租赁物清单.pdf";
        String filePath = "C:\\Users\\14307\\Desktop\\pdf查重\\印章.pdf";
        long l = System.currentTimeMillis();
        //boolean result = hasImages(filePath);
        boolean result = hasImageInPDF(filePath);
        long l1 = System.currentTimeMillis();
        System.out.println("耗时:"+(l1-l));
        if (result) {
            System.out.println("PDF 中包含图片。");
        } else {
            System.out.println("PDF 中不包含图片。");
        }
    }
}
