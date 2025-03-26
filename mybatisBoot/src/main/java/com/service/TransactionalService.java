package com.service;

import com.bean.Payment;

/**
 * @Author ming.li
 * @Date 2025/3/26 10:51
 * @Version 1.0
 */
public interface TransactionalService {
    void requiredNew(Payment payment);
    void required(Payment payment);
}
