package com.lambda;

/**
 * @author ming.li
 * @date 2023/6/13 10:44
 */
public class Demo2 {
    //⼀个接口中，要求实现类必须实现的抽象方法，有且只有⼀个！这样的接口，就是函数式接口
    public static void main(String[] args) {
        Test2 test = (name, age) -> {
            System.out.println(name + age + "岁了！");
            return age + 1;
        };
        int age = test.test("小新", 18);
        System.out.println(age);
    }


}

//有参 有返回值
interface Test2 {
    public int test(String name, int age);
}

