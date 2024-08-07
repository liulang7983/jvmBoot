package com.test.enumTest;

import com.test.enumUtil.IasSupportInvoiceType;

/**
 * @author ming.li
 * @date 2023/6/14 10:50
 */
public class Demo1 {
    public static void main(String[] args) {
        System.out.println(IasSupportInvoiceType.codeOf("03"));
        System.out.println(IasSupportInvoiceType.TYPE_0.getInvoiceType());
    }
}
