package com.ipTest;

/**
 * @author ming.li
 * @date 2023/8/29 18:08
 */
public class Demo1 {
    public static void main(String[] args) {
        String ip="127.0.0.1,127.0.0.2";
        System.out.println(ip.split(",")[0]);
        int position = ip.lastIndexOf(",");
        if (position > 0) {
            ip = ip.substring(position+1);
        }
        System.out.println(ip);

    }
}
