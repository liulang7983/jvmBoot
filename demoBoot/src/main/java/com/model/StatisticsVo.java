package com.model;

import lombok.Data;

@Data
public class StatisticsVo {
    private String userName;

    private String startDate;

    private String endDate;

    private String timePeriod;

    private String invoiceCode;

    private String invoiceNumber;

    private String remarkId;
    private String buyer;//购买方
    private String seller;//销售方
    private String tenantId;//租户ID
}
