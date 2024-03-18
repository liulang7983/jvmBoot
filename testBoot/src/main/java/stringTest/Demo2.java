package stringTest;

/**
 * @Author ming.li
 * @Date 2024/3/18 10:03
 * @Version 1.0
 */
public class Demo2 {
    public static void main(String[] args) {
        String str = "Hello\nWorld";
        System.out.println(str);
        str = str.replaceAll("\\r|\\n", "");
        System.out.println(str);  // 输出：HelloWorld
    }
}
