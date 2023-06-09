package com.buba.springcloud.service.fallback;


import com.buba.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

/**
 * @author ming.li
 * @date 2022/9/19 17:29
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "comsumer paymentInfo_OK接口熔断";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "comsumer PaymentFallbackService fall  paymentInfo_TimeOut接口熔断";
    }
}
