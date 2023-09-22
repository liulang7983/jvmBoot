package com.intTest;

/**
 * @author ming.li
 * @date 2023/9/22 9:43
 */
public class Demo4 {
    //数组是引用传递
    public static void main(String[] args) {
        int[] arr=new int[]{1,23,4};
        System.out.println(arr[0]);
        addArr(arr);
        System.out.println(arr[0]);
    }
    public static void addArr(int [] arr){
        arr[0]=5;
    }
}
