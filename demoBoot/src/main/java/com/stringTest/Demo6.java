package com.stringTest;

/**
 * @author ming.li
 * @date 2023/6/28 9:28
 */
public class Demo6 {
    public static void main(String[] args) {
        String s0="zhuge";
        String s1="zhuge";
        String s2="zhu" + "ge";
        System.out.println( s0==s1 ); //true
        System.out.println( s0==s2 ); //true
        /*因为例子中的 s0和s1中的”zhuge”都是字符串常量，它们在编译期就被确定了，所以s0==s1为true；
        而”zhu”和”ge”也都是字符串常量，当一个字 符串由多个字符串常量连接而成时，它自己肯定也是字符串常量，
        所以s2也同样在编译期就被优化为一个字符串常量"zhuge"，所以s2也是常量池中” zhuge”的一个引用。所以我们得出s0==s1==s2*/
    }
}
