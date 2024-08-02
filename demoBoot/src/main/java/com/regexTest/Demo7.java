package com.regexTest;

/**
 * @Author ming.li
 * @Date 2024/7/26 9:57
 * @Version 1.0
 */
public class Demo7 {
    //正的整数或者小数
    public static void main(String[] args) {

        String s = "0.10";
        String regstr = "^[+]?(([0-9]\\d*)|0)(\\.\\d+)?$";
        if (s.matches(regstr)){
            System.out.println("Matching pass!");
        }else{
            System.out.println("Matching fail!");
        }
    }


}
