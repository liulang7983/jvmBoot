package com.dao;

import com.bean.Payment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author ming.li
 * @Date 2024/7/11 9:02
 * @Version 1.0
 */
@Mapper
public interface PaymentMapper {
    int insert(Payment payment);
    List<Payment> selectList();
    List<Payment> selectListByid(List list);
    List<Payment> selectListAllByid(List list);
}
