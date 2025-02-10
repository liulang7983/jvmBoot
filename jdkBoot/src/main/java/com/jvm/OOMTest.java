package com.jvm;

import com.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author ming.li
 * @Date 2025/2/8 14:25
 * @Version 1.0
 */
public class OOMTest {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (true) {
            list.add(new User(i++, UUID.randomUUID().toString()));
            new User(j--, UUID.randomUUID().toString());
        }
    }
}
