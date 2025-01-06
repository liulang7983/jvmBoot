package model;


import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ming.li
 * @date 2022/9/7 15:27
 */

public class Relation {
    /*
     中登主键id（nlp租赁物）
     */
    private Long id;
    /*
      中登证明id
    */
    private Long nlpRegId;

    private Long nlpRfId;

    /**
     * nlpRfId有值时的坐标
     */
    private String rt;
    /*
     重复度
     */
    private BigDecimal percent;
    /*
     重复状态：0-不重复，1-重复
     */
    private Integer status;
    /*
    查重结论说明
     */
    private String msg;
    /*
    修改状态操作员
     */
    private String user;
    /*
    修改状态时间
     */
    private Date time;
    /*
     租赁物数量
     */
    //转json时忽略此字段
    @JSONField(serialize = false)
    private Float num;

    /**
     * 风险级别,1,高风险;2,中风险
     */
    private int riskLevel = 1;

    //匹配内容
    private String text;

    public Relation(Long id, BigDecimal percent, Integer status, String msg, Float num) {
        this.id = id;
        this.percent = percent;
        this.status = status;
        this.msg = msg;
        this.num = num;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNlpRegId() {
        return nlpRegId;
    }

    public void setNlpRegId(Long nlpRegId) {
        this.nlpRegId = nlpRegId;
    }

    public Long getNlpRfId() {
        return nlpRfId;
    }

    public void setNlpRfId(Long nlpRfId) {
        this.nlpRfId = nlpRfId;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Float getNum() {
        return num;
    }

    public void setNum(Float num) {
        this.num = num;
    }

    public int getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(int riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
