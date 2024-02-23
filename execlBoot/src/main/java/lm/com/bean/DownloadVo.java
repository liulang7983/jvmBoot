package lm.com.bean;

import lombok.Data;

@Data
public class DownloadVo {
    private String batchId;
    private String names;
    private String endDate;
    private String startDate;
    private String tpId;
    private String pathName;
}
