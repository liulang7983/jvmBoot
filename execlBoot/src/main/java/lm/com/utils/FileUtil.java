package lm.com.utils;

import cn.hutool.core.date.DateUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class FileUtil {

    private final static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static boolean isExist(String path) {
        File file = new File(path);
        return file.exists();
    }

    public static String getFileStr(String imgFile) {
        byte[] data = getFileBytes(imgFile);
        /*
        返回Base64编码过的字节数组字符串
         */
        return Base64.getEncoder().encodeToString(data);
    }

    public static byte[] getFileBytes(String imgFile) {
        byte[] data = null;
        /*
        读取文件字节数组
         */
        try (InputStream in = new FileInputStream(imgFile)) {
            data = new byte[in.available()];
            while (in.read(data) > 0) {
                /*
                 ...
                 */
            }
        } catch (IOException e) {
            logger.error("读取文件字节数组异常:", e);
        }
        return data;
    }


    public static String getFileExt(String fileName) {
        return fileName != null && fileName.indexOf(".") >= 1 ? fileName.substring(fileName.lastIndexOf(".") + 1) : "";
    }

    public static void mkdir(String pathDir) {
        File dirPath = new File(pathDir);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }

    }

    public static String getFileSeparator() {
        return System.getProperty("file.separator");
    }

    public static File[] listFiles(String path, String extName) {
        File pathFile = new File(path);
        FileNameFilter filter = new FileNameFilter(extName);
        File[] files = pathFile.listFiles(filter);
        return files;
    }

    public static File[] listFiles(String path) {
        File pathFile = new File(path);
        File[] files = pathFile.listFiles();
        return files;
    }

    public static void copyFile(String oldPath, String newPath) {
        File oldFile = new File(oldPath);
        if (oldFile.exists()) {
            try (InputStream inStream = new FileInputStream(oldPath);
                 FileOutputStream fs = new FileOutputStream(newPath)) {
                int byteread;
                byte[] buffer = new byte[1024];
                while ((byteread = inStream.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteread);
                }
            } catch (Exception var21) {
                LogManager.getLogger(FileUtil.class).error("复制单个文件操作出错 copyFile exception:{}", var21);
            }
        }
    }

    public static File doZip(String sourceDir, String zipFilePath) throws IOException {
        File zipFile = new File(zipFilePath);
        File file = new File(sourceDir);
        try (OutputStream os = new FileOutputStream(zipFile);
             BufferedOutputStream bos = new BufferedOutputStream(os);
             ZipOutputStream zos = new ZipOutputStream(bos)) {
            String basePath;
            if (file.isDirectory()) {
                basePath = file.getPath();
            } else {
                basePath = file.getParent();
            }
            zipFile(file, basePath, zos);
            zos.closeEntry();
        }
        return zipFile;
    }

    private static void zipFile(File source, String basePath, ZipOutputStream zos) throws IOException {
        File[] files;
        if (source.isDirectory()) {
            files = source.listFiles();
        } else {
            files = new File[]{source};
        }

        String pathName;
        byte[] buf = new byte[1024];
        int length;
        File[] var7 = files;
        int var8 = files.length;

        for (int var9 = 0; var9 < var8; ++var9) {
            File file = var7[var9];
            if (file.isDirectory()) {
                pathName = file.getPath().substring(basePath.length() + 1) + "/";
                zos.putNextEntry(new ZipEntry(pathName));
                zipFile(file, basePath, zos);
            } else {
                pathName = file.getPath().substring(basePath.length() + 1);
                try (InputStream is = new FileInputStream(file);
                     BufferedInputStream bis = new BufferedInputStream(is)) {
                    zos.putNextEntry(new ZipEntry(pathName));
                    while ((length = bis.read(buf)) > 0) {
                        zos.write(buf, 0, length);
                    }
                }
            }
        }
    }

    /**
     * 压缩文件或文件夹至目标文件(保留目录结构)
     *
     * @param targetFilePath // 压缩后的文件路径 比如 "D:/test/testZip/test1.zip"
     * @param sourcePath     // 要被压缩的文件或文件夹路径   比如"D:/test/testZip/ccc" 或 "D:/test/testZip/ccc/aaa.txt"
     */
    public static void doZipPath(String targetFilePath, String sourcePath) throws IOException {
        try (OutputStream out = new FileOutputStream(targetFilePath); ZipOutputStream zipOut = new ZipOutputStream(out)) {
            File rootFile = new File(sourcePath);
            if (rootFile.isFile()) {
                ZipEntry zipEntry = new ZipEntry(rootFile.getName());
                zipOut.putNextEntry(zipEntry);
                Files.copy(rootFile.toPath(), zipOut);
            } else {
                writeAllFiles(sourcePath, zipOut, sourcePath);
            }
            zipOut.closeEntry();
        }
    }

    private static void writeAllFiles(String path, ZipOutputStream zipOut, String rootDir) throws IOException {
        path = path.replace("\\", "/");
        rootDir = rootDir.replace("\\", "/");
        File pathFile = new File(path);
        if (pathFile.isFile()) {
            ZipEntry zipEntry = new ZipEntry(path.replace(rootDir, ""));
            zipOut.putNextEntry(zipEntry);
            Files.copy(pathFile.toPath(), zipOut);
        } else {
            File[] files = pathFile.listFiles();
            if (files != null && files.length != 0) {
                for (File file : files) {
                    writeAllFiles(file.getAbsolutePath(), zipOut, rootDir);
                }
            } else {
                ZipEntry zipEntry = new ZipEntry(path.replace(rootDir, "") + "/");
                zipOut.putNextEntry(zipEntry);
            }
        }
    }



    public static String getFileName(String filePath) {
        if (filePath.lastIndexOf("/") > 0) {
            filePath = filePath.substring(filePath.lastIndexOf("/") + 1);
        }

        if (filePath.lastIndexOf("\\") > 0) {
            filePath = filePath.substring(filePath.lastIndexOf("\\") + 1);
        }

        return filePath;
    }

    public static String getFileNameNoExtName(String fileName) {
        if (fileName == null) {
            return null;
        } else {
            int len = fileName.lastIndexOf(".");
            if (len > 0) {
                fileName = fileName.substring(0, len);
            }

            return fileName;
        }
    }

    public static boolean saveFile(String content, String fileName) {
        try {
            FileOutputStream out = new FileOutputStream(new File(fileName));
            IOUtils.write(content, out);
        } catch (Exception e) {
            logger.error("保存文件异常:", e);
            return false;
        }
        return true;
    }

    public static String generateOutputFile(String fileName, String ext) {
        fileName = getFileNameNoExtName(fileName);
        String datetimeString = DateUtil.format(new Date(), "yyyyMMddHHmmss");
        return fileName + "_" + datetimeString + (org.apache.commons.lang3.StringUtils.isEmpty(ext) ? "" : "." + ext);
    }

    private static class FileNameFilter implements FileFilter {
        private String extendName = null;

        public FileNameFilter(String extendName) {
            this.extendName = extendName;
        }

        @Override
        public boolean accept(File file) {
            if (file.isDirectory()) {
                return true;
            } else {
                String fileName = file.getName();
                return fileName.endsWith(this.extendName);
            }
        }
    }



    public static String getFilePrefix(String fileName) {
        return fileName != null && fileName.indexOf(".") >= 1 ? fileName.substring(0, fileName.lastIndexOf(".")) : "";
    }

    public static String getFileSuffix(String fileName) {
        return fileName != null && fileName.indexOf(".") >= 1 ? fileName.substring(fileName.lastIndexOf(".") + 1) : "";
    }

    /**
     * 根据时间生成临时文件名称
     *
     * @return
     */
    public static String buildTempName() {
        String sCurTime;
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        sCurTime = sdf.format(date);

        return sCurTime;
    }

    public static void saveFile(byte[] bfile, String filePath, String fileName) {
        File file;
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        file = new File(filePath + File.separator + fileName);
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            bos.write(bfile);
        } catch (Exception e) {
            logger.error("Exception:{}", e);
        }
    }

    /**
     * 解压到指定目录
     *
     * @param zipPath 要解压的zip文件路径
     * @param descDir 解压到目标目录
     * @throws IOException
     */
    public static void unZipFiles(String zipPath, String descDir) throws IOException {
        unZipFiles(new File(zipPath), descDir);
    }

    /**
     * 解压文件到指定目录
     */
    @SuppressWarnings("rawtypes")
    public static void unZipFiles(File zipFile, String descDir) throws IOException {
        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        /*
        解决zip文件中有中文目录或者中文文件
         */
        try (ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"))) {
            for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();
                try (InputStream in = zip.getInputStream(entry)) {
                    String outPath = (descDir + zipEntryName).replaceAll("\\\\{1,}", "/");
                    /*
                     判断路径是否存在,不存在则创建文件路径
                     */
                    boolean ismkdir = false;
                    /*
                     检查此文件是否带有文件夹
                     */
                    if (outPath.lastIndexOf("/") != -1) {
                        ismkdir = true;
                    }
                    File file;
                    if (ismkdir) {
                        file = new File(outPath.substring(0, outPath.lastIndexOf("/")));
                    } else {
                        file = new File(outPath);
                    }
                    if (ismkdir && !file.exists()) {
                        file.mkdirs();
                    }
                    /*
                     判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
                     */
                    if (new File(outPath).isDirectory()) {
                        continue;
                    }
                    /*
                     输出文件路径信息
                     */
                    try (OutputStream out = new FileOutputStream(outPath)) {
                        byte[] buf1 = new byte[1024];
                        int len;
                        while ((len = in.read(buf1)) > 0) {
                            out.write(buf1, 0, len);
                        }
                    } catch (Exception e) {
                        logger.error("Exception:{}", e);
                    }
                } catch (Exception e) {
                    logger.error("Exception:{}", e);
                }
            }
        } catch (Exception e) {
            logger.error("Exception:{}", e);
        }
    }

    public static boolean isInteger(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static void dispFile(String fileName, String fullFileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileExt = getFileExt(fileName);
        if (org.apache.commons.lang3.StringUtils.isBlank(fileName)) {
            return;
        }
        if ("pdf".equalsIgnoreCase(fileExt)) {
            response.setContentType("multipart/form-data");
        } else {
            response.setContentType("image/jpeg");
        }
        if (!isExist(fullFileName)) {
            response.setStatus(417);
            return;
        }

        String agent = request.getHeader("USER-AGENT");
        if (null != agent && agent.contains("MSIE")) {
            String[] param = new String[]{URLEncoder.encode(fileName, "UTF-8").replace("+", "%20")};
            fileName = String.format("attachment;filename=\"%s\"", URLEncoder.encode(fileName, "UTF-8").replace("+", "%20"));
        } else {
            String[] param = new String[]{new String(fileName.getBytes(), StandardCharsets.ISO_8859_1)};
            fileName = String.format("filename=\"%s\"", new String(fileName.getBytes(), StandardCharsets.ISO_8859_1));
        }

        response.setHeader("Content-Disposition", fileName);

        File file = new File(cleanFileString(fullFileName));
        /*
        压缩后的图片文件
         */
        if ("jpg".equalsIgnoreCase(fileExt) || "jpeg".equalsIgnoreCase(fileExt)) {
            Thumbnails.of(file).scale(1f).toOutputStream(response.getOutputStream());
            return;
        }

        /*
        压缩处理后的图片文件流
         */
        try (InputStream inputStream = new FileInputStream(file)) {
            IOUtils.copy(inputStream, response.getOutputStream());
        } catch (IOException e) {
            logger.error("Exception:{}", e);
        }
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

    public static void copyInputStreamToFile(final InputStream source, final File target, String directoryOfTarget) throws IOException {
        if (FileUtils.directoryContains(new File(directoryOfTarget), target)) {
            FileUtils.copyInputStreamToFile(source, target);
        }
    }

    /**
     * 写入文本文件，若文件存在，则在文件末尾追加
     *
     * @param filePath 文本文件路径
     * @param text     写入的文本内容
     */
    public static void writeTextFile(String filePath, String text) {
        if (null == text || 0 == text.length()) {
            return;
        }
        File file = new File(filePath);
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            if (!file.exists() && file.createNewFile()) {
                logger.info("创建文件成功:{}", filePath);
            }
            fileWriter.write(text);
            fileWriter.write("\r\n");
        } catch (Exception e) {
            logger.error("Exception:{}", e);
        }
    }
    /**
     * 图像文件转Base64
     *
     * @param imgFile 图像文件名称
     * @return
     */
    public static String image2Base64String(String imgFile) {
        String encodedStr = "";
        try {
            File file = new File(imgFile);
            byte[] fileByte = Files.readAllBytes(file.toPath());
            encodedStr = Base64.getEncoder().encodeToString(fileByte);
        } catch (Exception e) {
            logger.error("image2Base64String 错误", e);
        }
        return encodedStr;
    }

    /**
     * 旋转图片
     *
     * @param filePath
     * @param angle
     */
    public static void rectifyImage(String filePath, Float angle) throws IOException {
        logger.info("[合同识别]图像旋转，角度:" + angle + " 文件:" + filePath);
        if (angle < 5 && angle > -5) {
            logger.info("[合同识别]角度过小放弃旋转");
            return;
        }
        BufferedImage image = ImageIO.read(new File(filePath));
        Thumbnails.of(filePath).size(image.getWidth(), image.getHeight()).rotate(-angle).toFile(filePath);
    }



    /**
     * 文件反序列化对象
     *
     * @param serialFilePath 序列化文件名称
     * @return 对象实例
     * @throws IOException
     */
    public static Object binary2Object(String serialFilePath) {
        Object obj = null;

        File file = new File(serialFilePath);
        if (!file.exists()) {
            return obj;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            obj = ois.readObject();
        } catch (Exception e) {
            logger.error("binary2Object 错误", e);
        }
        return obj;
    }

    /**
     * 对象序列化文件
     *
     * @param obj            对象实例
     * @param serialFilePath 序列化文件名称
     */
    public static void object2Binary(Object obj, String serialFilePath) throws IOException {
        File file = new File(serialFilePath);
        if (!file.exists()) {
            Files.createFile(Paths.get(serialFilePath));
        }
        try (ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(file))) {
            oo.writeObject(obj);
        } catch (Exception e) {
            logger.error("object2Binary错误", e);
        }
    }


    /**
     * 写入文本文件，若文件存在，则在文件末尾不追加
     *
     * @param filePath 文本文件路径
     * @param text     写入的文本内容
     */
    public static void writeTextFile1(String filePath, String text) {
        if (null == text || 0 == text.length()) {
            return;
        }
        try (FileWriter fileWriter = new FileWriter(filePath, false)) {
            File file = new File(filePath);
            if (!file.exists()) {
                Files.delete(Paths.get(filePath));
            }
            fileWriter.write(text);
            fileWriter.write("\r\n");
        } catch (Exception e) {
            logger.error(" ", "writeTextFile1 错误 {}", e);
        }
    }

    /**
     * 按文件名称顺序排序
     *
     * @param listFile 文件名称列表
     */
    public static void fileNameOrderBy(List<File> listFile) {
        //order by文件名称列表
        Collections.sort(listFile, (f1, f2) -> {
            String name1 = f1.getName();
            String name1WithoutExt = name1.substring(0, name1.lastIndexOf('.'));
            Integer n1 = Integer.valueOf(name1WithoutExt);
            String name2 = f2.getName();
            String name2WithoutExt = name2.substring(0, name2.lastIndexOf('.'));
            Integer n2 = Integer.valueOf(name2WithoutExt);
            return n1.compareTo(n2);
        });
    }

    /**
     * 读取json文件内容
     *
     * @param jsonFile json文件名称
     * @return json文件内容
     */
    public static String readJsonText(String jsonFile) {
        StringBuilder sbJson = new StringBuilder();
        String lineText;
        File file = new File(jsonFile);
        if (!file.exists()) {
            return "";
        }
        try (FileReader fr = new FileReader(jsonFile); BufferedReader br = new BufferedReader(fr);) {
            while (null != (lineText = br.readLine())) {
                sbJson.append(lineText);
            }
        } catch (Exception e) {
            logger.error(" ", "sbJson.append 错误 {}", e);
        }
        String jsonText = sbJson.toString();
        //替换掉字符串中的\r\t\n，以及空格字符
        jsonText = replaceOnlyBlank(jsonText);
        return jsonText;
    }

    /**
     * 去除字符串中的回车、换行
     *
     * @param text
     * @return
     */
    public static String replaceOnlyBlank(String text) {
        //替换掉字符串中的\r\t\n，以及空格字符
        Pattern p = Pattern.compile("\\t|\r|\n");
        Matcher m = p.matcher(text);
        return m.replaceAll("");
    }

    //删除文件夹以及下面的内容
    public static void deleteFiles(File file) throws IOException {
        if (!file.exists()){
            return; //若文件不存在直接返回
        }
        if (file.isDirectory()) { //目录的情况下, 遍历所有的子级文件
            for (File f : file.listFiles()) {
                deleteFiles(f);
            }
        }
        Files.delete(file.toPath()); //不管是文件还是目录, 最终都要删除
    }

    public static void deleteZip(String zipPath) { //压缩文件名称
        try {
            if (new File(zipPath).exists()) {
                Files.delete(Paths.get(zipPath));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("删除异常!");
        }
    }

    

}
