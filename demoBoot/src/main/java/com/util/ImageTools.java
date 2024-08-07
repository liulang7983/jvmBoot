package com.util;

import com.test.enumUtil.EnumOcrFileType;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.FutureTask;

/**
 * @author ming.li
 * @date 2023/6/2 15:18
 */
public class ImageTools {
    public static final Logger logger = LoggerFactory.getLogger(ImageTools.class);

    public ImageTools() {
    }

    public static boolean rotateImage(String srcFileName, String toFileName, int angel) {
        try {
            Thumbnails.of(new String[]{srcFileName}).scale(1.0D).rotate((double)angel).toFile(toFileName);
            return true;
        } catch (FileNotFoundException var4) {
            logger.error("rotateImage FileNotFoundException", var4);
        } catch (IOException var5) {
            logger.error("rotateImage IOException", var5);
        }

        return false;
    }

    public static boolean rotateImage(File imageFile, int angel) {
        try {
            Thumbnails.of(new File[]{imageFile}).scale(1.0D).rotate((double)angel).toFile(imageFile);
            return true;
        } catch (FileNotFoundException var3) {
            logger.error("rotateImage FileNotFoundException", var3);
        } catch (IOException var4) {
            logger.error("rotateImage IOException", var4);
        }

        return false;
    }

    public static boolean resizeImage(File imageFile, float scale, float quality) {
        try {
            Thumbnails.of(new File[]{imageFile}).scale((double)scale).outputQuality(quality).toFile(imageFile);
            return true;
        } catch (IOException var4) {
            logger.error("resizeImage Exception", var4);
            return false;
        }
    }

    public static boolean resizeImageByFileSize(File imageFile, int compressSize) {
        String path = imageFile.getAbsolutePath();
        String ext = path.substring(path.lastIndexOf(46));
        long realKbSize = imageFile.length() / 1024L;
        long tarKbSize = (long)(compressSize * 1024);
        if (realKbSize <= tarKbSize) {
            logger.info("resizeImageByFileSize realKbSize:{} tarKbSize:{}", realKbSize, tarKbSize);
            return true;
        } else if (imageFile.exists() && imageFile.isFile()) {
            if (!ext.equalsIgnoreCase(".jpg") && !ext.equalsIgnoreCase(".png") && !ext.equalsIgnoreCase(".bmp")) {
                logger.error("resizeImageByFileSize failed:{} 不支持压缩的文件类型", path);
                return false;
            } else {
                float d = (float)tarKbSize / (float)realKbSize;
                return resizeImage(imageFile, 1.0F, d);
            }
        } else {
            logger.error("resizeImageByFileSize failed:{} 文件不存在", path);
            return false;
        }
    }

    public static boolean savePdfToImg(String filePath, String exportPath, String range, Float dpi) {
        try {
            if (dpi == 0.0F) {
                dpi = 96.0F;
            }

            File pdfFile = new File(filePath);
            int maxThread = 5;
            PDDocument doc = PDDocument.load(pdfFile);
            int pageNumber = doc.getNumberOfPages();
            List<Integer> rangeList = getPageList(range, pageNumber);
            int pageCount = rangeList.size();
            int threadCount = (int)Math.ceil((double)pageCount / ((double)maxThread * 1.0D));
            if (threadCount > 10) {
                threadCount = 10;
                maxThread = (int)Math.ceil((double)pageCount / ((double)threadCount * 1.0D));
            }

            FutureTask<List<String>>[] futureTasks = new FutureTask[threadCount];

            int i;
            for(i = 0; i < threadCount; ++i) {
                int startIndex = i * maxThread;
                int endIndex = i * maxThread + maxThread - 1;
                if (endIndex >= pageCount) {
                    endIndex = pageCount - 1;
                }

                PdfToImageTask task = new PdfToImageTask(exportPath, filePath, startIndex, endIndex, dpi, rangeList);
                futureTasks[i] = new FutureTask(task);
                Thread worker = new Thread(futureTasks[i]);
                worker.start();
            }

            for(i = 0; i < threadCount; ++i) {
                futureTasks[i].get();
            }

            doc.close();
            return true;
        } catch (Exception var17) {
            logger.error("pdf转图片错误", var17);
            return false;
        }
    }

    public static List<Integer> getPageList(String range, Integer pageNumber) {
        List<Integer> rangeList = new ArrayList();
        if (StringUtils.isEmpty(range)) {
            for(int i = 1; i <= pageNumber; ++i) {
                rangeList.add(i);
            }
        } else {
            String[] split = range.split(",");
            String[] var4 = split;
            int var5 = split.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String s = var4[var6];
                if (s.contains("-")) {
                    String[] split1 = s.split("-");

                    for(int i = Integer.parseInt(split1[0]); i <= Integer.parseInt(split1[1]); ++i) {
                        rangeList.add(i);
                    }
                } else {
                    rangeList.add(Integer.parseInt(s));
                }
            }
        }

        Collections.sort(rangeList);
        return rangeList;
    }

    /**
     * 压缩文件，按目的压缩
     * @param imageFile:文件
     * @return
     */
    public static File resizeImageSize(File imageFile) throws IOException {
        if (OcrUtil.isPdf(imageFile.getAbsolutePath())){
            return imageFile;
        }
        float size = ((float) FileUtils.sizeOf(imageFile))/ 1024 / 1024;
        float quality = 0.8f;
        if (size < 4){
            //小于4m，无需压缩
            return imageFile;
        }
        String strExtName = StringUtils.isNotBlank(imageFile.getName()) ? imageFile.getName().substring(imageFile.getName().lastIndexOf('.') + 1) : "";
        //png图片先转成jpg再压缩
        if (strExtName.equalsIgnoreCase(EnumOcrFileType.OCR_FILE_TYPE_PNG.getFileType())){
            //新图片路径
            String newFileSrc = imageFile.getPath().substring(0,imageFile.getPath().lastIndexOf('.') + 1)+EnumOcrFileType.OCR_FILE_TYPE_JPG.getFileType();
            Thumbnails.of(imageFile).scale(1f).toFile(newFileSrc);
            imageFile = new File(newFileSrc);
            size = ((float) FileUtils.sizeOf(imageFile))/ 1024 / 1024;
            if (size < 4){
                //小于4m，无需压缩
                return imageFile;
            }
        }
        if (size >= 8){
            quality = 5/size;
        }
        return resizeImage1(imageFile,1f,quality);
    }

    /**
     * 压缩图片
     * @param imageFile:图片文件，支持JPG，PNG,BMP
     * @param scale:绽放比例
     * @param quality:质量
     * @return
     */
    public static File resizeImage1(File imageFile,float scale,float quality){
        try
        {
            Thumbnails.of(imageFile).scale(scale).outputQuality(quality).toFile(imageFile);
            return imageFile;
        }catch (IOException e) {
            logger.error("resizeImage exception["+e.getMessage()+"]");
        }
        return imageFile;
    }
}

