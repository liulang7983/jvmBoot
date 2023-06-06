package com.buba.springclould.service.impl;


import com.buba.springcloud.pojo.Payment;
import com.buba.springclould.dao.PaymentDao;
import com.buba.springclould.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ming.li
 * @date 2022/10/21 16:54
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;
    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment queryById(Long id) {
        return paymentDao.queryById(id);
    }
}
