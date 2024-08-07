package com.test.whlie;

/**
 * @author ming.li
 * @date 2023/3/16 18:50
 */
public class Demo1 {
    private static volatile boolean flag=true;
    public static void main(String[] args)throws Exception {
        while (flag){
            System.out.println(1);
            Thread.sleep(3000);
            flag=false;
            System.out.println(2);
        }

    }
}
