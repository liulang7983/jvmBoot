package lm.com.fileTest;

import lm.com.utils.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Author ming.li
 * @Date 2024/2/27 14:50
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args) {
        File tempFile = new File("C:\\Users\\14307\\Desktop\\日志\\bs\\摘要支付宝.jpg");

        MultipartFile fileItem = FileUtils.createFileItem(tempFile, tempFile.getName());

        System.out.println(fileItem.getName());
        MultipartFile mulFileByPath = FileUtils.getMulFileByPath("C:\\Users\\14307\\Desktop\\日志\\bs\\摘要支付宝.jpg");
        System.out.println(mulFileByPath.getName());
        MultipartFile[] files=new MultipartFile[1];
        files[0]=fileItem;
        System.out.println(files.length);
    }
}
