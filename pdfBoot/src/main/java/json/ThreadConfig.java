package json;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 线程配置
 */

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

    public int getTaskThread() {
        return taskThread;
    }

    public void setTaskThread(int taskThread) {
        this.taskThread = taskThread;
    }

    public int getRegThread() {
        return regThread;
    }

    public void setRegThread(int regThread) {
        this.regThread = regThread;
    }

    public int getRegFileThread() {
        return regFileThread;
    }

    public void setRegFileThread(int regFileThread) {
        this.regFileThread = regFileThread;
    }
}
