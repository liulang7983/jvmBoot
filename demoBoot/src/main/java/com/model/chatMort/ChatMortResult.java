package com.model.chatMort;

/**
 * 对应动产抵押result标签对象
 */
public class ChatMortResult {

    /*
    抵押人
     */
    String mortgagor;
    /*
    登记编号
     */
    String registerno;
    /*
    登记日期
     */
    String registertime;
    /*
    登记机关
     */
    String registerauthority;

    public String getMortgagor() {
        return mortgagor;
    }

    public void setMortgagor(String mortgagor) {
        this.mortgagor = mortgagor;
    }

    public String getRegisterno() {
        return registerno;
    }

    public void setRegisterno(String registerno) {
        this.registerno = registerno;
    }

    public String getRegistertime() {
        return registertime;
    }

    public void setRegistertime(String registertime) {
        this.registertime = registertime;
    }

    public String getRegisterauthority() {
        return registerauthority;
    }

    public void setRegisterauthority(String registerauthority) {
        this.registerauthority = registerauthority;
    }

    @Override
    public String toString() {
        return "ChatMortResult{" +
                "mortgagor='" + mortgagor + '\'' +
                ", registerno='" + registerno + '\'' +
                ", registertime='" + registertime + '\'' +
                ", registerauthority='" + registerauthority + '\'' +
                '}';
    }
}
