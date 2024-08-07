package com.test.lambda;

/**
 * @author ming.li
 * @date 2023/6/13 10:44
 */
public class Demo4 {
    //⼀个接口中，要求实现类必须实现的抽象方法，有且只有⼀个！这样的接口，就是函数式接口
    //当出参是有一行时，可以省略大括号和return
    public static void main(String[] args) {
        Test4 test = (a,b) -> a+b;
        int age = test.test(18,2);
        System.out.println(age);
    }


}

//有参 有返回值
interface Test4 {
    public int test(int a,int b);
}

