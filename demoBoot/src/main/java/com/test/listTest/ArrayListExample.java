package com.test.listTest;

import java.util.ArrayList;

/**
 * @Author ming.li
 * @Date 2024/9/9 16:25
 * @Version 1.0
 */
public class ArrayListExample {
    public static void main(String[] args) {
        // 创建一个初始容量为10的ArrayList
        ArrayList<Integer> list = new ArrayList<>(10);

        // 添加元素，直到触发第一次扩容
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        // 打印当前ArrayList的容量和扩容阈值
        System.out.println("Initial capacity: " + list.size());
        System.out.println("Threshold before first resize: " + list.size() + " (additional " + (list.size() - 10) + " elements)");

        // 继续添加元素，直到触发第二次扩容
        for (int i = 10; i < 15; i++) {
            list.add(i);
        }

        // 打印扩容后的容量和新的扩容阈值
        System.out.println("Capacity after first resize: " + list.size());
        System.out.println("Threshold before second resize: " + list.size() + " (additional " + (list.size() - 15) + " elements)");

        // 添加更多元素以触发第二次扩容
        for (int i = 15; i < 20; i++) {
            list.add(i);
        }

        // 打印最终的容量和扩容阈值
        System.out.println("Capacity after second resize: " + list.size());
        System.out.println("Threshold after second resize: " + list.size() + " (additional " + (list.size() - 20) + " elements)");
    }
}
