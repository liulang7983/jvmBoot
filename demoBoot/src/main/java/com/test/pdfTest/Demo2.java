package com.test.pdfTest;

import com.util.PDFUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * @author ming.li
 * @date 2023/9/8 11:36
 */
public class Demo2 {
    public static void main(String[] args)throws Exception {
        String path="C:\\liming\\农商行\\合同\\合同\\PDF\\未命名1_加水印.pdf";
        String fileName="未命名1_加水印.pdf";
        List<BufferedImage> imgList = PDFUtils.getImgList(new File(path));
        File outputfile = new File("C:\\liming\\农商行\\合同\\合同\\PDF\\image.jpg");
        //ImageIO.write(imgList.get(0), "jpg", outputfile);
        int indexOf = path.lastIndexOf(File.separator);
        System.out.println(path.lastIndexOf(File.separator));
        System.out.println(path.substring(0,indexOf));
        String Fpath=path.substring(0,indexOf)+File.separator+fileName.substring(0,fileName.lastIndexOf('.'))+".jpg";
        File file = new File(Fpath);
        ImageIO.write(imgList.get(0), "jpg", file);
    }
}
