package com.service;

import com.bean.TansactionTest;
import com.dao.TansactionTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author ming.li
 * @Date 2025/9/9 14:24
 * @Version 1.0
 */
@Service
public class TansactionService1 {
    @Autowired
    private TansactionTestMapper tansactionTestMapper;
    public String bedek(TansactionTest tansactionTest){
        bedek1(tansactionTest);
        return "成功";
    }
    @Transactional
    String bedek1(TansactionTest tansactionTest){
        tansactionTestMapper.update(tansactionTest);
        int a=0/0;
        return "成功";
    }
    @Transactional
    public final String failFinal(TansactionTest tansactionTest){
        System.out.println("我是failFinal");
        tansactionTestMapper.update(tansactionTest);
        int a=0/0;
        return "成功";
    }
}
