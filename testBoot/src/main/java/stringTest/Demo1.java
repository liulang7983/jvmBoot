package stringTest;

public class Demo1 {
    public static void main(String[] args) {
        String s1 = "a"; // 懒惰的
        String s2 = "b";
        String s3 = "ab";
        String s4 = s1+s2;
        System.out.println(s3 == s4);  // false
    }
}
