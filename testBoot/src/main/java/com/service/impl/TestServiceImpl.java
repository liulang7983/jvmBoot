package com.service.impl;

import com.service.TestService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ming.li
 * @date 2023/9/25 19:13
 */
@Service
public class TestServiceImpl implements TestService, InitializingBean {
    public static List<String> list=new ArrayList<>();

    @Override
    public List<String> getArray() {
        return list;
    }

    @Override
    public String refresh() {
        //此时可以是修改了但是需要重新从DAO获取对象到静态对象里
        list.add("yy");
        return "成功";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //此时也可以调用DAO,把一些常用配置放入到静态对象里
        list.add("ss");
        list.add("zz");
    }
}
