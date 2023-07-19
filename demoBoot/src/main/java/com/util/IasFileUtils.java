package com.util;

import com.thoughtworks.xstream.core.BaseException;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.util.Base64;

/**
 * @Author jiajun.xiong
 * @Date 2021/1/6 15:38
 * @Version 1.0
 */
public class IasFileUtils {

    private static final Logger logger = LoggerFactory.getLogger(IasFileUtils.class);

    /**
     * 通过文件名获取文件类型后缀
     *
     * @param fileName
     * @return
     */
    public static String getFileExt(String fileName) {
        return fileName != null && fileName.indexOf('.') > -1 ? fileName.substring(fileName.lastIndexOf('.') + 1) : "";
    }

    /**
     * 根据文件路径获取pdf的页数
     *
     * @param fullFilePath
     * @return
     */
    public static Integer getPdfPageSize(String fullFilePath) {
        PDDocument doc = null;
        try {
            doc = PDDocument.load(new File(fullFilePath));
            return doc.getNumberOfPages();
        } catch (Exception e) {
            logger.error("根据fileId获取pdf页数出现异常:", e);
            return 0;
        } finally {
            if (doc != null) {
                try {
                    doc.close();
                } catch (IOException e) {
                    logger.error("关闭PDDocument流出现异常:", e);
                }
            }
        }
    }


    /**
     * 判断此目录是否存在
     *
     * @param path
     * @return
     */
    public static boolean isExist(String path) {
        File file = new File(path);
        return file.exists();
    }

    /**
     * 创建目录和文件
     */
    public static void createFile(String dirPath) {
        try {
            File dir = new File(dirPath);
            //判断上级目录是否是目录
            if (dir.getParentFile().isDirectory() && !dir.exists()) {
                //创建文件目录
                dir.mkdirs();
            }
        } catch (Exception e) {
            logger.error("createFile exception:", e);
        }
    }


    /**
     * 根据全路径获取去掉.后缀的路径
     *
     * @param fullFmsFilePath 带文件名的全路径
     * @return
     */
    public static String getNoExtPath(String fullFmsFilePath) {
        return fullFmsFilePath.substring(0, fullFmsFilePath.lastIndexOf(46));
    }



    public void responseImg(String fullFileName, String fileName, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (fullFileName.toLowerCase().endsWith(".pdf")) {
                response.setContentType("multipart/form-data");
            } else {
                response.setContentType("image/jpeg");
            }
            File file = new File(fullFileName);
            logger.info("dispEcmFile filenae:{}", fullFileName);
            response.setContentLength((int) file.length());

            String agent = request.getHeader("USER-AGENT");
            if (null != agent && agent.contains("MSIE")) {
                String replace = URLEncoder.encode(fileName, "UTF-8").replace("+", "%20");
                Object[] objects = new Object[1];
                objects[0] = replace;
                fileName = String.format("attachment;filename=\"%s\"", objects);
            } else {
                String iso8859fileName = new String(fileName.getBytes(), "ISO-8859-1");
                fileName = String.format("filename=\"%s\"", iso8859fileName);
            }

            response.setHeader("Content-Disposition", fileName);
            try (InputStream is = new FileInputStream(fullFileName)) {
                IOUtils.copy(is, response.getOutputStream());
            }
        } catch (Exception e) {
            logger.error("返回图片出错", e);
        }
    }



    private static Color avgOne(int red, int green, int blue) {
        int avg = Math.round((red * 1f + green * 0f + blue * 0f));
        return new Color(avg, avg, avg);
    }

    /**
     * 图片base64灰度化转换
     * @param base64 图片base64
     * @throws BaseException
     */
    public static String imageGrayscaleByBase(String base64){
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] bytes = decoder.decode(base64);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            BufferedImage img = ImageIO.read(bais);

//            BufferedImage img = ImageIO.read(new File(filePath));
            int w = img.getWidth(), h = img.getHeight();
            // 创建新的灰度图片画板
            BufferedImage gray = new BufferedImage(w, h, img.getType());
            Graphics2D g2d = gray.createGraphics();
            g2d.setColor(null);
            g2d.fillRect(0, 0, w, h);

            for (int x = 0; x < w; x++) {
                for (int y = 0; y<h; y++) {
                    // 针对像素点的颜色灰度化之后，重新绘制
                    int color = img.getRGB(x, y);
                    Color grayColor = avgOne((color & 0xff0000) >> 16, (color & 0xff00) >> 8, color & 0x0000ff);
                    g2d.setColor(grayColor);
                    g2d.fillRect(x, y, 1, 1);
                }
            }
            g2d.dispose();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(gray,"jpg", outputStream);
            Base64.Encoder  encoder = Base64.getEncoder();
            String base64Img = encoder.encodeToString(outputStream.toByteArray());
//            String base64Img = Base64Util.encode(outputStream.toByteArray());
            outputStream.close();
            return base64Img;
        }catch (Exception e){
            logger.error("图片灰度化转换错误 ", e);
        }
        return null;
    }

    /**
     * 图片灰度化转换
     * @param filePath 图片路径
     * @throws BaseException
     */
    public static String imageGrayscaleByPath(String filePath){
        try {
            BufferedImage img = ImageIO.read(new FileInputStream(filePath));

//            BufferedImage img = ImageIO.read(new File(filePath));
            int w = img.getWidth(), h = img.getHeight();
            // 创建新的灰度图片画板
            BufferedImage gray = new BufferedImage(w, h, img.getType());
            Graphics2D g2d = gray.createGraphics();
            g2d.setColor(null);
            g2d.fillRect(0, 0, w, h);

            for (int x = 0; x < w; x++) {
                for (int y = 0; y<h; y++) {
                    // 针对像素点的颜色灰度化之后，重新绘制
                    int color = img.getRGB(x, y);
                    Color grayColor = avgOne((color & 0xff0000) >> 16, (color & 0xff00) >> 8, color & 0x0000ff);
                    g2d.setColor(grayColor);
                    g2d.fillRect(x, y, 1, 1);
                }
            }
            g2d.dispose();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(gray,"jpg", outputStream);
            Base64.Encoder  encoder = Base64.getEncoder();
            String base64Img = encoder.encodeToString(outputStream.toByteArray());
//            String base64Img = Base64Util.encode(outputStream.toByteArray());
            outputStream.close();
            return base64Img;
        }catch (Exception e){
            logger.error("图片灰度化转换错误 ", e);
        }
        return null;
    }



    public static String savePageToJpg(File pdfFile, String savePdfFile, int page, float dpi) throws Exception {
        PDDocument doc = PDDocument.load(pdfFile);
        PDFRenderer renderer = new PDFRenderer(doc);
        int pageCount = doc.getNumberOfPages();
        if (page > pageCount) {
            logger.error("savePageToJpg page[" + page + "] > pageCount[" + pageCount + "] error");
            doc.close();
            throw new Exception("页码超过PDF文件总页数");
        } else {
            BufferedImage image = renderer.renderImageWithDPI(page - 1, dpi);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            try {
                // 设置图片的格式
                ImageIO.write(image, "jpg", stream);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Base64.Encoder  encoder = Base64.getEncoder();
            String base64 = encoder.encodeToString(stream.toByteArray());
//            base64= imageGrayscale(base64);
            doc.close();
            return base64;
        }
    }
}
