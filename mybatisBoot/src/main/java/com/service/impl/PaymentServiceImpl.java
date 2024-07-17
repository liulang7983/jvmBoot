package com.service.impl;

import com.bean.Payment;
import com.dao.PaymentMapper;
import com.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author ming.li
 * @Date 2024/7/11 11:32
 * @Version 1.0
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public void create() {
        for (int i = 0; i <900 ; i++) {
            Payment payment = new Payment();
            payment.setSerial("张三"+i);
            paymentMapper.insert(payment);
        }
    }

    @Override
    public void selectList() {
        List<Payment> payments = paymentMapper.selectList();
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i <payments.size() ; i++) {
            list.add(payments.get(i).getId());
        }

        int i = list.size() / 977;
        int i1 = list.size() % 977;
        if (i1>0){
            i+=1;
        }
        List<Payment> allList=new ArrayList<>();

        for (int j = 0; j <i ; j++) {
            List<Integer> list1 =new ArrayList<>();
            if (j==i-1){
                list1 = list.subList(j * 977, list.size());
            }else {
                list1 = list.subList(j * 977, (j + 1)*977);
            }
            List<Payment> payments1 = paymentMapper.selectListByid(list1);
            allList.addAll(payments1);
        }
        List<Payment> payments1 = paymentMapper.selectListAllByid(list);
        System.out.println(allList.size());
        System.out.println(payments1.size());
    }

    @Override
    public List<Payment> selectListSerial() {
        long start = System.currentTimeMillis();
        List<Integer> list1 =new ArrayList<>();
        List<Integer> list2 =new ArrayList<>();
        List<Integer> list3 =new ArrayList<>();
        List<Payment> paymentList=new ArrayList<>();
        for (int i = 1100; i <1400 ; i++) {
            list1.add(i);
        }
        for (int i = 1300; i <1600 ; i++) {
            list2.add(i);
        }
        for (int i = 1600; i <1900 ; i++) {
            list3.add(i);
        }
        paymentList.addAll(paymentMapper.selectListByid(list1));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        paymentList.addAll(paymentMapper.selectListByid(list2));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        paymentList.addAll(paymentMapper.selectListByid(list3));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("selectListSerial耗时:"+(end-start));
        return paymentList;
    }

    @Override
    public List<Payment> selectListConcurrent() {
        long start = System.currentTimeMillis();
        List<Integer> list1 =new ArrayList<>();
        List<Integer> list2 =new ArrayList<>();
        List<Integer> list3 =new ArrayList<>();
        List<Payment> paymentList=new ArrayList<>();
        CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
            for (int i = 1100; i < 1400; i++) {
                list1.add(i);
            }
            paymentList.addAll(paymentMapper.selectListByid(list1));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }).thenApplyAsync((q) -> {
            for (int i = 1300; i < 1600; i++) {
                list2.add(i);
            }
            paymentList.addAll(paymentMapper.selectListByid(list2));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }).thenApplyAsync((q) -> {
            for (int i = 1600; i < 1900; i++) {
                list3.add(i);
            }
            paymentList.addAll(paymentMapper.selectListByid(list3));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        });
        try {
            Boolean aBoolean = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("selectListConcurrent耗时:"+(end-start));
        return paymentList;
    }
}
