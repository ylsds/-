package com.domain;

public class Auth {
    private String orgcode;
    private String remark;
    private String contactman;
    private String contacttel;
    private String filename;
    private String fileremark;
    private String authno;
    private String usercode;
    private String feedback;
    private String regdate;
    private String regcry;
    private Integer regcap;

    public Integer getRegcap() {
        return regcap;
    }

    public void setRegcap(Integer regcap) {
        this.regcap = regcap;
    }

    public String getRegcry() {
        return regcry;
    }

    public void setRegcry(String regcry) {
        this.regcry = regcry;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileremark() {
        return fileremark;
    }

    public void setFileremark(String fileremark) {
        this.fileremark = fileremark;
    }

    public String getAuthno() {
        return authno;
    }

    public void setAuthno(String authno) {
        this.authno = authno;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "Auth{" +
                "orgcode='" + orgcode + '\'' +
                ", remark='" + remark + '\'' +
                ", contactman='" + contactman + '\'' +
                ", contacttel='" + contacttel + '\'' +
                ", filename='" + filename + '\'' +
                ", fileremark='" + fileremark + '\'' +
                ", authno='" + authno + '\'' +
                ", usercode='" + usercode + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
