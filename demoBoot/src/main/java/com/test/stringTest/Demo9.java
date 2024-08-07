package com.test.stringTest;

import com.alibaba.fastjson.JSONObject;
import com.bean.User;

/**
 * @author ming.li
 * @date 2023/7/6 10:29
 */
public class Demo9 {
    public static void main(String[] args) {
        User user = new User(1L, "张三", "是憨憨");
        JSONObject jsonObject = JSONObject.parseObject(user.toString());
    }
}
