package com.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @Author jiajun.xiong
 * @Date 2021/1/8 14:14
 * @Version 1.0
 */
public class IasUtils {
    private static final Logger logger = LoggerFactory.getLogger(IasUtils.class);

    /**
     * 获取一个随机的UUID，32位
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    /**
     * 返回一个十位的随机数
     *
     * @return
     */
    public static String randomNine() {
        return uuid().substring(2, 12);
    }

    public static Map<String, Object> entityToMap(Object obj) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            Class<?> clazz = obj.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(obj);
                map.put(fieldName, value);
            }
        } catch (IllegalAccessException var8) {
            return map;
        }

        return map;
    }

    /**
     * 读取资源文件
     *
     * @param fileName:资源文件名
     * @return
     */
    public static String readResourceFileToString(String fileName, String cs) {
        StringBuilder sb = new StringBuilder();
        try (InputStream is = IasUtils.class.getClassLoader().getResourceAsStream(fileName);
             InputStreamReader isr = new InputStreamReader(is, cs)) {
            try (BufferedReader reader = new BufferedReader(isr)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line.trim());
                }
            }
        } catch (IOException e) {
            logger.error("获取文件出错:", e);
        }
        return sb.toString();
    }

    /**
     * 格式化敏感字符串
     */
    public static String formatSensitiveInfo(String s) {
        if (StringUtils.isEmpty(s)) {
            return s;
        }
        if (s.length() == 0) {
            return "*";
        } else if (s.length() == 2) {
            StringBuilder sbuilder = new StringBuilder();
            sbuilder.append(s.charAt(0));
            sbuilder.append("*");
            return sbuilder.toString();
        } else {
            StringBuilder sbuilder = new StringBuilder();
            sbuilder.append(s.charAt(0));
            for (int i = 1; i < s.length() - 1; i++) {
                sbuilder.append("*");
            }
            sbuilder.append(s.charAt(s.length() - 1));
            return sbuilder.toString();
        }
    }

    public static void base64ToFile(String base64, String filePath, String fileName) {
        File file = null;
        //创建文件目录
        File dir = new File(filePath);
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        byte[] bytes = Base64.getDecoder().decode(base64);
        file = new File(filePath + System.getProperty("file.separator") + fileName);
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            bos.write(bytes);
        } catch (IOException e) {
            logger.error("base64ToFile 错误 :", e);
        }
    }

    /**
     * 格式化数字类型，将数字以外的字符去掉
     *
     * @param s
     * @return
     */
    public static String formatDigital(String s) {
        s = s.replaceAll("[^0-9.%]", "");
        return s;
    }

    /**
     * 格式化货币
     *
     * @param s
     * @return
     */
    public static String formatCurrency(String s) {
        if (Pattern.matches("[^\\u4e00-\\u9fa5]+", s)) {
            /*为非中文小写金额*/
            int index = s.indexOf("Y");
            s = s.substring(index + 1);
            s = s.replaceAll("。", ".");
            s = s.replaceAll("，", ",");
            s = s.replaceAll("o", "0");
            s = s.replaceAll("[^0-9.,+\\-]", "");
            return s;
        } else {
            /*大写金额，直接返回*/
            return s;
        }


    }

    /**
     * 格式化数字类型，将数字以外的字符去掉
     *
     * @param s
     * @return
     */
    public static String formatDigitalAndLetter(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "");
        return s;
    }


    /**
     * 大写货币格式文本
     *
     * @param str
     * @return
     */
    public static String dxMoneyChars = "亿仟佰万拾零壹贰叁肆伍陆柒捌玖拾角分圆整";

    /**
     * 小货币格式文本
     *
     * @param str
     * @return
     */
    public static boolean isMoney(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }

        if (!str.matches(".*\\d.*")) {
            return false;
        }

        if (str.length() > 13) {
            return false;
        }

        str = str.replace("￥", "");
        str = str.replace(",", "");
        str = str.replace(">", "");
        str = str.replace("：", "");
        for (int i = 1; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '+' || c == '-') {
                return false;
            }
        }

        if (!str.contains(".") && !str.contains(",")) {
            return false;
        }
        boolean isMoney = true;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ((c >= '0' && c <= '9') || c == ',' || c == '.' || c == '+' || c == '-') {

            } else {
                isMoney = false;
            }
        }

        return isMoney;
    }

    public static String formDate(String str) {

        if (StringUtils.isEmpty(str)) {
            return str;
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = sdf1.parse(str);
        } catch (ParseException e) {
            logger.info("日期格式化错误！");
            try {
                date = sdf2.parse(str);
            } catch (ParseException e1) {
                logger.error("日期格式化错误！", e1);
                return str;
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(date);
    }


    public static boolean isMoney2(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }

        if (!str.matches(".*\\d.*")) {
            return false;
        }

        if (str.length() > 13) {
            return false;
        }
        str = str.replace("元", "");
        str = str.replace("￥", "");
        str = str.replace(",", "");
        str = str.replace(">", "");
        str = str.replace("：", "");
        for (int i = 1; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '+' || c == '-') {
                return false;
            }
        }


        boolean isMoney = true;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ((c >= '0' && c <= '9') || c == ',' || c == '.' || c == '+' || c == '-') {

            } else {
                isMoney = false;
            }
        }

        return isMoney;
    }

    public static boolean isDXMoney(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }


        int moneycharNum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (dxMoneyChars.contains(str.charAt(i) + "")) {
                moneycharNum++;
            }
        }
        int dba = (int) ((moneycharNum / (str.length() * 1.0)) * 100);
        return dba > 60 ? true : false;
    }


    /**
     * 覆盖from对象上的所有属性到to对象上,如果属性为null则不覆盖
     *
     */
    public static void copyPropertiesExclude(Object source, Object target) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null) {
                PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null &&
                            ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object value = readMethod.invoke(source);
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            if (value != null && StringUtils.isNotEmpty(value.toString())){
                                writeMethod.invoke(target, value);
                            }
                        }
                        catch (Throwable ex) {
                            throw new FatalBeanException(
                                    "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                        }
                    }
                }
            }
        }
    }

    public static String formatStr(String str) {
        String formatStr = null;
        if (StringUtils.isNotBlank(str)) {
            formatStr = str.replace("（", "(");
            formatStr = formatStr.replace("）", ")");
            formatStr = formatStr.replace("，", ",");
            formatStr = formatStr.replace("；", ";");
            formatStr = formatStr.replace("：", ":");
        }
        return formatStr;
    }

}
