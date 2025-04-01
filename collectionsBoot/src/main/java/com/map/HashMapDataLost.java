package com.map;

import java.util.HashMap;
import java.util.Map;


public class HashMapDataLost {
    public static final Map<String, String> map = new HashMap<String, String>();
    //结果是map.size不到两千，而且中间有的key在value为null，应该是扩容的时候丢失
    public static void main(String[] args) throws InterruptedException {
        //线程一
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    map.put(String.valueOf(i), String.valueOf(i));
                }
            }
        }.start();
        //线程二
        new Thread(){
            @Override
            public void run() {
                for(int j=1000;j<2000;j++){
                    map.put(String.valueOf(j), String.valueOf(j));
                }
            }
        }.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //输出
        for(int i=0;i<2000;i++){
            System.out.println("第："+i+"元素，值："+map.get(String.valueOf(i)));
        }
        System.out.println("map.size:"+map.size());
    }
}
