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
        String ss="d://sdfd.ofd";
        System.out.println(ss.substring(0,ss.lastIndexOf(".")+1)+"pdf");
        String sss="2,3,4,5,";
        System.out.println(sss.substring(0,sss.lastIndexOf(",")));
    }
}
