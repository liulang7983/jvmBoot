package com.test.lockTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author ming.li
 * @Date 2025/8/15 11:06
 * @Version 1.0
 */
public class Demo3 {
    private  static Map<Integer, Date> map=new HashMap();


    public boolean isExist(Integer a){
        boolean flags=false;
        if (!map.containsKey(a)){
            map.put(a,new Date());
        }else {
            flags=true;
        }
        return flags;
    }
}
