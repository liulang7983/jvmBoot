package com.model.qcc;

public class QCCPaging {
    private Integer PageSize;
    private Integer PageIndex;
    private Integer TotalRecords;

    public Integer getPageSize() {
        return PageSize;
    }

    public void setPageSize(Integer pageSize) {
        PageSize = pageSize;
    }

    public Integer getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        PageIndex = pageIndex;
    }

    public Integer getTotalRecords() {
        return TotalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        TotalRecords = totalRecords;
    }
}
