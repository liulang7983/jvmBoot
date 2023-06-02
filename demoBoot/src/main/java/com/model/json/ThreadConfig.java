package com.model.json;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 线程配置
 */
@Getter
@Setter
@NoArgsConstructor
public class ThreadConfig {

    /**
     * 任务线程池数量
     */
    @JSONField(name = "task_thread")
    private int taskThread;

    /**
     * 登记证明线程池数量
     */
    @JSONField(name = "reg_thread")
    private int regThread;

    /**
     * 附件线程池数量
     */
    @JSONField(name = "reg_file_thread")
    private int regFileThread;

}
