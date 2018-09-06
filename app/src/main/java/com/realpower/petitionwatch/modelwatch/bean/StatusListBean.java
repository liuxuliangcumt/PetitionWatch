package com.realpower.petitionwatch.modelwatch.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/22.
 */

public class StatusListBean implements Serializable {
    /**
     * description : string
     * id : 1
     * imageUrl : link.jpg
     * lasttime : 2017-12-27 16:38:27
     * latitude : sssss
     * level : 1 安全  2 危险
     * longitude : string
     * status : 1 外出  2在家
     * supervisorId : 1
     * taskId : 1
     * videoUrl : link.jpg
     * voiceUrl : link.jpg
     */

    private String description;
    private int id;
    private String imageUrl;
    private String lasttime;
    private String latitude;
    private int level;
    private String longitude;
    private int status;
    private int supervisorId;
    private int taskId;
    private String videoUrl;
    private String voiceUrl;

    @Override
    public String toString() {
        return "StatusListBean{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", lasttime='" + lasttime + '\'' +
                ", latitude='" + latitude + '\'' +
                ", level=" + level +
                ", longitude='" + longitude + '\'' +
                ", status=" + status +
                ", supervisorId=" + supervisorId +
                ", taskId=" + taskId +
                ", videoUrl='" + videoUrl + '\'' +
                ", voiceUrl='" + voiceUrl + '\'' +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLasttime() {
        return lasttime;
    }

    public void setLasttime(String lasttime) {
        this.lasttime = lasttime;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }
}
