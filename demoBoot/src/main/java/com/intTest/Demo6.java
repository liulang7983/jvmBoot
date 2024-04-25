package com.intTest;

import com.bean.User;
import com.bean.UserVo;

import java.util.UUID;

/**
 * @Author ming.li
 * @Date 2024/4/8 14:26
 * @Version 1.0
 */
public class Demo6 {
    public static void main(String[] args) {
        Integer a=5;
        Double v = a * 0.3;
        System.out.println(v.intValue());
        String replace = UUID.randomUUID().toString().replace("-", "");
        System.out.println(replace);
        UserVo userVo = new UserVo();
        //userVo.setSs("ss");
        userVo.setId(1L);
        User user=userVo;
        System.out.println(user);
        UserVo ss=(UserVo) user;
        System.out.println(ss);

    }
}
