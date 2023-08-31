package com.bean;

import lombok.Data;

/**
 * @author ming.li
 * @date 2023/8/18 14:10
 */
@Data
public class ContractGoods {
    private Long id;
    private String batchId;
    private String fileName;
    private String contractNumber;
    private Integer type;
    private String buyer;
    private String seller;
    private String fileId;
    private String json;
}
