package com.listTest;

import com.alibaba.excel.util.StringUtils;
import com.bean.User;
import org.apache.poi.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ming.li
 * @date 2023/5/18 10:50
 */
public class Demo1 {
    public static void main(String[] args) {
        List<User> list=new ArrayList<>();
        list.add(new User(1,"lm1","lm11"));
        list.add(new User(2,"lm2","lm22"));
        list.add(new User(3,"lm3","lm33"));
        list.add(new User(4,"lm4","lm44"));
        list.add(new User(4,"lm4",null));
        List<String> collect = list.stream().map(User::getMessage).collect(Collectors.toList());
        System.out.println(collect);
        String join = StringUtil.join(",", collect);
        System.out.println(join);
        String s = list.stream().map(User::getMessage).toString();
        System.out.println(s);
    }
}
