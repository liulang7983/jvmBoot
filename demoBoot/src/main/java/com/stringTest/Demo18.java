package com.stringTest;

import com.bean.User;
import com.util.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * @author ming.li
 * @date 2023/10/26 9:59
 */
public class Demo18 {
    public static void main(String[] args) {
        String filePath="C:\\Users\\ken\\Desktop\\1.txt";
        FileUtils.writeTextFile1(filePath,"我是张三1");
    }

}
