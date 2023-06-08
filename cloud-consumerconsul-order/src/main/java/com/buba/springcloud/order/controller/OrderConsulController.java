package com.buba.springcloud.order.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author ming.li
 * @date 2022/9/15 14:19
 */
@RestController
public class OrderConsulController {
    public static final  String PAYMENT_URL = "http://cloud-providerconsul-payment";
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/consul")
    public String getPayment(){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/consul/",String.class);
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
}
