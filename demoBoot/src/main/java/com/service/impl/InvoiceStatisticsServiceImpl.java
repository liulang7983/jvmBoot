package com.service.impl;


import com.bean.ApiResult;
import com.dao.InvoiceStatisticsMapper;
import com.test.enumUtil.ERetCode;
import com.model.StatisticsVo;
import com.service.InvoiceStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceStatisticsServiceImpl implements InvoiceStatisticsService {

    @Autowired
    private InvoiceStatisticsMapper invoiceStatisticsMapper;


    @Override
    public ApiResult indistinctSelect(StatisticsVo statisticsVo) {
        statisticsVo.setTenantId("lm");
        List<String> list = invoiceStatisticsMapper.indistinctSelect(statisticsVo);
        return new ApiResult(list);
    }


}
