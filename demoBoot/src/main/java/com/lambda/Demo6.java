package com.lambda;

/**
 * @author ming.li
 * @date 2023/6/13 11:02
 */
public class Demo6 {
    public static void main(String[] args) {
        //对非静态方法的引用，需要使用对象来完成
        //相当于我的Test5接口只有一个方法test，然后方法实现是new了Calculator这个类再调用calculate方法
        Test6 test6= new Calculator()::calculate;
        System.out.println(test6.calculate(2, 3));
    }
    private static class Calculator{
        public int calculate(int a, int b) {
            return a > b ? a - b : b - a;
        }
    }
}
interface Test6{
    int calculate(int a,int b);
}
