package com.listTest;

/**
 * @author ming.li
 * @date 2023/7/4 16:22
 */
public class Demo2 {
    public static void main(String[] args) {
        long[][] longs= new long[6][];
        for (int i = 0; i < 6; i++) {
            longs[i] = new long[6];
            for (int j = 0; j < 6; j++) {
                longs[i][j] = 1L;
            }
        }
        for (int i = 0; i < longs.length; i++) {
            long[] aLong = longs[i];
            System.out.println("====================i是:"+i);
            for (int j = 0; j < aLong.length; j++) {
                if (j==2){
                    continue;
                }
                System.out.println("j是："+j);
            }
        }
    }
}
