package regexText;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author ming.li
 * @Date 2024/3/13 19:31
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args) {
        String s="(含13%增值税123";
        String REHGEX="([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])";
        Matcher matcher = Pattern.compile(REHGEX).matcher(s);
        while (matcher.find()){
            String trim = matcher.group();
            System.out.println(trim);
        }

        System.out.println(getNumberFromString("sdsad500sd200.35dd"));
        System.out.println(getNumberFromString(s));
    }

    public static   List<Double>  getNumberFromString(String text) {
        List<Double> numbers=new ArrayList<>();
        String pattern="([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher matcher = r.matcher(text);
        Set<String> matchStrs = new HashSet<>();
        while (matcher.find()) {
            System.out.println(matcher.group());
            //获取当前匹配的值
            matchStrs.add(matcher.group());
        }
        if(matchStrs.size()==0){
            return numbers;
        }
        for(String s:matchStrs){
            try{
                double number=Double.parseDouble(s);
                numbers.add(number);
            }catch(Exception e){
                continue;
            }
        }
        return numbers;
    }


}
