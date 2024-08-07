package com.test.lambda;

/**
 * @author ming.li
 * @date 2023/6/13 10:31
 */
public class Demo1 {
    public static void main(String[] args) {
        //使用lambda表达式实现接口，正常只能有一个接口
        Test test = () -> {
            System.out.println("test");
        };
        test.test();
    }
}

interface Test{
    public void test();
}
