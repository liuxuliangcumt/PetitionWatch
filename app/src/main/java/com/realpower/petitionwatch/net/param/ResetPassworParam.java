package com.realpower.petitionwatch.net.param;

/**
 * Created by Administrator on 2017/12/22.
 */

public class ResetPassworParam {

    /**
     * code : string
     * phone : string
     * monitoredPwd : string
     */

    private String code;
    private String supervisorPhone;
    private String supervisorPwd;
    private String phone;

    public ResetPassworParam(String code, String phone, String monitoredPwd) {
        this.code = code;
        this.supervisorPhone = phone;
        this.phone = phone;
        this.supervisorPwd = monitoredPwd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getSupervisorPhone() {
        return supervisorPhone;
    }

    public void setSupervisorPhone(String supervisorPhone) {
        this.supervisorPhone = supervisorPhone;
    }

    public String getSupervisorPwd() {
        return supervisorPwd;
    }

    public void setSupervisorPwd(String supervisorPwd) {
        this.supervisorPwd = supervisorPwd;
    }
}
