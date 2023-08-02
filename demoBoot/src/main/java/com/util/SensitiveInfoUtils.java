package com.util;

import com.bean.TelInfo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ming.li
 * @date 2023/8/1 16:20
 */
public class SensitiveInfoUtils {

    // 全电正则表达式
    private static final String QD_REGEX = "(\\d{4})\\d{12}(\\d{4})";
    // 手机号码正则表达式
    private static final String PHONE_REGEX = "(\\d{3})\\d{4}(\\d{4})";
    // 身份证号码正则表达式
    private static final String ID_CARD_REGEX = "(\\d{4})\\d{10}(\\w{4})";
    //是否为全中文
    private static final String CHINESE_REGEX = "[\\u4e00-\\u9fa5]+";
    //身份证
    private static final String ID_CARD="(^\\d{15}$)|(^\\d{17}([0-9]|X)$)";
    //是否为纯数字发票号等
    private static final String INVOICE_REGEX="[0-9]{1,}";
    /**
     * @param str 待处理字符串
     * @return 处理后的字符串
     */
    public static String desensitize(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        int length = str.length();
        // 替换手机号码
        Pattern phonePattern = Pattern.compile(PHONE_REGEX);
        Matcher phoneMatcher = phonePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (phoneMatcher.find() && length == 11) {
            System.out.println("phoneMatcher");
            phoneMatcher.appendReplacement(sb, "$1****$2");
            phoneMatcher.appendTail(sb);
            return sb.toString();
        }
        // 替换发票号码
        Pattern invoicePattern = Pattern.compile(INVOICE_REGEX);
        Matcher invoiceMatcher = invoicePattern.matcher(str);
        sb.setLength(0); // 清空 StringBuffer，用于存储结果
        while (invoiceMatcher.find() && length >= 8 && length <= 12) {
            System.out.println("invoiceMatcher");
            invoiceMatcher.appendReplacement(sb, "$1****");
            invoiceMatcher.appendTail(sb);
            return sb.toString();
        }
        // 替换身份证号码
        Pattern idCardPattern = Pattern.compile(ID_CARD_REGEX);
        Matcher idCardMatcher = idCardPattern.matcher(str);
        sb.setLength(0); // 清空 StringBuffer，用于存储结果
        while (idCardMatcher.find() && length == 18) {
            System.out.println("idCardMatcher");
            idCardMatcher.appendReplacement(sb, "$1**********$2");
            idCardMatcher.appendTail(sb);
            return sb.toString();
        }

        // 替换全电
        Pattern qdPattern = Pattern.compile(QD_REGEX);
        Matcher qdMatcher = qdPattern.matcher(str);
        sb.setLength(0); // 清空 StringBuffer，用于存储结果
        while (qdMatcher.find() && length == 20) {
            System.out.println("qdMatcher");
            qdMatcher.appendReplacement(sb, "$1************$2");
            qdMatcher.appendTail(sb);
            return sb.toString();
        }
        // 替换全文字
        Pattern chinesePattern = Pattern.compile(CHINESE_REGEX);
        Matcher chineseMatcher = chinesePattern.matcher(str);
        sb.setLength(0); // 清空 StringBuffer，用于存储结果
        while (chineseMatcher.find()) {
            if (length <= 4) {
                return nameDesensitization(str);
            }
            System.out.println("chineseMatcher");

            chineseMatcher.appendReplacement(sb, "$1************$2");
            chineseMatcher.appendTail(sb);
            return sb.toString();
        }
        return str;
    }

    public static String nameDesensitization(String name) {
        if (!StringUtils.isBlank(name)) {
            return name;
        }
        char[] sArr = name.toCharArray();
        if (sArr.length == 2) {
            return sArr[0] + "**";
        } else if (sArr.length > 2) {
            for (int i = 1; i < sArr.length; i++) {
                sArr[i] = '*';
            }
            return new String(sArr);
        }
        return name;
    }

    public static String dataDesensitization(int start, int end, String value) {
        char[] chars;
        int i;
        if (start >= 0 && end + 1 <= value.length()) {
            chars = value.toCharArray();

            for (i = start; i < chars.length && i < end ; i++) {
                chars[i] = '*';
            }

            return new String(chars);
        } else if (start >= 0 && end >= value.length()) {
            chars = value.toCharArray();
            for (i = start; i < chars.length; i++) {
                chars[i] = '*';
            }

            return new String(chars);
        } else {
            return value;
        }
    }

    public static String identify(String str) {
        String DETECTION_PHONE = "(13[0-9]{9}|14[0-14-9]{9}|15[0-35-9]{9}|16[2567]{9}|17[0-8]{9}|18[0-9]{9}|19[0-35-9]{9})";
        String DETECTION_PHONE_NUMBER = "\\d{3,4}-\\d{7,8}";
        String regex2 = "(([1][3-9][\\d]{9})|(0\\d{2,4}-\\d{7,8})) | (^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$*)";
        System.out.println(dataDesensitization(0,1,str));
        Pattern pattern = Pattern.compile(DETECTION_PHONE);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            int start = matcher.start();
            str = dataDesensitization(matcher.start() + 3, matcher.start() + 7, str);
            if (start > 6) {
                str = dataDesensitization(2, start - 4-1, str);
            }
            return str;
        }
        //存在座机号直接处理
        Pattern phoneNumberPattern = Pattern.compile(DETECTION_PHONE_NUMBER);
        Matcher phoneNumberMatcher = phoneNumberPattern.matcher(str);
        while (phoneNumberMatcher.find()) {
            int start = phoneNumberMatcher.start();
            String phoneNumber ="0,4";
            if (StringUtils.isNotBlank(phoneNumber)){
                String[] split = phoneNumber.split(",");
                String substring = str.substring(start);
                int i = substring.indexOf("-")+1;
                str = dataDesensitization(phoneNumberMatcher.start() + Integer.valueOf(split[0])+i, phoneNumberMatcher.start() + Integer.valueOf(split[1])+i, str);
            }
            if (start > 6) {
                str = dataDesensitization(2, start - 4-1, str);
            }
            return str;
        }

        //存在身份证直接处理
        Pattern idCardPattern = Pattern.compile(ID_CARD);
        Matcher idCardMatcher = idCardPattern.matcher(str);
        while (idCardMatcher.find()) {
            System.out.println("身份证");
            int start = idCardMatcher.start();
            String idCard ="4";
            if (StringUtils.isNotBlank(idCard)){
                str = dataDesensitization(idCardMatcher.start() + Integer.valueOf(idCard), idCardMatcher.end()-Integer.valueOf(idCard), str);
            }
            return str;
        }
        //存在发票直接处理
        Pattern invoicePattern = Pattern.compile(INVOICE_REGEX);
        Matcher invoiceMatcher = invoicePattern.matcher(str);
        while (invoiceMatcher.find()) {
            System.out.println("发票啥的");
            int start = invoiceMatcher.start();
            String idCard ="2";
            if (StringUtils.isNotBlank(idCard)){
                str = dataDesensitization(invoiceMatcher.start() + Integer.valueOf(idCard), invoiceMatcher.end()-Integer.valueOf(idCard), str);
            }
            return str;
        }
        return str;
    }
}
