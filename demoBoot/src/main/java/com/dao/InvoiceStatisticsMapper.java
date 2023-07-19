package com.dao;


import com.model.StatisticsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InvoiceStatisticsMapper {

    List<String> indistinctSelect(StatisticsVo statisticsVo);

}
