package com.stringTest;

/**
 * @author ming.li
 * @date 2023/6/28 9:29
 */
public class Demo7 {
    public static void main(String[] args) {
        String s0="zhuge";
        String s1=new String("zhuge");
        String s2="zhu" + new String("ge");
        System.out.println( s0==s1 );// false
        System.out.println( s0==s2 );// false
        System.out.println( s1==s2 );// false
        /*用new String() 创建的字符串不是常量，不能在编译期就确定，所以new String() 创建的字符串不放入常量池中，它们有自己的地址空间。
        s0还是常量池 中"zhuge”的引用，s1因为无法在编译期确定，所以是运行时创建的新对象”zhuge”的引用，
        s2因为有后半部分 new String(”ge”)所以也无法在编译期确定，所以也是一个新创建对象”zhuge”的引用;明白了这些也就知道为何得出此结果了
*/
    }
}
