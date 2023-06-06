package com.buba.springclould.service;


import com.buba.springcloud.pojo.Payment;

/**
 * @author ming.li
 * @date 2022/10/21 16:54
 */
public interface PaymentService {
    int create(Payment payment);
    Payment queryById(Long id);
}
