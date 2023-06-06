package com.buba.springclould.dao;


import com.buba.springcloud.pojo.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ming.li
 * @date 2022/10/21 16:45
 */
@Mapper
public interface PaymentDao {
    int create(Payment payment);
    Payment queryById(@Param("id") Long id);
}
