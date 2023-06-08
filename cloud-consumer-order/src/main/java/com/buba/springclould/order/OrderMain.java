package com.buba.springclould.order;


import com.buba.springclould.myRule.MyselfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author ming.li
 * @date 2022/10/21 17:18
 */
@SpringBootApplication
@EnableDiscoveryClient
//name为生产者服务的服务名称  configuration为配置类的类名
// 加了这个意思就是调这个服务我会用MyselfRule里面的自定义负载均衡路由规则类
@RibbonClient(name = "cloud-provide-payment",configuration = MyselfRule.class)
public class OrderMain {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain.class,args);
    }
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
