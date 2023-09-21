package com.config;

import com.bean.CheckInvoice;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author ming.li
 * @date 2023/9/21 16:29
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{CheckInvoice.class.getName(),"com.bean.InvoiceCheck"};
    }
}
