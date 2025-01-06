package json;


import model.NlpLease;
import model.RelationExtend;

import java.util.*;

/**
 * 租赁物规则类型枚举
 */
public enum RuleTypeEnum {

    UUID_IN("uuidIn", "唯一识别码(报告)",1),

    UUID_OUT("uuidOut", "唯一识别码(附件)",2),

    INVOICE_NO("invoiceNo", "发票号码",3),

    LEASE_NAME("leaseName", "名称",4),

    LEASE_SPEC("leaseSpec", "型号",5),

    SUPPLIER("supplier", "供应商",6),

    ADDRESS("address", "地址",7),

    KEYSEARCH1("keySearch1", "关键词一",8),

    KEYSEARCH2("keySearch2", "关键词二",9),

    KEYSEARCH3("keySearch3", "关键词三",10);

    //类型代号
    private final String code;

    //类型名称
    private final String name;

    //排序
    private final Integer sort;


    RuleTypeEnum(String code, String name, Integer sort) {
        this.code = code;
        this.name = name;
        this.sort = sort;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Integer getSort() {
        return sort;
    }

    public static RuleTypeEnum codeMapping(String code){
        for (RuleTypeEnum value : RuleTypeEnum.values()) {
            if (value.code.equals(code)){
                return value;
            }
        }
        return null;
    }

    public static String code2Name(String code) {
        for (RuleTypeEnum value : RuleTypeEnum.values()) {
            if (value.code.equals(code)) {
                return value.name;
            }
        }
        return "";
    }

    public static String findReasonKey(String reason) {
        StringBuilder reasonSb = new StringBuilder();
        for (RuleTypeEnum value : RuleTypeEnum.values()) {
            if (reason.contains(value.name)) {
                if (value.equals(RuleTypeEnum.UUID_IN) || value.equals(RuleTypeEnum.UUID_OUT)) {
                    reasonSb.append("leaseUuid,");
                } else {
                    reasonSb.append(value.code + ",");
                }
            }
        }
        if (reason.contains("关键词")) {
            reasonSb.append("keySearch,");
        }
        return reasonSb.length() > 0 ? reasonSb.substring(0, reasonSb.length() - 1) : "";
    }

    public static Set<String> checkDupValue(RelationExtend relationExtend, NlpLease nlpLease) {
        Set<String> dupValue = new HashSet<>();
        for (RuleTypeEnum value : RuleTypeEnum.values()) {
            if (relationExtend.getReason().contains(value.name)) {
                if (value.equals(RuleTypeEnum.UUID_IN) || value.equals(RuleTypeEnum.UUID_OUT)) {
                    dupValue.add("leaseUuid");
                } else {
                    dupValue.add(value.code);
                }
            }
        }
        if (relationExtend.getReason().contains("关键词")) {
            String name = relationExtend.getName();
            String keySearch1 = nlpLease.getKeySearch1();
            if (keySearch1 != null && keySearch1.equals(name)) {
                dupValue.add("keySearch1");
            }
            String keySearch2 = nlpLease.getKeySearch2();
            if (keySearch2 != null && keySearch2.equals(name)) {
                dupValue.add("keySearch2");
            }
            String keySearch3 = nlpLease.getKeySearch3();
            if (keySearch3 != null && keySearch3.equals(name)) {
                dupValue.add("keySearch3");
            }
        }
        return dupValue;
    }

    public static List<String> codeMappingCondition(String code) {
        RuleTypeEnum mappingCode = codeMapping(code);
        if (mappingCode == null) {
            return Collections.emptyList();
        }
        switch (mappingCode) {
            case UUID_IN:
                return Arrays.asList(RuleConditionEnum.EQUAL.getCode());
            case UUID_OUT:
            case INVOICE_NO:
                return Arrays.asList(RuleConditionEnum.EQUAL.getCode(),RuleConditionEnum.EMPTY.getCode(),RuleConditionEnum.SELF_EMPTY.getCode());
            case LEASE_NAME:
                return Arrays.asList(RuleConditionEnum.EQUAL.getCode(),RuleConditionEnum.SIMILAR.getCode(),RuleConditionEnum.EMPTY.getCode(),RuleConditionEnum.SELF_EMPTY.getCode());
            case LEASE_SPEC:
            case SUPPLIER:
            case ADDRESS:
                return Arrays.asList(RuleConditionEnum.EQUAL.getCode(),RuleConditionEnum.CONTAIN.getCode(),RuleConditionEnum.EMPTY.getCode(),RuleConditionEnum.SELF_EMPTY.getCode());
            case KEYSEARCH1:
            case KEYSEARCH2:
            case KEYSEARCH3:
                return Arrays.asList(RuleConditionEnum.CONTAIN.getCode());
            default:
                return Collections.emptyList();
        }
    }
}
