package com.test.lambda;

/**
 * @author ming.li
 * @date 2023/6/13 10:57
 */
public class Demo5 {
    public static void main(String[] args) {
        //实现多个参数，一个返回值的接口
        //对一个静态方法的引用，语法：类::静态方法
        //相当于我的Test5接口只有一个方法test，然后实现是调用Calculator的calculate静态方法
        Test5 test5 = Calculator::calculate;
        System.out.println(test5.test(4,5));
    }
}

class Calculator{
    public static int calculate(int a,int b ){
        // 稍微复杂的逻辑：计算a和b的差值的绝对值
        if (a > b) {
            return a - b;
        }
        return b - a;
    }
}
interface Test5{
    int test(int a,int b);
}
