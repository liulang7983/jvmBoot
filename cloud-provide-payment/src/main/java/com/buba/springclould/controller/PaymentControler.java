package com.buba.springclould.controller;


import com.buba.springcloud.pojo.CommonResult;
import com.buba.springcloud.pojo.Payment;
import com.buba.springclould.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ming.li
 * @date 2022/10/21 16:56
 */
@RestController
@RequestMapping("/payment")
public class PaymentControler {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private Integer port;
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        System.out.println("Payment:"+payment);
        int i = paymentService.create(payment);
        if (i==0){
            return new CommonResult(400,"插入失败");
        }else {
            return new CommonResult(200,"插入成功");
        }
    }

    @RequestMapping("/queryById/{id}")
    public CommonResult queryById(@PathVariable("id") Long id){
        System.out.println("queryById:"+id);
        Payment payment = paymentService.queryById(id);
        if (payment==null){
            return new CommonResult(400,"查询失败");
        }else {
            return new CommonResult(200,"查询成功",payment);
        }
    }
    @RequestMapping("/getport")
    public Integer getport(){
        return port;
    }
    @RequestMapping("/discovery")
    public Object discovery(){
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-provide-payment");
        for (ServiceInstance instance:instances) {
            System.out.println("InstanceId:"+instance.getInstanceId()+","+"ServiceId:"+instance.getServiceId()+","+"Host:"+instance.getHost()+","+"Port:"+instance.getPort()+","+"Uri:"+instance.getUri());
        }
        List<String> services = discoveryClient.getServices();
        for (String service:services) {
            System.out.println(service);
        }
        return this.discoveryClient;
    }

    @RequestMapping("/getportTimeOut")
    public Integer getportTimeOut() throws InterruptedException {
        System.out.println("getportTimeOut被调用");
        TimeUnit.SECONDS.sleep(5);
        return port;
    }
}
