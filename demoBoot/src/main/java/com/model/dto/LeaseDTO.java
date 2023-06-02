package com.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * 租赁物DTO
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "租赁物DTO")
public class LeaseDTO {

    /*
     业务ID
     */
    @NotBlank(message = "租赁物业务ID不能为空")
    @ApiModelProperty(value = "租赁物业务ID不能为空")
    private String extId;
    /*
     租赁物名称
     */
    @NotBlank(message = "租赁物名称不能为空")
    @ApiModelProperty(value = "租赁物名称")
    private String leaseName;
    /*
    租赁物型号
     */
    @ApiModelProperty(value = "租赁物型号")
    private String leaseSpec;
    /*
     租赁物数量
     */
    @ApiModelProperty(value = "租赁物数量")
    private Float leaseNum;
    /*
    租赁物单位
     */
    @ApiModelProperty(value = "租赁物单位")
    private String leaseUnit;
    /*
     发票编号
     */
    @ApiModelProperty(value = "发票编号")
    private String ownerNo;
    /*
     唯一识别码
     */
    @ApiModelProperty(value = "唯一识别码")
    private String leaseUuid;
    /*
     金额
     */
    @ApiModelProperty(value = "金额")
    private BigDecimal leasePrice;

}
