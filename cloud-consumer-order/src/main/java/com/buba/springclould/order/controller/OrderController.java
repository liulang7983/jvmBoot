package com.buba.springclould.order.controller;


import com.buba.springcloud.pojo.CommonResult;
import com.buba.springcloud.pojo.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ming.li
 * @date 2022/10/21 17:20
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private static final String PAYMENT_URL="http://cloud-provide-payment/payment";

    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        System.out.println("payment:"+payment);
        return restTemplate.postForObject(PAYMENT_URL+"/create",payment,CommonResult.class);
    }
    @RequestMapping("/queryById/{id}")
    public CommonResult queryById(@PathVariable("id") Long id){
        System.out.println("id:"+id);
        return restTemplate.getForObject(PAYMENT_URL+"/queryById/"+id,CommonResult.class);
    }

    @RequestMapping("/getport")
    public Integer getport(){
        System.out.println("getport");
        return restTemplate.getForObject(PAYMENT_URL+"/getport",Integer.class);
    }
    @RequestMapping("/discovery")
    public Object discovery(){
        System.out.println("discovery");
        return restTemplate.getForObject(PAYMENT_URL+"/discovery",Object.class);
    }
}
