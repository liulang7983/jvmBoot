package com.enumUtil;

/**
 * @author qinwu.zhu
 * @since 2020/4/30 15:07
 */
public enum OcrTypeEnum {
    /**
     * 身份证
     */
    ID_CARD("00000001", "身份证"),
    BANK_CARD("00000002", "银行卡"),
    RESIDENCE_BOOKLET("00000003", "户口本"),
    DRIVER("00000004", "驾驶证"),
    DRIVING("00000005", "行驶证"),
    REAL_ESTATE("00000006", "不动产"),
    PROP_OWNER("00000007", "房产证"),
    BIZLICENSE("00000008", "营业执照"),
    INVOICE("00000009", "增值税发票"),
    INVOICE_ROLL("00000012", "增值税卷票"),
    INVOICE_QUOTA("00000013", "定额发票"),
    INVOICE_FLIGHT("00000014", "机票行程单"),
    INVOICE_TRAINTICKET("00000015", "火车票"),
    INVOICE_BUS("00000016", "巴士车票"),
    INVOICE_TAXI("00000017", "出租车票"),
    INVOICE_SHIP("00000018", "轮船票"),
    INVOICE_MIX("00000019", "发票混排识别"),
    INVOICE_GENERAL("00000020", " 通用机打发票"),
    INVOICE_TOLL("00000021", "过路票"),
    INVOICE_CAR("00000022", "购车发票"),
    INVOICE_USEDCAR("00000023", "二手车发票"),
    INVOICE_TAXPAYMENT("00000024", "完税证明"),
    ORGANIZATION_CODE("00000026", "组织机构代码证"),
    INVOICE_DETECT("00000029", "发票混排检测"),
    GENERALOCR("00000030", "通用印刷体文字识别"),
    HANDWRITINGOCR("00000031", "通用手写体文字识别"),
    SMARTSTRUCTURALOCR("00000032", "智能结构化"),
    PASSPORT("00000040", "护照"),
    PASSPORT_INTERN("00000041", "国际护照"),
    HKMOTWTRAVEL_PERMIT("00000050", "港澳台通行证"),
    HONGKONG_IDCARD("00000051", "香港身份证识别"),
    ENTERPRISE_LICENCE("00000053", "企业证照"),
    HOUSE_ENCUMB("00000060", "他项权证"),
    FORM("00001000", "通用表单"),
    CPS("00001001", "划款指令"),
    CLASSIFY("00001003", "图像分类"),
    FINANCIAL_BILL("00001014", "金融票据")
    ;


    private final String code;
    private final String desc;

    OcrTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public String code() {
        return this.code;
    }

    public String getDesc() {
        return desc;
    }

    public static OcrTypeEnum codeOf(String code) {
        for (OcrTypeEnum enums : OcrTypeEnum.values()) {
            if (enums.code().equalsIgnoreCase(code)) {
                return enums;
            }
        }
        return null;
    }
}

