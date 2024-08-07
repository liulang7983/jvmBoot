package com.test.listTest;

public class Demo7 {
    public static void main(String[] args) {
        int[] arr={1,2,3,4};
        int length = arr.length;
        int i1 = length - 1;
        for (int i = length - 1; i >=0 ; i--) {
            System.out.println(arr[i]);
        }
        System.out.println("---");
        for (int i = 0; i <length ; i++) {
            System.out.println(arr[i]);
        }
        System.out.println(arr);
    }
}
