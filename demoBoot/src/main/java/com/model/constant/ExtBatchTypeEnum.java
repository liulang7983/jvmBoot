package com.model.constant;

public enum ExtBatchTypeEnum {

    BATCHTYPE_LERIS("0007", "租赁物");

    private final String code;
    private final String desc;

    private ExtBatchTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String code() {
        return this.code;
    }

    public static ExtBatchTypeEnum codeOf(String code) {
        ExtBatchTypeEnum[] extBatchTypeEnums = values();
        int var2 = extBatchTypeEnums.length;
        for (int i = 0; i < var2; ++i) {
            ExtBatchTypeEnum enums = extBatchTypeEnums[i];
            if (enums.code().equalsIgnoreCase(code)) {
                return enums;
            }
        }
        return null;
    }
}
