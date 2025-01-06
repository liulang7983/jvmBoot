package json;

/**
 * 租赁物重复原因类型枚举
 */
public enum LeaseRepeatTypeEnum {

    A("1", "发票号码重复","发票号码一致"),
    B("2", "唯一识别码(附件)重复","唯一识别码(附件)一致"),
    C("3", "名称重复","名称一致"),
    D("4", "名称+型号重复","名称一致且型号一致"),
    E("5", "唯一识别码(报告)重复","唯一识别码(报告)一致"),
    F("6", "关键字重复","关键字包含");
    private final String type;
    private final String msg;

    private final String text;

    LeaseRepeatTypeEnum(String type, String msg, String text) {
        this.type = type;
        this.msg = msg;
        this.text = text;
    }

    public String type() {
        return type;
    }

    public String msg() {
        return msg;
    }

    public String text() {
        return text;
    }

    public static String msgToMsg(String msg) {
        LeaseRepeatTypeEnum[] types = values();
        for (LeaseRepeatTypeEnum type : types) {
            if (type.msg.equals(msg)){
                return msg;
            }
        }
        return null;
    }
}
