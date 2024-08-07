package com.test.fileTest;

import java.io.File;

/**
 * @author ming.li
 * @date 2023/7/5 11:15
 */
public class Demo1 {
    public static void main(String[] args) {
        File file = new File("C://Users//ken//Desktop/日志/框选/截取.png");
        String path = file.getPath();
        System.out.println(path);
        String property = System.getProperty("C://Users//ken//Desktop/日志/框选/截取.png");
        String filePath="C://Users//ken//Desktop/日志/框选/截取.png";
        String exportPath = filePath.substring(0, filePath.lastIndexOf(47));
        System.out.println(exportPath);
    }
}
