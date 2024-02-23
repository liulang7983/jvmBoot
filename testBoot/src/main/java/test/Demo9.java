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


    }
}
