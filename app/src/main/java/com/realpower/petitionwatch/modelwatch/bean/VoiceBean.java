package com.realpower.petitionwatch.modelwatch.bean;

/**
 * Created by Administrator on 2017/12/14.
 */

public class VoiceBean {
    private String path;
    private String len;
    private boolean isDetail;
    private String loadPath;

    public boolean isDetail() {
        return isDetail;
    }

    public void setDetail(boolean detail) {
        isDetail = detail;
    }

    public String getLoadPath() {
        return loadPath;
    }

    public void setLoadPath(String loadPath) {
        this.loadPath = loadPath;
    }

    public VoiceBean() {
    }

    public VoiceBean(String path, String len) {
        this.path = path;
        this.len = len;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLen() {
        return len;
    }

    public void setLen(String len) {
        this.len = len;
    }
}
