package lm.com.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.LinkedHashMap;

/**
 * @author ming.li
 * @date 2023/9/6 10:27
 */
public class FileUtils {


    /**
     * 读取ExeclMap.json内容
     *
     * @return
     */
    public static JSONObject getExeclMap() {
        String strJson = FileUtils.readResourceFileToString("ExeclMap.json", "utf-8");
        //保证和json文件中的顺序一致
        LinkedHashMap<String, Object> json = JSON.parseObject(strJson, LinkedHashMap.class, Feature.OrderedField);
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.putAll(json);
        return jsonObject;
    }


    /**
     * 读取资源文件
     *
     * @param fileName:资源文件名
     * @return
     */
    public static String readResourceFileToString(String fileName, String cs) {
        StringBuilder sb = new StringBuilder();
        try (InputStream is = FileUtils.class.getClassLoader().getResourceAsStream(fileName);
             InputStreamReader isr = new InputStreamReader(is, cs)) {
            try (BufferedReader reader = new BufferedReader(isr)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line.trim());
                }
            }
        } catch (IOException e) {
        }
        return sb.toString();
    }

    /**
     * 获取resources下files下的文件
     *
     * @param fileName:资源文件名
     * @return
     */
    public static Workbook getFilesFromResources(String fileName) {
        String fileType = getFileExt(fileName);
        Workbook wb = null;
        try (InputStream in = new ClassPathResource("files/" + fileName).getInputStream()) {
            wb = "xlsx".equalsIgnoreCase(fileType) ? new XSSFWorkbook(in) : new HSSFWorkbook(new POIFSFileSystem(in));
        } catch (Exception e) {
        }
        return wb;
    }

    public static String getFileExt(String fileName) {
        return fileName != null && fileName.indexOf('.') > -1 ? fileName.substring(fileName.lastIndexOf('.') + 1) : "";
    }


    /**
     * 创建MultipartFile
     */
    public static MultipartFile createFileItem(File file, String fieldName) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        FileItem item = factory.createItem(fieldName, "text/plain", true, file.getName());
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        try {
            FileInputStream fis = new FileInputStream(file);
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MultipartFile multipartFile = new CommonsMultipartFile(item);
        return multipartFile;
    }

    //file 转换为 MultipartFile
    public static MultipartFile getMulFileByPath(String filePath) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        String textFieldName = "textField";
        int num = filePath.lastIndexOf(".");
        String extFile = filePath.substring(num);
        FileItem item = factory.createItem(textFieldName, "text/plain", true,
                "MyFileName" + extFile);
        File newfile = new File(filePath);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        try {
            FileInputStream fis = new FileInputStream(newfile);
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, 8192))
                    != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MultipartFile mfile = new CommonsMultipartFile(item);
        return mfile;
    }
}
