package com.yg.edu.pdfTest;

/**
 * @Author ming.li
 * @Date 2025/3/17 14:23
 * 批量发送感觉也能实现顺序发送，他好像按顺序发到了一个队列中
 * @Version 1.0
 */

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDSignatureField;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PDFNonSignatureImageChecker {

    public static boolean hasNonSignatureImageInPDF(String filePath) {
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            // 获取表单
            PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
            List<PDSignatureField> signatureFields = new ArrayList<>();
            if (acroForm != null) {
                // 手动遍历表单字段，找出签名域
                for (PDField field : acroForm.getFields()) {
                    if (field instanceof org.apache.pdfbox.pdmodel.interactive.form.PDSignatureField) {
                        signatureFields.add((PDSignatureField)field);
                    }
                }
            }
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                PDPage page = document.getPage(i);
                PDResources resources = page.getResources();
                if (hasNonSignatureImage(resources, signatureFields)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean hasNonSignatureImage(PDResources resources, List<PDSignatureField> signatureFields) throws IOException {
        for (COSName xObjectName : resources.getXObjectNames()) {
            if (resources.isImageXObject(xObjectName)) {
                PDImageXObject image = (PDImageXObject) resources.getXObject(xObjectName);
                if (!isSignatureImage(image, signatureFields)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isSignatureImage(PDImageXObject image, List<PDSignatureField> signatureFields) {
        // 这里简单假设如果图片和签名域关联，则认为是签名图片
        for (PDSignatureField signatureField : signatureFields) {
            PDSignature signature = signatureField.getValue();
            if (signature != null) {
                // 实际中可能需要更复杂的判断逻辑来关联图片和签名
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\14307\\Desktop\\1\\37750623004920154436\\租赁物清单-用印版本.pdf";
        //String filePath = "C:\\Users\\14307\\Desktop\\pdf查重\\印章.pdf";
        boolean hasNonSignatureImage = hasNonSignatureImageInPDF(filePath);
        if (hasNonSignatureImage) {
            System.out.println("PDF 文件中存在非签名印章图片。");
        } else {
            System.out.println("PDF 文件中不存在非签名印章图片。");
        }
    }
}
