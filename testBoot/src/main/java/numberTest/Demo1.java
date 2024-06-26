package numberTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author ming.li
 * @Date 2024/5/6 10:39
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args) {
        String s="11.1";
        System.out.println(isNumeric(s));
        System.out.println(isNumericInt(s));
        System.out.println(isNumeric1(s));
    }

    //是否为数字
    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
    //是否为数字
    public static boolean isNumericInt(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static boolean isNumeric1(String str){
        Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]+");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
