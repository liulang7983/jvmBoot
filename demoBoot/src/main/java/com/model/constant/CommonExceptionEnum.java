package com.model.constant;

public enum CommonExceptionEnum {

    /*
    服务器相关错误
     */
    SYSTEM_ERROR("010101", "系统错误"),
    SERVICE_ERROR("010102", "服务内部错误"),
    RESPONSE_CONVERT_ERROR("010103", "返回结果转换异常"),
    SERVICE_BIZ_ERROR("010104", "服务端业务处理异常"),
    NOT_FOUND("010105", "访问路径不存在"),
    OCR_SERVER_CONNECT_ERROR("010106","OCR服务器连接失败,请检查OCR服务器是否启动!"),

    /*
    财报模板相关错误
     */
    TP_CODE_IS_EMPTY("010201","财报模板编码不能为空"),
    TP_CODE_IS_EXIST("010202","财报模板编码已经存在"),
    TP_SEARCH_RESULT_IS_EMPTY("010203","财报模板不存在"),
    TP_FORMAT_ERROR("010204","财报模板格式错误"),
    TP_INDUSTRY_AND_TYPE_IS_EXIST("010205","已经存在该行业和模板类型的财报模板"),
    TP_TYPE_ID_NOT_EXIST("010206","不存在该模板类型"),

    TP_TYPE_CODE_IS_EMPTY("010210","财报模板类型ID不能为空"),
    TP_TYPE_CODE_IS_EXIST("010211","财报模板类型ID已经存在"),

    COMMON_SUBJECT_NAME_IS_EXIST("010220","标准科目名称已经存在"),

    /*
    财报模板科目相关错误
     */
    TP_ADD_EMPTY_SUBJECT_ERROR("010301", "财报模板 新增空白科目发生异常"),
    TP_REMOVE_SUBJECT_ROW_ERROR("010302","财报模板 删除科目行发生异常"),
    TP_SUBJECT_REBUILD_PINYIN_ERROR("010303","财报模板 科目重新生成拼音发生异常"),
    TP_SUBJECT_CHECK_CODE_ERROR("010304","财报模板 检查科目编码异常"),
    TP_SUBJECT_LIST_IS_EMPTY("010305","财报模板 科目列表为空"),
    TP_SUBJECT_SC_IS_EMPTY("010306","财报模板 科目编号为空"),
    TP_SUBJECT_COLUMN_IS_NOT_FOUND("010307","财报模板 列名称对应的表头未找到"),
    TP_SUBJECT_LIST_HEAD_IS_EMPTY("010308","财报模板 请先配置科目列表头"),

    /*
     模板配置
     */
    TP_SUBJECT_DOWNLOAD_FILE_ERROR("010309","文件下载异常"),
    TP_SUBJECT_IS_NOT_EXIST("010310","不存在该行业下的科目模板"),

    /*
    报表期次相关错误
     */
    FRSET_UUID_IS_EMPTY("010401","报表期次UUID为空"),
    FRSET_SEARCH_RESULT_IS_EMPTY("010402","报表期次不存在"),

    /*
    财报任务相关错误
     */
    FR_SEARCH_RESULT_IS_EMPTY("010501","财报不存在"),
    FR_JSON_FILE_NOT_FOUND("010502","财报JSON文件不存在"),

    /*
     行业相关错误
     */
    FAS_INDUSTRY_TENANTID_OR_USERID_EMPTY("010503","租户为空或当前用户为空"),
    FAS_INDUSTRY_EXIST("010504","当前行业已存在"),
    FAS_TENANTID_EMPTY("010505","租户为空"),

    /*
    影像文件的相关错误
     */
    FMS_FILE_LIST_IS_EMPTY("010601","识别影像文件记录列表为空"),
    FMS_DIRECTORY_CREATE_ERROR("010602","创建财报任务文件夹失败"),
    FMS_SAVE_LOCAL_FILES_TO_FMS_ERROR("010603","根据父文件信息创建一个新的本地临时子文件错误"),
    FMS_SAVE_UPLOAD_FILES_TO_FMS_ERROR("010604","上传文件到财报文件系统错误"),
    FMS_SEARCH_RESULT_IS_EMPTY("010605","影像文件记录不存在"),
    FMS_FILE_IS_NOT_FOUND("010606","影像文件不存在"),
    FMS_FILE_READ_ERROR("010607","影像文件读取失败"),
    FMS_FILE_UPLOAD_SAVE_TO_DISK_ERROR("010608","上传文件保存失败"),
    FMS_FILE_UPLOAD_TYPE_VERIFY_ERROR("010609","上传文件不存在或文件类型不支持"),
    FMS_FILE_UPLOAD_LENGTH_OUT_OF_BOUNDS("010610","上传影像文件个数不能超过12张"),
    FMS_FILE_SELECT_LIST_IS_EMPTY("010611","影像文件选择列表为空"),
    FMS_FILE_IMG_CUT_ERROR("010612","影像文件裁剪失败,请再尝试几次"),
    FMS_FILE_TYPE_READ_ERROR("010613","影像文件类别读取错误"),
    FMS_FILE_SAVE_CUT_IMG_ERROR("010614","影像文件裁剪保存失败"),
    FMS_OUT_PUT_FILE_TYPE_ERROR("010615", "导出文件类型不对，应为xls或xlsx"),
    FMS_CROP_CELL_FILE_IS_NOT_FOUND_ERROR("010616", "单元格的影像截切文件查找失败，请使用默认空白影像"),
    FMS_FILE_UPLOAD_TO_DISK_ERROR("010617","上传文件失败"),

    /*
    PDF操作的相关错误
     */
    PDF_PAGE_TOO_MUCH_NOT_SPLIT("010701","请手动拆分pdf页码"),
    PDF_PAGE_SPLIT_ERROR("010702","PDF拆分图像页错误"),
    PDF_PAGE_SPLIT_RANGE_IS_ERROR("010703","PDF拆分页码范围错误"),
    PDF_PAGE_INDEX_IS_EMPTY("010704","PDF页码不存在"),
    PDF_PAGE_INDEX_START_ERROR("010705","PDF页码从1开始，不能从0开始"),

    /*
    其他
     */
    OTHER_DICT_NOT_EXIST("010801","数据字典不存在'[STATICRATETYPE]'对应的值或者值为空"),

    /*
    视频资源不存在
     */
    FMS_FILE_NOT_EXIST("010901","视频资源不存!"),


    /*
     Excel
     */
    FAS_EXCEL_READ_REEOR("010902","excel 读取错误!"),
    ;

    /**
     * 错误编码
     */
    String code;
    /**
     * 错误信息
     */
    String message;

    CommonExceptionEnum(String code, String message){
        this.code=code;
        this.message=message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
