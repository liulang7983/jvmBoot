package com.md5Test;


import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author ming.li
 * @Date 2024/6/6 19:09
 * @Version 1.0
 */
public class KeyPairUtil {

    /**
     * 非对称加密算法的密钥长度，DH算法的默认密钥长度是1024，
     * 密钥长度必须是64的倍数，在512到65536位之间。
     */
    private static final int RSA_KEY_SIZE = 2048;
    /**
     * 公钥
     */
    public static final String PUBLIC_KEY = "publicKey";
    /**
     * 私钥
     */
    public static final String PRIVATE_KEY = "privateKey";

    /**
     * @Description: 生成非对称加密的密钥对
     * @Param: []
     * @return: java.util.Map<java.lang.String, java.security.interfaces.RSAKey>
     * @Author: dingyn
     * @Date: 2021/9/3 18:52
     */
    public static Map<String, String> generateKeyPair() throws Exception {

        Map<String, String> keyPairMap = new HashMap<>();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(SignUtil.RSA_ALGORITHM);
        //初始化密钥生成器
        keyPairGenerator.initialize(RSA_KEY_SIZE);
        //生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //甲方公钥
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        //甲方私钥
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

        byte[] publicKeyBytes = rsaPublicKey.getEncoded();
        byte[] privateKeyBytes = rsaPrivateKey.getEncoded();

        //字节数组转换成base64字符串
        String publicKey = Base64.getMimeEncoder().encodeToString(publicKeyBytes);
        String privateKey = Base64.getMimeEncoder().encodeToString(privateKeyBytes);

        keyPairMap.put(PUBLIC_KEY, publicKey);
        keyPairMap.put(PRIVATE_KEY, privateKey);
        return keyPairMap;
    }

}

