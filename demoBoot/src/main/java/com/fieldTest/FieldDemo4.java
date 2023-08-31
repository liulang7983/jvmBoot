package com.fieldTest;

import com.bean.CheckTemp;
import com.bean.Invoice;
import com.bean.InvoiceGoods;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ming.li
 * @date 2023/7/4 14:46
 */
public class FieldDemo4 {
    public static void main(String[] args) throws IllegalAccessException {
        Invoice invoice = new Invoice();
        Field[] declaredFields = invoice.getClass().getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            if (field.getName().equals("invoiceId")){
                field.setAccessible(true);
                field.set(invoice,"ss");
            }

        }
        System.out.println(invoice);

    }
}
