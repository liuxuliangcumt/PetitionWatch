package com.realpower.petitionwatch.net.param;

/**
 * Created by Administrator on 2018/1/8.
 */

public class AlarmParam {

    /**
     * areaId : 0
     * description : string
     * id : 0
     * imageUrl : string
     * monitoredId : string
     * status : 0
     * supervisorId : 0
     * videoUrl : string
     * voiceUrl : string
     */

    private String description;
    private String id;
    private String imageUrl;
    private String monitoredId;
    private int status;
    private String videoUrl;
    private String voiceUrl;

    public AlarmParam(String description, String monitoredId, int status,
                      String imageUrl, String videoUrl, String voiceUrl) {
        this.description = description;
        this.imageUrl = imageUrl;
        this.monitoredId = monitoredId;
        this.status = status;
        this.videoUrl = videoUrl;
        this.voiceUrl = voiceUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMonitoredId() {
        return monitoredId;
    }

    public void setMonitoredId(String monitoredId) {
        this.monitoredId = monitoredId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
