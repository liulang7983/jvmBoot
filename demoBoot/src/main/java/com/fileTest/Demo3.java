package com.fileTest;

import com.util.FileUtils;

import java.io.File;

/**
 * @author ming.li
 * @date 2023/10/19 14:41
 */
public class Demo3 {
    public static void main(String[] args) {
        File file=new File("C:\\liming\\发票\\2.jpg");
        FileUtils.changeFile(file);
    }
}
