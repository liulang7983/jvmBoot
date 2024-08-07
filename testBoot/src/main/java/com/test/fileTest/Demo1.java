package com.test.fileTest;

import java.io.File;

public class Demo1 {
    public static void main(String[] args) {
        String path="C:\\Users\\14307\\Desktop\\日志\\mi\\切割\\test";
        File[] files = new File(path).listFiles();
        for (File f:files){
            String name = f.getName();
            System.out.println(name);
            String substring = name.substring(0, name.indexOf("."));
            System.out.println(substring);
        }
    }
}
