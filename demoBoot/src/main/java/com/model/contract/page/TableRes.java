package com.model.contract.page;

import com.alibaba.fastjson.annotation.JSONField;


public class TableRes {
    @JSONField(name = "tables")
    private Tables[] tables = new Tables[]{};

    public Tables[] getTables() {
        return tables;
    }

    public void setTables(Tables[] tables) {
        this.tables = tables;
    }
}
