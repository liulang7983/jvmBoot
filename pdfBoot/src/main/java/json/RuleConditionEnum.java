package json;


/**
 * 租赁物规则条件枚举
 */
public enum RuleConditionEnum {

    EQUAL("equal", "一致",1),

    CONTAIN("contain", "包含",2),

    SIMILAR("similar", "相似",3),

    EMPTY("empty", "为空",4),

    SELF_EMPTY("selfEmpty", "我方为空",5);

    //条件代号
    private final String code;

    //规则名称
    private final String name;

    //排序
    private final Integer sort;


    RuleConditionEnum(String code, String name, Integer sort) {
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


    public static RuleConditionEnum codeMapping(String code){
        for (RuleConditionEnum value : RuleConditionEnum.values()) {
            if (value.code.equals(code)){
                return value;
            }
        }
        return null;
    }

    public static String code2Name(String code){
        for (RuleConditionEnum value : RuleConditionEnum.values()) {
            if (value.code.equals(code)){
                return value.name;
            }
        }
        return "";
    }
}
