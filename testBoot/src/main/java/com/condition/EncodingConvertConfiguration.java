package com.condition;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

/**
 * @author ming.li
 * @date 2023/9/21 20:37
 */
@SpringBootConfiguration
public class EncodingConvertConfiguration {
    @Bean
    @Conditional(UTF8Condition.class)
    public EncodingConvert UTF8EncodingConvert() {
        return new UTF8EncodingConvert();
    }

    @Bean
    @Conditional(GBKCondition.class)
    public EncodingConvert GBKEncodingConvert() {
        return new GBKEncodingConvert();
    }
}
