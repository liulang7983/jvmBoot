package com.service;

import com.bean.Payment;

import java.util.List;

/**
 * @Author ming.li
 * @Date 2024/7/11 11:32
 * @Version 1.0
 */

public interface PaymentService {
    void create();
    void selectList();
    List<Payment> selectListSerial();
    List<Payment> selectListConcurrent();
    Payment selectById(Integer id);
    void requiredNew(Payment payment);
    void required(Payment payment);
    void notSupported(Payment payment);
    void never(Payment payment);
}
