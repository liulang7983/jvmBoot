package com.buba.springcloud.service;


import com.buba.springcloud.pojo.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ming.li
 * @date 2022/9/16 14:03
 */
@Service
@FeignClient(value = "cloud-provide-payment")
public interface  PaymentService {
    @GetMapping("/payment/queryById/{id}")
    public CommonResult queryById(@PathVariable("id") Long id);

    //调用生产者微服务名称为cloud-provide-payment下边的模拟超时的接口
    @GetMapping("/payment/getportTimeOut")
    public String PaymentFeignTimeOut() throws InterruptedException;

}
