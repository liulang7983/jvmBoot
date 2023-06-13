package com.lambda;

/**
 * @author ming.li
 * @date 2023/6/13 10:44
 */
public class Demo3 {
    //⼀个接口中，要求实现类必须实现的抽象方法，有且只有⼀个！这样的接口，就是函数式接口
    //当入参只有一个时，可以省略小括号
    public static void main(String[] args) {
        Test3 test = age -> {
            System.out.println(age + "岁了！");
            return age + 1;
        };
        int age = test.test(18);
        System.out.println(age);
    }


}

//有参 有返回值
interface Test3 {
    public int test(int age);
}

