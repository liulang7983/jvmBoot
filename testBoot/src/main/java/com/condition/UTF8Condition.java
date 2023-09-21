package com.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author ming.li
 * @date 2023/9/21 20:33
 */
public class UTF8Condition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String property = System.getProperty("file.encoding");
        if (property!=null&&"UTF-8".equalsIgnoreCase(property)){
            return true;
        }
        return false;
    }
}
