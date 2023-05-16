package com.jvm;

/**
 * @author ming.li
 * @date 2023/5/16 11:50
 */
public class StackOverflowTest {

    /*
    -Xmx64M -Xms64M -Xss2M修改Xss可以发现count的值是不同的，说明线程栈里能分配的栈帧会越少，但是对JVM整体来说能开启的线程数会更多
     */
    static int count = 0;

    static void redo() {
        count++;
        redo();
    }

    public static void main(String[] args) {
        try {
            redo();
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println(count);
        }
    }
}
