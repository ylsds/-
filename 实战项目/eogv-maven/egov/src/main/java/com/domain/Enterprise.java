package com.domain;

public class Enterprise {
   private String orgcode;
   private String regno;
   private String cnname;
   private String enname;
   private String contactman;
   private String contacttel;
   private Integer regcap;
   private Integer outregcap;
   private String regcry;
   private String usercode;
   private String regdate;

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    public String getContactman() {
        return contactman;
    }

    public void setContactman(String contactman) {
        this.contactman = contactman;
    }

    public String getContacttel() {
        return contacttel;
    }

    public void setContacttel(String contacttel) {
        this.contacttel = contacttel;
    }

    public Integer getRegcap() {
        return regcap;
    }

    public void setRegcap(Integer regcap) {
        this.regcap = regcap;
    }

    public Integer getOutregcap() {
        return outregcap;
    }

    public void setOutregcap(Integer outregcap) {
        this.outregcap = outregcap;
    }

    public String getRegcry() {
        return regcry;
    }

    public void setRegcry(String regcry) {
        this.regcry = regcry;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    @Override
    public String toString() {
        return "Enterprise{" +
                "orgcode='" + orgcode + '\'' +
                ", regno='" + regno + '\'' +
                ", cnname='" + cnname + '\'' +
                ", enname='" + enname + '\'' +
                ", contactman='" + contactman + '\'' +
                ", contacttel='" + contacttel + '\'' +
                ", regcap=" + regcap +
                ", outregcap=" + outregcap +
                ", regcry='" + regcry + '\'' +
                ", usercode='" + usercode + '\'' +
                ", regdate='" + regdate + '\'' +
                '}';
    }
}
