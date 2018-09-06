package com.realpower.petitionwatch.modelwatch.bean;

/**
 * Created by Administrator on 2017/12/4.
 */

public class TaskDetailPicBean {
    private String time;
    private String url;

    public TaskDetailPicBean(String time, String url) {
        this.time = time;
        this.url = url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
