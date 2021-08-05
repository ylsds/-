package com.domain;

public class Invest {
    private String invregnum;
    private String invname;
    private String cty;
    private String orgcode;
    private String contactman;
    private String contacttel;
    private String email;
    private String remark;
    private String regdate;
    private String username;
    private String usercode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getInvregnum() {
        return invregnum;
    }

    public void setInvregnum(String invregnum) {
        this.invregnum = invregnum;
    }

    public String getInvname() {
        return invname;
    }

    public void setInvname(String invname) {
        this.invname = invname;
    }

    public String getCty() {
        return cty;
    }

    public void setCty(String cty) {
        this.cty = cty;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Invest{" +
                "invregnum=" + invregnum +
                ", invname='" + invname + '\'' +
                ", cty='" + cty + '\'' +
                ", orgcode='" + orgcode + '\'' +
                ", contactman='" + contactman + '\'' +
                ", contacttel='" + contacttel + '\'' +
                ", email='" + email + '\'' +
                ", remark='" + remark + '\'' +
                ", regdate='" + regdate + '\'' +
                '}';
    }
}
