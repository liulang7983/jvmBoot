package com.config;

import com.bean.Invoice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ming.li
 * @date 2023/9/21 16:22
 */
@Configuration
public class MyConfiguration {
    @Bean
    public Invoice getInvoice(){
        return new Invoice();
    }
}
