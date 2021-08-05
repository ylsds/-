package com.domain;

public class En_Inv {
    private String orgcode;
    private String invregnum;
    private Integer regcap;
    private Integer scale;

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public String getInvregnum() {
        return invregnum;
    }

    public void setInvregnum(String invregnum) {
        this.invregnum = invregnum;
    }

    public Integer getRegcap() {
        return regcap;
    }

    public void setRegcap(Integer regcap) {
        this.regcap = regcap;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    @Override
    public String toString() {
        return "En_Inv{" +
                "orgcode='" + orgcode + '\'' +
                ", invregnum='" + invregnum + '\'' +
                ", regcap=" + regcap +
                ", scale=" + scale +
                '}';
    }
}
