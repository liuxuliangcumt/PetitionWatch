package com.realpower.petitionwatch.net.param;

/**
 * Created by Administrator on 2017/12/23.
 */

public class ChangePhoneParam {

    /**
     * areaId : 0
     * code : string
     * monitoredAddress : string
     * monitoredAssessStatus : 0
     * monitoredId : 0
     * monitoredIdcard : string
     * monitoredPhone : string
     * monitoredPwd : string
     * monitoredRealname : string
     * permanentAddress : string
     * phone : string
     * sysUserId : 0
     */
    private String monitoredPhone;//原手机号
    private String code;
    private String supervisorPwd;
    private String supervisorPhone;

    public ChangePhoneParam(String monitoredPhone, String code, String monitoredPwd, String phone) {
        this.monitoredPhone = monitoredPhone;
        this.code = code;
        this.supervisorPwd = monitoredPwd;
        this.supervisorPhone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMonitoredPhone() {
        return monitoredPhone;
    }

    public void setMonitoredPhone(String monitoredPhone) {
        this.monitoredPhone = monitoredPhone;
    }

    public String getSupervisorPwd() {
        return supervisorPwd;
    }

    public void setSupervisorPwd(String supervisorPwd) {
        this.supervisorPwd = supervisorPwd;
    }

    public String getSupervisorPhone() {
        return supervisorPhone;
    }

    public void setSupervisorPhone(String supervisorPhone) {
        this.supervisorPhone = supervisorPhone;
    }
}
