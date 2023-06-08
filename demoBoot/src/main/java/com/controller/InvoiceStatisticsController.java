package com.controller;


import com.bean.ApiResult;
import com.model.StatisticsVo;
import com.service.InvoiceStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "发票统计页面控制器")
@RestController
@RequestMapping("ias/statistics")
public class InvoiceStatisticsController {
    private static final Logger logger = LoggerFactory.getLogger(InvoiceStatisticsController.class);
    @Autowired
    private InvoiceStatisticsService invoiceStatisticsService;


    @ApiOperation(value = "模糊查询租户")
    @PostMapping(value = "/findStatistics")
    public ApiResult indistinctSelect(@RequestBody StatisticsVo statisticsVo) {
        //按照条件case when查询
        return invoiceStatisticsService.indistinctSelect(statisticsVo);
    }

}
