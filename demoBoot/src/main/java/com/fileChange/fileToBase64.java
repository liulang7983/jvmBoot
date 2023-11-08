package com.fileChange;

import com.util.FileUtils;
import com.util.fileToBase64Util;

import java.io.File;
import java.io.IOException;

/**
 * @author ming.li
 * @date 2023/11/8 10:41
 */
public class fileToBase64 {

    public static void main(String[] args) throws IOException {
        String s = fileToBase64Util.GetBase64FromFile(new File("C:\\liming\\change\\1.png"));
        System.out.println(s);
        FileUtils.writeTextFile1("C:\\liming\\change\\1.txt",s);
    }

}

