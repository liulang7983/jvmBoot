package pdfTest;

import util.PdfUtil;

/**
 * @Author ming.li
 * @Date 2025/1/3 16:05
 * 判断是否为矢量
 * @Version 1.0
 */
public class Demo2 {
    public static void main(String[] args) {
        //String pdfPath="C:\\Users\\14307\\Desktop\\pdf查重\\（已压缩）租赁物清单.pdf";
        //String pdfPath="C:\\liming\\租赁物\\报告\\33807221004340133524\\H202408028DDDY-1.pdf";
        String pdfPath="C:\\Users\\14307\\Desktop\\1\\37750623004920154436\\租赁物清单-用印版本.pdf";
        long l = System.currentTimeMillis();
        boolean b = PdfUtil.checkVecPdf(pdfPath);
        System.out.println(b);
        long end = System.currentTimeMillis();
        System.out.println(end-l);
    }
}
