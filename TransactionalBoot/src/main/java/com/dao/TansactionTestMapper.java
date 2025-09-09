package com.dao;

import com.bean.TansactionTest;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author ming.li
 * @Date 2025/9/9 14:18
 * @Version 1.0
 */
@Mapper
public interface TansactionTestMapper {
    int update(TansactionTest tansactionTest);
}
