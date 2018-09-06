package com.realpower.petitionwatch.net.param;

import java.util.Date;

/**
 * Created by Administrator on 2018/3/29.
 */

public class NewMonitorTaskParam {
    private String title;
    private String info;
    private String monitoredId;
    private String supervisorId = "";
    private Date endtimeDate;
    private Date starttimeDate;
    private String starttime;
    private String endtime;
    private String areaId;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public Date getEndtimeDate() {
        return endtimeDate;
    }

    public void setEndtimeDate(Date endtimeDate) {
        this.endtimeDate = endtimeDate;
    }

    public Date getStarttimeDate() {
        return starttimeDate;
    }

    public void setStarttimeDate(Date starttimeDate) {
        this.starttimeDate = starttimeDate;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getMonitoredId() {
        return monitoredId;
    }

    public void setMonitoredId(String monitoredId) {
        this.monitoredId = monitoredId;
    }

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }


}
