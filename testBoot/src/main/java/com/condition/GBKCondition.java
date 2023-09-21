package com.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author ming.li
 * @date 2023/9/21 20:33
 */
public class GBKCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String property = System.getProperty("file.encoding");
        if (property!=null&&"gbk".equalsIgnoreCase(property)){
            return true;
        }
        return false;
    }
}
