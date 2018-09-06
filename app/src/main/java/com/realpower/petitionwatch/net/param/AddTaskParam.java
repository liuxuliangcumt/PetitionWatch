package com.realpower.petitionwatch.net.param;

import java.util.Date;

/**
 * Created by Administrator on 2018/4/10.
 * 市领导新建任务
 */

public class AddTaskParam {
    private Date endtimeDate;
    private Date starttimeDate;
    private String endtime ;
    private String starttime;
    private String title;
    private String info;
    private String districtId;

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
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

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
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
}
