package com.test.stringTest;

/**
 * @Author ming.li
 * @Date 2025/4/15 16:02
 * @Version 1.0
 */
import java.nio.charset.StandardCharsets;

public class HexToText {
    public static void main(String[] args) {
        String hexString = "0xc341088";
        // 去掉前缀 "0x"
        if (hexString.startsWith("0x")) {
            hexString = hexString.substring(2);
        }

        // 如果长度为奇数，在前面补零
        if (hexString.length() % 2 != 0) {
            hexString = "0" + hexString;
        }

        // 将十六进制字符串转换为字节数组
        byte[] bytes = hexToBytes(hexString);

        // 使用 UTF-8 编码将字节数组转换为字符串
        String text = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("转换后的文字: " + text);
    }

    public static byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }
}
