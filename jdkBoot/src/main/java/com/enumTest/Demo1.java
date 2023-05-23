package com.enumTest;

import com.enumBean.InvoiceType;

/**
 * @author ming.li
 * @date 2023/5/16 15:26
 */
public class Demo1 {
    public static void main(String[] args) {
        System.out.println(InvoiceType.SYSTEM_ERROR.getCode());
        System.out.println(InvoiceType.SYSTEM_ERROR.getMessage());
        System.out.println(InvoiceType.codeOf("010101").getMessage());
        System.out.println(InvoiceType.SYSTEM_ERROR);
    }
}
