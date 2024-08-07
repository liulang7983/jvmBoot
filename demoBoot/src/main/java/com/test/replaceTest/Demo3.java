package com.test.replaceTest;

/**
 * @author ming.li
 * @date 2023/10/9 11:26
 */
public class Demo3 {
    public static void main(String[] args) {
        String str="11.pdf【1】";
        int start = str.indexOf("【");
        int end = str.indexOf("】");
        String substring = str.substring(start+1, end);
        System.out.println(substring);
        String substring1 = str.substring(0, start);
        System.out.println(substring1);
        str=str.substring(0,substring1.lastIndexOf("."));
        System.out.println(str);
        System.out.println(str+"-"+substring);
    }
}
