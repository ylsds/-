package com.domain;

import org.w3c.dom.ls.LSOutput;

public class User {
    private String usercode;
    private String username;
    private String orgtype;
    private String userpswd;
    private String regdate;

    public User() {
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrgtype() {
        return orgtype;
    }

    public void setOrgtype(String orgtype) {
        this.orgtype = orgtype;
    }

    public String getUserpswd() {
        return userpswd;
    }

    public void setUserpswd(String userpswd) {
        this.userpswd = userpswd;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    @Override
    public String toString() {
        return "User{" +
                "usercode='" + usercode + '\'' +
                ", username='" + username + '\'' +
                ", orgtype='" + orgtype + '\'' +
                ", userpswd='" + userpswd + '\'' +
                ", regdate='" + regdate + '\'' +
                '}';
    }
}
