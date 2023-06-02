package com.model.constant;

public enum RegTypeEnum {

    INIT_REG("01", "初始登记"),
    CHANGE_REG("02", "变更登记"),
    EXTENSION_PERIOD_REG("03", "展期登记"),
    OBJECTION_REG("04", "异议登记"),
    CANCELLATION_REG("05", "注销登记");
    private final String code;
    private final String desc;

    private RegTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String code() {
        return this.code;
    }

    public String desc() {
        return this.desc;
    }

    public static RegTypeEnum codeOf(String code) {
        RegTypeEnum[] extBatchTypeEnums = values();
        int var2 = extBatchTypeEnums.length;
        for (int i = 0; i < var2; ++i) {
            RegTypeEnum enums = extBatchTypeEnums[i];
            if (enums.code().equalsIgnoreCase(code)) {
                return enums;
            }
        }
        return null;
    }
}
