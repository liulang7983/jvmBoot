package com.model.chatMort;

/**
 * 抵押物
 */
public class Collateral {
    /*
    抵押物名称
     */
    private String guaName;
    /*
    所有权归属
     */
    private String own;
    /*
    概况
     */
    private String guaDes;

    public String getGuaName() {
        return guaName;
    }

    public void setGuaName(String guaName) {
        this.guaName = guaName;
    }

    public String getOwn() {
        return own;
    }

    public void setOwn(String own) {
        this.own = own;
    }

    public String getGuaDes() {
        return guaDes;
    }

    public void setGuaDes(String guaDes) {
        this.guaDes = guaDes;
    }

    @Override
    public String toString() {
        return "Collateral{" +
                "guaName='" + guaName + '\'' +
                ", own='" + own + '\'' +
                ", guaDes='" + guaDes + '\'' +
                '}';
    }
}
