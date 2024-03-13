package regexText;

/**
 * @Author ming.li
 * @Date 2024/3/12 10:33
 * @Version 1.0
 */
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        String str1 = "125.2万元";
        String REGEX = "[^(0-9).]";
        String trim = Pattern.compile(REGEX).matcher(str1).replaceAll("").trim();
        System.out.println(trim);
    }

}
