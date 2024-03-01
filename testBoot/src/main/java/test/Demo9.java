package test;

import java.net.InetAddress;

public class Demo9 {
    public static void main(String[] args) {


        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            String ip = inetAddress.getHostAddress();
            System.out.println("本机IP地址：" + ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String ss="sso:2c6c3e4f9c7ee3cbfd6f4c42e70c56edfc0189b9c84d8eb567c30158fc7abb8d92e731533f261bd3493594aeaa1a3b3aecd137df6fbcc1ce53e7d9eaba2a1238c185e591f95b97d7c366ac278edbb3327fb6bb0a181b4cb9c3c099a00e5d75c4|jarvis";
        String[] split = ss.split("\\|");
        String s1 = ss.split("|")[0];
        System.out.println(s1);
        String sss="1.pdf";
        System.out.println(sss.substring(0,sss.indexOf(".")));


    }
}
