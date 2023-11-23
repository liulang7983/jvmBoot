package model.singleton;

/**
 * @author ming.li
 * @date 2023/11/22 14:30
 */
public class Singleton {
    //单例模式
    // 使用volatile关键字防止指令重排序
    private static volatile Singleton instance;

    private Singleton() {}

    // 提供全局访问点
    public static Singleton getInstance() {
        // 第一次检查
        if (instance == null) {
            // 加锁
            synchronized (Singleton.class) {
                // 第二次检查
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

