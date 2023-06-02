package com.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 租赁物导出VO
 */
@Getter
@Setter
@NoArgsConstructor
public class LeaseExportDTO {
    /*
     主键ID
     */
    private Long nlpLId;
    /*
     租赁物名称
     */
    private String leaseName;
    /*
    租赁物型号
     */
    private String leaseSpec;
    /*
     重复状态：0-不重复，1-重复
     */
    private Integer dupStatus;
    /*
     中登比对结果字段
     */
    private String relation;
    /*
     最大重复度
     */
    private Integer maxDup;
    /*
     租赁物业务ID
     */
    private String extId;
}
