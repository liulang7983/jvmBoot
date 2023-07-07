package com.stringTest;

import com.util.DateUtil;
import com.util.StringUtil;

/**
 * @author ming.li
 * @date 2023/5/31 16:13
 */
public class Demo1 {
    public static void main(String[] args) {
        System.out.println(StringUtil.repairStr("222.1234456678",".",2));
        System.out.println(StringUtil.isNumeric2("2.22X"));
        String str="sss.s";
        System.out.println(str.substring(str.lastIndexOf(".")+1));
    }
}
