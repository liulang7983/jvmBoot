package lm.com.fileTest;

import com.sunyard.client.SunEcmClientApi;
import com.sunyard.client.bean.ClientBatchBean;
import com.sunyard.client.impl.SunEcmClientSocketApiImpl;

/**
 * @Author ming.li
 * @Date 2024/2/28 15:09
 * @Version 1.0
 */
public class Demo2 {
    public static void main(String[] args)throws Exception {
        String ip = "172.18.26.250";
        int socketPort = 7002;
        SunEcmClientApi clientApi = new SunEcmClientSocketApiImpl(ip,socketPort);
        ClientBatchBean clientBatchBean = new ClientBatchBean();
        clientApi.checkIn(clientBatchBean,null);
        System.out.println("ss");
    }
}
