package com.fieldTest;

import com.bean.User;
import com.util.FieldUtil;

import java.lang.reflect.Field;

/**
 * @author ming.li
 * @date 2023/5/31 18:13
 */
public class FieldDemo1 {
    public static void main(String[] args) {
        User user = new User(1L, "张三", "是憨憨");
        User user1 = new User(1L, "李四", "是憨憨");
        Field[] declaredFields = User.class.getDeclaredFields();
        FieldUtil.fieldCompare(declaredFields,user,user1);
    }
}
