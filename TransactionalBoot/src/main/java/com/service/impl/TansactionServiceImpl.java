package com.service.impl;

import com.bean.TansactionTest;
import com.dao.TansactionTestMapper;
import com.service.TansactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author ming.li
 * @Date 2025/9/9 14:24
 * @Version 1.0
 */
@Service
public class TansactionServiceImpl implements TansactionService {
    @Autowired
    private TansactionTestMapper tansactionTestMapper;
    @Transactional
    @Override
    public String success(TansactionTest tansactionTest) {
        tansactionTestMapper.update(tansactionTest);
        return "成功";
    }
    @Transactional
    @Override
    public String fail(TansactionTest tansactionTest) {
        tansactionTestMapper.update(tansactionTest);
        int a=0/0;
        return "成功";
    }

    @Override
    public String failNoTansaction(TansactionTest tansactionTest) {
        tansactionTestMapper.update(tansactionTest);
        int a=0/0;
        return "成功";
    }

    @Transactional
    @Override
    public String failTryCatch(TansactionTest tansactionTest) {
        try {
            tansactionTestMapper.update(tansactionTest);
            int a=0/0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "成功";
    }
}
