package com.realpower.petitionwatch.net.result;

/**
 * Created by Administrator on 2017/12/20.
 */

public class LoginResult {


    /**
     * msg : success
     * code : //200 正确  403密码错误
     * token : eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNzMxMDM3MzUwMywxOTkxMTAiLCJhdWRpZW5jZSI6IndlYiIsImNyZWF0ZWQiOjE1MTM3MzQzODYxOTEsImV4cCI6MTUxMzgyMDc4Nn0.kgJu-9csRroC-MhzSMSKtWzm3tI0S6bXcXQYDopZv2GImUy2HncV9aZescTVA3t-YI2zuJgV2MnhoV96kRWvFA
     * timestamp : 1513734386196
     * username : 17310373503
     */
    private String account;
    private String userSig;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserSig() {
        return userSig;
    }

    public void setUserSig(String userSig) {
        this.userSig = userSig;
    }

    private String msg;
    private int code;
    private String Authorization;
    private String token;
    private int role;//1市领导  2区县领导  3工作人员  4监控人员 5系统维护人员  6系统审核人员

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getAuthorization() {
        return Authorization;
    }

    public void setAuthorization(String authorization) {
        Authorization = authorization;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "account='" + account + '\'' +
                ", userSig='" + userSig + '\'' +
                ", msg='" + msg + '\'' +
                ", code=" + code +
                ", Authorization='" + Authorization + '\'' +
                ", token='" + token + '\'' +
                ", role=" + role +
                '}';
    }
}
