package stringTest;

import java.lang.reflect.Field;

/**
 * @Author ming.li
 * @Date 2025/9/9 10:21
 * @Version 1.0
 */
public class Demo4 {
    public static void main(String[] args)throws Exception {
        String a=new String("abc");
        String b=a;
        a=a+"d";
        //会改变位置
        System.out.println(b==a);
        System.out.println(b);
        //通过反射直接去改原始的那个String
        String a1=new String("abc");
        String b1=a1;
        Field value = a1.getClass().getDeclaredField("value");
        value.setAccessible(true);
        value.set(a1,"abcd".toCharArray());
        System.out.println(b1==a1);
        System.out.println(b1);

    }
}
