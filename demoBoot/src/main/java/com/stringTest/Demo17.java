package com.stringTest;

import com.bean.User;
import org.apache.commons.lang3.StringUtils;

/**
 * @author ming.li
 * @date 2023/10/26 9:59
 */
public class Demo17 {
    public static void main(String[] args) {
        User user = new User();
        System.out.println("ss:"+user.getName());
        change(user);
        System.out.println("ss:"+user.getName());
    }
    public static void change(User user){
        String name = user.getName();
        if (StringUtils.isBlank(name)){
            user.setName("张三");
        }
    }
}
