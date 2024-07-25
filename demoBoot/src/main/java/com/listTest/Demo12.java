package com.listTest;

import com.bean.LeaseDetail;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author ming.li
 * @Date 2024/6/25 14:41
 * @Version 1.0
 */
public class Demo12 {
    public static void main(String[] args) {
        List<LeaseDetail> list=new ArrayList<>();
        list.add(new LeaseDetail("张三  ",0,3));
        list.add(new LeaseDetail("李四  ",2,1));
        list.add(new LeaseDetail("王五",0,1));
        list.add(new LeaseDetail("赵六",0,1));
        list.add(new LeaseDetail("田七",0,3));
        List<LeaseDetail> collect = list.stream().filter(o -> o.getIsDelete().equals(0)).sorted(Comparator.comparing(LeaseDetail::getDupStatus).reversed()).collect(Collectors.toList());


        for (int i = 0; i <collect.size() ; i++) {
            System.out.println(collect.get(i).getName().trim()+"ss");
        }
        String s="  ";
        if (StringUtils.isNotEmpty(s)){
            System.out.println("不为空");
        }
        if (StringUtils.isNotBlank(s)){
            System.out.println("不为空");
        }else {
            System.out.println("为空");
        }
    }
}
