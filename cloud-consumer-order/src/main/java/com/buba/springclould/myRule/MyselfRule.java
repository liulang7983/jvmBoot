package com.buba.springclould.myRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;

/**
 * @author ming.li
 * @date 2023/6/7 20:11
 */
public class MyselfRule {
    @Bean
    public IRule myRule(){
        //return new RoundRobinRule();//轮询
        return new RandomRule();//随机
    }
}
