package com.realpower.petitionwatch.modelcounty.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */

public class SixMonthMonitoredBean {

    private List<String> time;
    private List<String> monitored;
    private List<String> appeal;
    private List<String> suggestion;

    public List<String> getAppeal() {
        return appeal;
    }

    public void setAppeal(List<String> appeal) {
        this.appeal = appeal;
    }

    public List<String> getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(List<String> suggestion) {
        this.suggestion = suggestion;
    }

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
