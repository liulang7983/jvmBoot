package com.service;

import com.bean.TansactionTest;

/**
 * @Author ming.li
 * @Date 2025/9/9 14:24
 * @Version 1.0
 */
public interface TansactionService {
    public String success(TansactionTest tansactionTest);
    public String fail(TansactionTest tansactionTest);
    public String failNoTansaction(TansactionTest tansactionTest);
    public String failTryCatch(TansactionTest tansactionTest);
}
