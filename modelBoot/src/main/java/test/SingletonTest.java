package test;

import model.singleton.Singleton;

/**
 * 单例模式
 * @author ming.li
 * @date 2023/11/22 14:30
 */
public class SingletonTest {
    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1==instance2);
    }
}
