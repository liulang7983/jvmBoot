package com.model.constant;

public class LerisConstants {

    public static final String APP = "LERIS";

    public static final String APP_ID = "LERIS";

    public static final String FMS_FOLDER_NAME = "leris";

    /*
    批次识别状态
     */
    /*
     正在识别
     */
    public final static Integer BATCH_IS_OCR = 0;
    /*
     识别成功
     */
    public final static Integer BATCH_IS_FINISH = 1;

    /*
    租赁物状态
     */
    /*
    原始
     */
    public final static int LEASE_INIT_STATUS = 1;
    /*
    合并
     */
    public final static int LEASE_COMBINE_STATUS = 2;

    public static final String FMS_BATCH_STATUS_OPEN = "1";

    /*
    发票信息
     */
    public static final int INVOICE_CORE_YOUTU = 0;
    public static final int INVOICE_CORE_BAIDU = 1;
    public static final int INVOICE_CORE_YY = 2;
    public static final String INVOCR_OCRING = "0";
    public static final String INVOCR_SUCUESS = "1";
    public static final String INVOCR_FAIL = "-1";
    public static final String INVOICE_CHECK_SUCUESS = "0";
    public static final String INVOICE_CHECK_CORE_NB = "0";
    public static final String INVOCR_NOCONFIRM = "0";
    public static final String INVOCR_ISCONFIRM = "1";
    public static final String INVOICE_TYPE_YOUTU = "0";
    public static final String INVOICE_TYPE_BAIDU = "1";
    public static final String OCR_FILE_TYPE_XLS = "xls";
    public static final String OCR_FILE_TYPE_XLSX = "xlsx";
    public static final String OCR_FILE_TYPE_PDF = "pdf";
    public static final int IMAGE_SIZE = 4;


    /*
    发票重复状态：不重复
     */
    public static final String INVOCR_NOREPEAT = "0";
    /*
    发票重复状态：重复
     */
    public static final String INVOCR_ISREPEAT = "1";

    /*
    发票比较状态：不一致
     */
    public static final int INVOICE_COMPARE_FAIL = 0;
    /*
    发票比较状态：一致
     */
    public static final int INVOICE_COMPARE_SUCUESS = 1;

    /*
    发票查验失败
     */
    public static final int INVOICE_CHECK_FAIL = 0;
    /*
    发票查验正常
     */
    public static final int INVOICE_CHECK_NORMAL = 1;

    /*
     中登网微服务
     */
    public final static String DRAW_URL = "/misc/draw";
    public final static String INIT_REGISTER_URL = "/register/initRegister";
    public final static String AMEND_REGISTER_URL = "/register/amendRegister";
    public final static String EXTEND_REGISTER_URL = "/register/extendRegister";
    public final static String TERMINATE_REGISTER_URL = "/register/terminateRegister";
    public final static String QUERY_BY_SUBJECT_URL = "/query/queryBySubject?orgName=";
    public final static String QUERY_BY_GS_SUBJECT_URL = "/query/queryGsBySubject?debttype=1&nameorno=";
    public final static String QUERY_BY_NUM_URL = "/query/queryByNum?registerNo=";
    public final static String QUERY_GS_DETAIL_BY_NUM_URL = "/query/queryGsDetailByNum?debttype=1&";
    public final static String DOWNLOAD_BY_NUM_URL = "/query/queryDownLoadFileByNum";
    /*
     超时时间23s
     */
    public final static Integer TIME_OUT = 35000;
    public final static String UTF_8 = "utf-8";
    public final static String CHARSET_UTF8 = "UTF-8"; //编码

    public final static String REG_NO = "登记证明编号";
    public final static String REG_DATE = "登记时间";
    public final static String INITREG_HEAD = "初始登记编号";
    public final static String REGISTERORG_HEAD = "填表人名称";
    public final static String REGISTERORG_CURRENCY = "财产信息";
    public final static String BUSINESS_TYPE_NAME = "交易业务类型";
    public final static String CONTRACT_NO = "合同号";

    /*
    中登网PDF
     */
    public final static int CHECK_TYPE_ZDW = 0;
    /*
    中登网(附件)
     */
    public final static int CHECK_TYPE_ZDW_FJ = 1;
    /*
    企查查
     */
    public final static int CHECK_TYPE_QCC = 2;

    /*
    查重状态
     */
    /*
     查重中
     */
    public final static int CHECK_STATUS_NONE = 0;
    /*
     已完成/成功
     */
    public final static int CHECK_STATUS_SUCCESS = 1;
    /*
     失败
     */
    public final static int CHECK_STATUS_FAILED = 2;
    /*
     融资租赁
     */
    public final static String BUSINESS_TYPE = "B00000";

    /*
     登记类型
     */
    /*
     初始登记
     */
    public final static String REGISTER_TYPE_INIT = "01";
    /*
     变更登记
     */
    public final static String REGISTER_TYPE_AMEND = "02";
    /*
     展期登记
     */
    public final static String REGISTER_TYPE_EXTEND = "03";
    /*
     注销登记
     */
    public final static String REGISTER_TYPE_TERMINATE = "05";

    /*
    表格识别配置
     */
    /*
    youtu离线识别
     */
    public final static int YOUTU_LEAVE_TABLE_CORE = 0;
    /*
    youtu在线识别
     */
    public final static int YOUTU_HIGH_PRECISION_TABLE_CORE = 1;
    /*
     合合表格识别
     */
    public final static int HH_TABLE_CORE = 3;

    public static final String STATUS = "status";
    public static final String ANGLE = "angle";
    public static final String ITEMS = "items";
    public static final String CLASS = "class";
    public static final String RECOGNIZE_WARN_MSG = "recognize_warn_msg";
    public static final String RECOGNIZE_WARN_CODE = "recognize_warn_code";
    public static final String CODE = "code";
    public static final String DATA = "data";
    public static final String MESSAGE = "message";
    public static final String SEQ = "seq";
    public static final String JSON = ".json";
    public static final String SESSIONID = "session_id";
    public static final String FMS_LEASE = "Contract";

}
