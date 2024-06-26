package com.md5Test;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;

/**
 * @Author ming.li
 * @Date 2024/6/6 19:06
 * @Version 1.0
 */
public class SignUtil {

    /**
     * 非对称加密算法，
     * 甲乙双方使用不同的密钥加密解密数据，甲方生成一对密钥，将公钥发布给多个乙方，自己保留私钥。
     * 用法一：
     * 乙方使用公钥加密数据，发给甲方，甲方使用私钥解密数据
     * 用法二：
     * 数字签名，甲方使用私钥对数据进行签名，发给乙方，乙方使用公钥解密数据并验证签名是否是甲方，验证数据是否被篡改过
     */
    public static final String RSA_ALGORITHM = "RSA";

    /**
     * 数字签名算法。
     * JDK只提供了MD2withRSA, MD5withRSA, SHA1withRSA，其他的算法需要第三方包才能支持。
     * 数字签名是基于RSA非对称加密实现的，
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * @Description: 用私钥对数据签名
     * @Param: [data 待签名数据, privateKeyBytes 从私钥文件中读取到的字节数组]
     * @return: java.lang.String
     * @Author: dingyn
     * @Date: 2021/9/3 18:53
     */
    public static String sign(byte[] data, String privateKeyStr) throws Exception {
        // 解密由base64编码的私钥
        byte[] keyBytes = base64Str2ByteArr(privateKeyStr);
        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        //指定加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        //获取私钥匙对象
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(data);
        return byteArr2Base64Str(signature.sign());
    }

    /**
     * @Description: 验证签名
     * @Param: [data 待验签数据, publicKeyStr 公钥字符串, sign 签名]
     * @return: boolean
     * @Author: dingyn
     * @Date: 2021/9/3 18:53
     */
    public static boolean verify(byte[] data, String publicKeyStr, String sign) throws Exception {

        // 解密由base64编码的公钥
        byte[] keyBytes = base64Str2ByteArr(publicKeyStr);
        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        //指定加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        // 取公钥匙对象
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(data);
        // 验证签名是否正常
        return signature.verify(base64Str2ByteArr(sign));
    }

    /**
     * @Description: 字节数组加密成base64字符串
     * @Param: [byteArr]
     * @return: java.lang.String
     * @Author: dingyn
     * @Date: 2021/9/3 18:57
     */
    public static String byteArr2Base64Str(byte[] byteArr) {
        return Base64.getMimeEncoder().encodeToString(byteArr);
    }

    /**
     * @Description: base64字符串解密成字节数组
     * @Param: [base64Str]
     * @return: byte[]
     * @Author: dingyn
     * @Date: 2021/9/3 18:57
     */
    public static byte[] base64Str2ByteArr(String base64Str) throws Exception {
        return Base64.getMimeDecoder().decode(base64Str);
    }

    public static void main(String[] args) throws Exception {
        //生成钥匙对
        Map<String, String> keyPair = KeyPairUtil.generateKeyPair();
        String privateKey = keyPair.get(KeyPairUtil.PRIVATE_KEY);
        String publicKey = keyPair.get(KeyPairUtil.PUBLIC_KEY);

        String str = "/**\n" +
                "     * @Description: 验证签名\n" +
                "     * @Param: [data 待验签数据, publicKey 公钥, sign]\n" +
                "     * @return: boolean\n" +
                "     * @Author: dingyn\n" +
                "     * @Date: 2021/9/3 18:56\n" +
                "     */\n" +
                "    public static boolean verify(byte[] data, PublicKey publicKey, String sign) throws Exception {\n" +
                "\n" +
                "        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);\n" +
                "        signature.initVerify(publicKey);\n" +
                "        signature.update(data);\n" +
                "        //base64字符串解密成字节数组\n" +
                "        byte[] decode = Base64.getMimeDecoder().decode(sign);\n" +
                "        // 验证签名是否正常\n" +
                "        return signature.verify(decode);\n" +
                "    }";

        //对字符串进行md5加密
        String md5Str = MD5Util.string2MD5(str);
        System.out.println("加密:"+md5Str);
        //对字符串进行签名
        String signStr = sign(md5Str.getBytes(), privateKey);
        System.out.println("加密签名:"+signStr);

        //验证签名
        boolean verify = SignUtil.verify(md5Str.getBytes(), publicKey, signStr);
        System.out.println(verify);
    }

}

