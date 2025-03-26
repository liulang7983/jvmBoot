package com.service.impl;

import com.bean.Payment;
import com.service.PaymentService;
import com.service.TransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author ming.li
 * @Date 2025/3/26 10:52
 * @Version 1.0
 */
@Service
public class TransactionalServiceImpl implements TransactionalService {

    @Autowired
    private PaymentService paymentService;

    /**
     * 此时paymentService.requiredNew的事务传播行为为:
     * Propagation.REQUIRES_NEW：无论当前是否存在事务，都会创建一个新的事务，并且挂起当前事务（如果存在）
     * 那么此时我本身的的异常不会影响到paymentService.requiredNew
     * @param payment
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void requiredNew(Payment payment) {
        paymentService.requiredNew(payment);
        int a = 1 / 0;
    }
    /**
     * 此时paymentService.required的事务传播行为为:
     * Propagation.REQUIRED：如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。这是默认的传播行为
     * 那么此时他加入到了我本地的事务，我本身的异常会影响他，所以回滚了
     * @param payment
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void required(Payment payment) {
        paymentService.required(payment);
        int a = 1 / 0;

    }
}
