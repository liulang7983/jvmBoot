package numberTest;

/**
 * @Author ming.li
 * @Date 2025/6/25 13:54
 * @Version 1.0
 */
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AmountConverter {

    public static void main(String[] args) {
        String str1 = "合计:壹仟元整(￥:1000元，其中救助基金20.00元)";
        String str2 = "合计玖仟壹佰零柒元玖角捌分(￥：9107.98元(不含税报废:8592.42,增值税:515.55元))";
        String str3 = "合计:壹万伍仟元整";
        String str4 = "合计:伍仟元整(￥：5000元)";
        String str5  = "合计:壹仟元整(￥:1000元，其中救助基金（2.00%）￥：20.00元)（不含税保费:943.40元,增值税:56.60元）";

        System.out.println(extractAmount(str1));  // 输出1000.0
        System.out.println(extractAmount(str2));  // 输出9107.98
        System.out.println(extractAmount(str3));  // 输出15000.0
        System.out.println(extractAmount(str4));  // 输出5000.0
        System.out.println(extractAmount(str5));  // 输出5000.0
    }

    /**
     * 提取或转换金额为数字金额
     * @param input 输入字符串，可能包含大写金额或带￥的数字金额
     * @return 提取或转换后的BigDecimal金额，若无法处理返回null
     */
    public static BigDecimal extractAmount(String input) {
        // 优先提取￥后的数字金额
        BigDecimal extracted = extractFromPattern(input);
        if (extracted != null) {
            return extracted;
        }
        // 若提取失败，尝试将大写金额转换为数字
        return chineseToDecimal(input);
    }

    /**
     * 通过正则表达式提取￥后的数字金额
     */
    private static BigDecimal extractFromPattern(String input) {
        // 匹配￥:或￥：后的数字（可能包含整数或小数）及元
        Pattern pattern = Pattern.compile("￥[:：]\\s*([\\d\\.]+)\\s*元");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String numberStr = matcher.group(1);
            try {
                return new BigDecimal(numberStr);
            } catch (NumberFormatException e) {
                return null; // 数字格式错误，返回null
            }
        }
        return null; // 未找到匹配的￥金额
    }

    /**
     * 将大写金额字符串转换为数字金额
     */
    private static BigDecimal chineseToDecimal(String chineseAmount) {
        // 大写数字到数值的映射
        Map<Character, Integer> digitMap = new HashMap<>();
        digitMap.put('零', 0);
        digitMap.put('壹', 1);
        digitMap.put('贰', 2);
        digitMap.put('叁', 3);
        digitMap.put('肆', 4);
        digitMap.put('伍', 5);
        digitMap.put('陆', 6);
        digitMap.put('柒', 7);
        digitMap.put('捌', 8);
        digitMap.put('玖', 9);

        // 单位到数值的映射（按从大到小排列）
        Map<Character, Integer> unitMap = new LinkedHashMap<>();
        unitMap.put('亿', 100000000);
        unitMap.put('万', 10000);
        unitMap.put('仟', 1000);
        unitMap.put('佰', 100);
        unitMap.put('拾', 10);
        unitMap.put('元', 1); // 元是整数部分的结束

        BigDecimal result = BigDecimal.ZERO;
        int length = chineseAmount.length();
        int i = 0;

        // 处理整数部分（元之前）
        while (i < length && !chineseAmount.startsWith("元", i)) {
            char c = chineseAmount.charAt(i);
            if (digitMap.containsKey(c)) {
                int num = digitMap.get(c);
                // 检查下一个字符是否是单位
                if (i + 1 < length && unitMap.containsKey(chineseAmount.charAt(i + 1))) {
                    char unit = chineseAmount.charAt(i + 1);
                    int value = unitMap.get(unit);
                    result = result.add(BigDecimal.valueOf(num).multiply(BigDecimal.valueOf(value)));
                    i += 2; // 跳过数字和单位
                } else {
                    // 无后续单位，视为个位（如"伍元"中的"伍"）
                    result = result.add(BigDecimal.valueOf(num));
                    i++;
                }
            } else {
                // 非数字字符（如"整"），跳过
                i++;
            }
        }

        // 处理小数部分（角和分，在元之后）
        int yuanIndex = chineseAmount.indexOf('元');
        if (yuanIndex != -1 && yuanIndex < length - 1) {
            // 提取角和分部分
            String decimalPart = chineseAmount.substring(yuanIndex + 1);
            // 处理角（0.1）
            int jiaoIndex = decimalPart.indexOf('角');
            if (jiaoIndex != -1 && jiaoIndex > 0) {
                char jiaoChar = decimalPart.charAt(0);
                if (digitMap.containsKey(jiaoChar)) {
                    result = result.add(BigDecimal.valueOf(digitMap.get(jiaoChar)).multiply(BigDecimal.valueOf(0.1)));
                }
            }
            // 处理分（0.01）
            int fenIndex = decimalPart.indexOf('分');
            if (fenIndex != -1 && fenIndex > 0) {
                // 分可能在角的后面（如"玖角捌分"）或直接跟元（如"伍分"）
                int start = (fenIndex > jiaoIndex) ? jiaoIndex + 1 : 0;
                if (start < fenIndex) {
                    char fenChar = decimalPart.charAt(start);
                    if (digitMap.containsKey(fenChar)) {
                        result = result.add(BigDecimal.valueOf(digitMap.get(fenChar)).multiply(BigDecimal.valueOf(0.01)));
                    }
                }
            }
        }

        return result;
    }
}
