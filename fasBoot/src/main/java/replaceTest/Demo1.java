package replaceTest;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public class Demo1 {

    public static void main(String[] args) {
        String text1="项。";
        String string="测试<>《》！*(^)$%~!@#$…&%￥—+=、。，；‘’“”：·`文本";
        System.out.println(text1.replaceAll("\\pP|\\pS", ""));
        text1=processSpecStr(text1);
        text1 = text1.replaceAll("[^\\d.]", "");
        System.out.println(removePoint(text1));
    }
    private static String processSpecStr(String text) {
        return text.replaceAll("[|lI\\[\\]\\{\\}\\【\\】]", "1");
    }

    public static String removePoint(String strNum) {
        int length = strNum.length();
        if (length <= 0|| ".00".equals(strNum)) {
            return "0.00";
        }
        if (length > 1) {
            //首尾为小数点，去除
            if (strNum.charAt(0) == '.') {
                strNum = "0"+strNum;
                length++;
            }
            if (strNum.lastIndexOf(".") == length - 1) {
                strNum = strNum.substring(0, length - 1);
            }
        }
        //存在多个小数点则取第一个小数点到其后两位即可
        int pointNum = StringUtils.countMatches(strNum, ".");
        if (pointNum > 1) {
            int lastPointIndex = strNum.indexOf(".");
            String strNum1 = strNum.substring(0,lastPointIndex+1);
            String strNum2 = strNum.substring(lastPointIndex+1).replace(".", "");
            strNum = strNum1 + strNum2;
        }
        pointNum = StringUtils.countMatches(strNum, ".");
        if (pointNum == 1) {
            int gap = strNum.length() - 1 - strNum.lastIndexOf(".");
            if (gap > 2) {
                strNum = strNum.substring(0, strNum.lastIndexOf(".") + 3);
            }
            if (gap < 2) {
                strNum = strNum + "0";
            }
        }
        if (pointNum < 1) {
            strNum = strNum + ".00";
        }
        strNum = new BigDecimal(strNum).toString();
        //TODO
        return strNum;
    }
}
