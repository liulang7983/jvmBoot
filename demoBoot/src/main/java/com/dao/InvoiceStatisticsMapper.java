package com.dao;



import com.model.Statistics;
import com.model.StatisticsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface InvoiceStatisticsMapper {

    List<String> indistinctSelect(StatisticsVo statisticsVo);

}
