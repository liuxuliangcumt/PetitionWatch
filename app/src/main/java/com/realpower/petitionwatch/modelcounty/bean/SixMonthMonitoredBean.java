package com.realpower.petitionwatch.modelcounty.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */

public class SixMonthMonitoredBean {

    private List<String> time;
    private List<String> monitored;

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public List<String> getMonitored() {
        return monitored;
    }

    public void setMonitored(List<String> monitored) {
        this.monitored = monitored;
    }
}
