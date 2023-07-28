package com.fileTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author ming.li
 * @date 2023/7/26 17:05
 */
public class Demo2 {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\ken\\Desktop\\日志\\文档比对\\2.json");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("我是你爹");
        fileWriter.close();
    }
}
