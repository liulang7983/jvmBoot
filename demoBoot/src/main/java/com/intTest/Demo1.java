package com.intTest;

/**
 * @author ming.li
 * @date 2023/7/26 15:28
 */
public class Demo1 {
    public static void main(String[] args) {
        int s1=300;
        Integer s2=300;
        Integer s3=new Integer(300);
        Integer s4=new Integer(300);
        if (s1==s2){
            System.out.println("S1S2相等");
        }else {
            System.out.println("S1S2不相等");
        }
        if (s1==s3){
            System.out.println("S1S3相等");
        }else {
            System.out.println("S1S3不相等");
        }
        if (s3.equals(s2)){
            System.out.println("S3S2值相等");
        }else {
            System.out.println("S3S2值不相等");
        }
        if (s3==s2){
            System.out.println("S3S2相等");
        }else {
            System.out.println("S3S2不相等");
        }
        if (s3.equals(s4)){
            System.out.println("S3S4相等");
        }else {
            System.out.println("S3S4不相等");
        }
    }
}
