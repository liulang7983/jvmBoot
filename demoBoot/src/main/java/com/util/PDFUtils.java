package com.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.log.LogUtils;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * pdf工具类
 */
public class PDFUtils {


    /**
     * PDF分解图片文件
     *
     * @param pdfPath pdf文件路径
     */
    public static void cutPNG(String pdfPath) throws IOException {
        File pdf = new File(pdfPath);
        cutPNG(pdfPath, pdf.getParent() + File.separator + pdf.getName() + "_pngs");
    }

    /**
     * PDF分解图片文件
     *
     * @param pdfPath pdf文件路径
     * @param outPath 输出文件夹路径
     */
    public static void cutPNG(String pdfPath, String outPath) throws IOException {
        File outDir = new File(outPath);
        if (!outDir.exists()) outDir.mkdirs();
        cutPNG(new File(pdfPath), outDir);
    }

    /**
     * PDF分解图片文件
     *
     * @param pdf    pdf文件
     * @param outDir 输出文件夹
     */
    public static void cutPNG(File pdf, File outDir) throws IOException {
        System.out.println("PDF分解图片工作开始");
        List<BufferedImage> list = getImgList(pdf);
        System.out.println(pdf.getName() + " 一共发现了 " + list.size() + " 页");
        FileUtils.cleanDir(outDir);
        for (int i = 0; i < list.size(); i++) {
            IMGUtils.saveImageToFile(list.get(i), outDir.getAbsolutePath() + File.separator + (i + 1) + ".png");
        }
        System.out.println("PDF分解图片工作结束，一共分解出" + list.size() + "个图片文件，保存至：" + outDir.getAbsolutePath());
    }

    /**
     * 将pdf文件转换成一张图片
     *
     * @param pdfPath pdf文件路径
     */
    public static void transition_ONE_PNG(String pdfPath) throws IOException {
        transition_ONE_PNG(new File(pdfPath));
    }

    /**
     * 将pdf文件转换成一张图片
     *
     * @param pdf pdf文件
     */
    public static void transition_ONE_PNG(File pdf) throws IOException {
        System.out.println("PDF转换长图工作开始");
        List<BufferedImage> list = getImgList(pdf);
        System.out.println(pdf.getName() + " 一共发现了 " + list.size() + " 页");
        BufferedImage image = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            image = IMGUtils.verticalJoinTwoImage(image, list.get(i));
        }
        byte[] data = IMGUtils.getBytes(image);
        String imgPath = pdf.getParent() + File.separator + pdf.getName().replaceAll("\\.", "_") + ".png";
        FileUtils.saveDataToFile(imgPath, data);
        System.out.println("PDF转换长图工作结束，合成尺寸：" + image.getWidth() + "x" + image.getHeight() + "，合成文件大小：" + data.length / 1024 + "KB，保存至：" + imgPath);
    }


    /**
     * 将PDF文档拆分成图像列表
     *
     * @param pdf PDF文件
     */
    public static List<BufferedImage> getImgList(File pdf) throws IOException {
        PDDocument pdfDoc = PDDocument.load(pdf);
        List<BufferedImage> imgList = new ArrayList<>();
        PDFRenderer pdfRenderer = new PDFRenderer(pdfDoc);
        int numPages = pdfDoc.getNumberOfPages();
        for (int i = 0; i < numPages; i++) {
            BufferedImage image = pdfRenderer.renderImageWithDPI(i, 300, ImageType.RGB);
            imgList.add(image);
        }
        pdfDoc.close();
        return imgList;
    }

    /**
     * 图片合成PDF
     *
     * @param pngsDirPath 图片文件夹路径
     */
    public static void merge_PNG(String pngsDirPath) throws Exception {
        File pngsDir = new File(pngsDirPath);
        merge_PNG(pngsDir, pngsDir.getName() + ".pdf");
    }

    /**
     * 图片合成pdf
     *
     * @param pngsDir 图片文件夹
     * @param pdfName 合成pdf名称
     */
    private static void merge_PNG(File pngsDir, String pdfName) throws Exception {
        File pdf = new File(pngsDir.getParent() + File.separator + pdfName);
        if (!pdf.exists()) pdf.createNewFile();
        File[] pngList = pngsDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
        System.out.println("在" + pngsDir.getAbsolutePath() + "，一共发现" + pngList.length + "个PNG文件");
        Document document = new Document();
        FileOutputStream fo = new FileOutputStream(pdf);
        PdfWriter writer = PdfWriter.getInstance(document, fo);
        document.open();
        for (File f : pngList) {
            document.newPage();
            byte[] bytes = FileUtils.getFileBytes(f);
            Image image = Image.getInstance(bytes);
            float heigth = image.getHeight();
            float width = image.getWidth();
            int percent = getPercent2(heigth, width);
            image.setAlignment(Image.MIDDLE);
            image.scalePercent(percent + 3);// 表示是原来图像的比例;
            document.add(image);
            System.out.println("正在合成" + f.getName());
        }
        document.close();
        writer.close();
        System.out.println("PDF文件生成地址：" + pdf.getAbsolutePath());

    }


    private static int getPercent2(float h, float w) {
        int p = 0;
        float p2 = 0.0f;
        p2 = 530 / w * 100;
        p = Math.round(p2);
        return p;
    }

    /**
     * 多PDF合成
     *
     * @param pngsDirPath pdf所在文件夹路径
     */
    public static void merge_PDF(String pngsDirPath) throws IOException {
        File pngsDir = new File(pngsDirPath);
        merge_PDF(pngsDir, pngsDir.getName() + "_合并.pdf");
    }

    /**
     * 多PDF合成
     *
     * @param pngsDir pdf所在文件夹
     * @param pdfName 合成pdf文件名
     */
    private static void merge_PDF(File pngsDir, String pdfName) throws IOException {
        File[] pdfList = pngsDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));
        System.out.println("在" + pngsDir.getAbsolutePath() + "，一共发现" + pdfList.length + "个PDF文件");
        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
        pdfMergerUtility.setDestinationFileName(pngsDir.getParent() + File.separator + pdfName);
        for (File f : pdfList) {
            pdfMergerUtility.addSource(f);
        }
        pdfMergerUtility.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
    }

    public static void getPartPDF(String pdfPath, int from, int end) throws Exception {
        pdfPath = pdfPath.trim();
        Document document = null;
        PdfCopy copy = null;
        PdfReader reader = new PdfReader(pdfPath);
        int n = reader.getNumberOfPages();
        if (end == 0) {
            end = n;
        }
        document = new Document(reader.getPageSize(1));
        copy = new PdfCopy(document, new FileOutputStream(pdfPath.substring(0, pdfPath.length() - 4) + "_" + from + "_" + end + ".pdf"));
        document.open();
        for (int j = from; j <= end; j++) {
            document.newPage();
            PdfImportedPage page = copy.getImportedPage(reader, j);
            copy.addPage(page);
        }
        document.close();
    }


}
