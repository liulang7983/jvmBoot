package com.test.fieldTest;

import com.bean.CheckTemp;
import com.bean.Invoice;
import com.bean.InvoiceGoods;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ming.li
 * @date 2023/7/4 14:46
 */
public class FieldDemo3 {
    public static void main(String[] args) {
        Invoice invoice = new Invoice();
        invoice.setBuyer("张三");
        List<InvoiceGoods> list=new ArrayList<>();
        InvoiceGoods invoiceGoods = new InvoiceGoods();
        invoiceGoods.setGoodsName("嘿嘿");
        list.add(invoiceGoods);
        invoice.setInvoiceGoodsList(list);
        CheckTemp checkTemp = new CheckTemp();//存放识别结果
        BeanUtils.copyProperties(invoice, checkTemp);
        System.out.println(checkTemp);

    }
}
