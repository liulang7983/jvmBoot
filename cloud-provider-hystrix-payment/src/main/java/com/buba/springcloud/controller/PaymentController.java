package com.buba.springcloud.controller;


import com.buba.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ming.li
 * @date 2022/9/19 9:55
 */
@RestController
public class PaymentController {

        @Autowired
        private PaymentService paymentService;

        @Value("${server.port}")
        private String servicePort;

        private static int INTEGER=0;
        /**
         * 正常访问
         * @param id
         * @return
         */
        @GetMapping("/payment/hystrix/ok/{id}")
        public String paymentInfo_OK(@PathVariable("id") Integer id) {
            String result = paymentService.paymentInfo_OK(id);
            System.out.println("*******************result:" + result);
            return result;
        }

        /**
         * 超时访问
         * @param id
         * @return
         */
        @GetMapping("/payment/hystrix/timeout/{id}")
        public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
            System.out.println("进入paymentInfo_TimeOut方法");
            String result = paymentService.paymentInfo_TimeOut(id);
            System.out.println(result);
            return result;

        }

    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        String result = paymentService.paymentCircuitBreaker(id);
        System.out.println("****result: "+result);
        return result;
    }

    @GetMapping("/test")
    public Integer test() {
        INTEGER++;
        return INTEGER;

    }

}
