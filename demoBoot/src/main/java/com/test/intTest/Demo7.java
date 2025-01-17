package com.test.intTest;

import java.util.Arrays;

/**
 * @Author ming.li
 * @Date 2025/1/17 10:15
 * @Version 1.0
 */
public class Demo7 {
    public static void main(String[] args) {
        int[] arr1={1,2};
        int len = arr1.length;
        int[] arr2={0,9};
        arr1 = resizeArray(arr1, len+arr2.length);
        for (int i = 0; i < arr2.length; i++) {
            arr1[len+i]=arr2[i];
        }
        Arrays.sort(arr1);
        for (int i = 0; i < arr1.length; i++) {
            System.out.println(arr1[i]);
        }
    }


    public static int[] resizeArray(int[] original, int newSize) {
        int[] newArray = new int[newSize];
        // 复制原数组到新数组
        System.arraycopy(original, 0, newArray, 0, original.length);
        return newArray;
    }
}
