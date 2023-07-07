package com.fieldTest;

import com.bean.Invoice;
import com.bean.InvoiceGoods;
import com.bean.User;
import com.util.FieldUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ming.li
 * @date 2023/5/31 18:13
 */
public class FieldDemo2 {
    public static void main(String[] args) {
        Invoice invoice = new Invoice();
        invoice.setBuyer("张三");
        List<InvoiceGoods> list=new ArrayList<>();
        InvoiceGoods invoiceGoods = new InvoiceGoods();
        invoiceGoods.setGoodsName("嘿嘿");
        list.add(invoiceGoods);
        invoice.setInvoiceGoodsList(list);

        Invoice invoice1 = new Invoice();
        invoice1.setBuyer("张三1");
        List<InvoiceGoods> list1=new ArrayList<>();
        InvoiceGoods invoiceGoods1 = new InvoiceGoods();
        invoiceGoods1.setGoodsName("嘿嘿");
        list1.add(invoiceGoods1);
        invoice1.setInvoiceGoodsList(list1);
        Field[] declaredFields = Invoice.class.getDeclaredFields();
        String s = FieldUtil.fieldCompare1(declaredFields, invoice, invoice1);
        System.out.println(s);
    }
}
