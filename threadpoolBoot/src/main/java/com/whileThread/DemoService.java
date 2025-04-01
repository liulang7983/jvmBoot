package com.whileThread;

import java.util.Random;

/**
 * @author ming.li
 * @date 2023/11/9 11:45
 */
public class DemoService {

    public String getStr(){
        int i = new Random().nextInt(3);
        try {
            Thread.sleep(i*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "暂停了"+i+"秒";
    }
}
