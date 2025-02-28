package com.test.listTest;

/**
 * @Author ming.li
 * @Date 2025/2/24 17:17
 * 通过反射拿到扩容后容积大小
 * @Version 1.0
 */
import java.lang.reflect.Field;
import java.util.ArrayList;

public class ArrayListCapacityTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        // 创建初始容量为 9 的 ArrayList
        ArrayList<Integer> list = new ArrayList<>(9);
        System.out.println("初始容量: " + getCapacity(list));

        // 模拟添加元素，触发扩容
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println("扩容一次后的容量: " + getCapacity(list));
    }

    // 通过反射获取 ArrayList 的容量
    private static int getCapacity(ArrayList<?> list) throws NoSuchFieldException, IllegalAccessException {
        Field field = ArrayList.class.getDeclaredField("elementData");
        field.setAccessible(true);
        return ((Object[]) field.get(list)).length;
    }
}
