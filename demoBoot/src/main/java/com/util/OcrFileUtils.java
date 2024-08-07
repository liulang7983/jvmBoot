package com.util;



import com.test.enumUtil.EnumOcrFileType;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * @author qinwu.zhu
 * @since 2020/4/6 17:03
 */
public class OcrFileUtils {

    private static final Logger logger = LoggerFactory.getLogger(OcrFileUtils.class);

    public static final String TYPE_JPG = ".jpg";
    public static final String TYPE_GIF = ".gif";
    public static final String TYPE_PNG = ".png";
    public static final String TYPE_BMP = ".bmp";
    public static final String WAIT_PROCESS = "waitprocess";
    public static final String FORMATTED = "formatted";
    public static final String BLUR = "blur";
    public static final String CUT = "cut_";
    public static final String THUMBNAIL = "_thumbnail.";

    private OcrFileUtils() {
    }



    /**
     * 清除文件目录中..，解决安全扫描问题
     *
     * @param fullFileName
     * @return
     */
    public static String cleanFileString(String fullFileName) {

        if (fullFileName == null) {
            return null;
        }

        return FilenameUtils.normalize(fullFileName);
    }


    public static boolean saveMultipartFile(InputStream inStream, String newPath) {
        try (FileOutputStream fs = new FileOutputStream(newPath);
             InputStream in = inStream) {
            byte[] buffer = new byte[10240];
            int byteread1;
            while ((byteread1 = in.read(buffer)) != -1) {
                fs.write(buffer, 0, byteread1);
            }
        } catch (Exception e) {
            logger.error("saveMultipartFile 复制单个文件操作出错 Exception: {}", e);
            return false;
        }
        return true;
    }


    /**
     * 下载文件
     * @param fileName
     * @param stream
     * @param res
     */
    public static void copyFileToDownLoad(String fileName, InputStream stream, HttpServletResponse res) {

        try (OutputStream os = res.getOutputStream()) {

            res.reset();
            res.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8").replace("+", "%20"));
            String fileExt = OcrFileUtils.getFileExt(fileName);
            if (EnumOcrFileType.OCR_FILE_TYPE_XLSX.getFileType().equalsIgnoreCase(fileExt) || EnumOcrFileType.OCR_FILE_TYPE_XLS.getFileType().equalsIgnoreCase(fileExt)) {
                res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            } else if (EnumOcrFileType.OCR_FILE_TYPE_PDF.getFileType().equalsIgnoreCase(fileExt)) {
                res.setContentType("application/pdf;charset=utf-8");
            } else {
                res.setContentType("application/octet-stream; charset=gbk");
            }
            IOUtils.copy(stream, os);
        } catch (IOException e) {
            logger.error("CopyFileToDownLoad Exception e ={}", e.getMessage(), e);
        }
    }

    public static String getFileExt(String fileName) {
        return fileName != null && fileName.indexOf('.') > -1 ? fileName.substring(fileName.lastIndexOf('.') + 1) : "";
    }


    public static boolean isExist(String path) {
        return new File(cleanFileString(path)).exists();
    }


    public static void dispFile(String fileName, String fullFileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileExt = OcrFileUtils.getFileExt(fileName);
        if (StringUtils.isBlank(fileName)) {
            logger.error("dispFile function failed fileName is null");
            return;
        }
        if ("pdf".equalsIgnoreCase(fileExt)) {
            response.setContentType("multipart/form-data");
        } else {
            response.setContentType("image/jpeg");
        }
        if (!OcrFileUtils.isExist(fullFileName)) {
            response.setStatus(417);
            return;
        }

        fileName = formatFileName(request,fileName);

        response.setHeader("Content-Disposition", fileName);

        File file = new File(cleanFileString(fullFileName));
        logger.info("图片大小为："+file.length() / 1024 + "kb");
        //压缩后的图片文件
        if (fileExt.equals(EnumOcrFileType.OCR_FILE_TYPE_JPG.getFileType())) {
            Thumbnails.of(file).scale(1f).outputQuality(0.25f).toOutputStream(response.getOutputStream());
            return;
        }

        //压缩处理后的图片文件流
        try (InputStream inputStream = new FileInputStream(file)) {
            IOUtils.copy(inputStream, response.getOutputStream());
        } catch (IOException e) {
            logger.error("dispFile function IOException e={}", e.getMessage(), e);
        }
    }

    public static String formatFileName(HttpServletRequest request,String fileName) throws UnsupportedEncodingException {
        String resultName = "";
        String agent = request.getHeader("USER-AGENT");
        if (null != agent && agent.contains("MSIE")) {
             resultName = String.format("attachment;filename=\"%s\"", URLEncoder.encode(fileName, "UTF-8").replace("+", "%20"));
        } else {
             resultName = String.format("filename=\"%s\"", new String(fileName.getBytes(), StandardCharsets.ISO_8859_1));
        }
        return resultName;
    }



    /**
     * 路径为文件且不为空则进行删除
     *
     * @param sPath
     * @throws IOException
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(cleanFileString(sPath));
        // 路径为文件且不为空则进行删除
        try {
            if (file.isFile() && file.exists()) {
                Files.delete(file.toPath());
                flag = true;
            }
        } catch (IOException e) {
            logger.error("IOException deleteFile e={}", e.getMessage(), e);
        }

        return flag;
    }



    /**
     * 根据文件路径读取byte[] 数组
     */
    public static byte[] readFileByBytes(File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException(file.getAbsolutePath());
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
            BufferedInputStream in = null;

            try {
                in = new BufferedInputStream(new FileInputStream(file));
                short bufSize = 1024;
                byte[] buffer = new byte[bufSize];
                int len1;
                while (-1 != (len1 = in.read(buffer, 0, bufSize))) {
                    bos.write(buffer, 0, len1);
                }

                byte[] var7 = bos.toByteArray();
                return var7;
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException var14) {
                    logger.error("readFileByBytes error",var14);
                }

                bos.close();
            }
        }
    }

}
