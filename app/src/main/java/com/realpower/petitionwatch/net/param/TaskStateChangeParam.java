package com.realpower.petitionwatch.net.param;

/**
 * Created by Administrator on 2018/1/3.
 */

public class TaskStateChangeParam {

    public TaskStateChangeParam(String description, int level, int status,
                                int taskId, String imageUrl, String videoUrl) {
        this.description = description;
        this.imageUrl = imageUrl;
        this.level = level;
        this.status = status;
        this.taskId = taskId;
        this.videoUrl = videoUrl;
    }

    /**
     * description : string
     * id : 0
     * imageUrl : string
     * lasttime : 2018-01-03T02:53:04.149Z
     * latitude : string
     * level : 0
     * longitude : string
     * status : 0
     * supervisorId : 0
     * taskId : 0
     * videoUrl : string
     * voiceUrl : string
     */

    private String description;
    private String imageUrl;
    private int level;
    private int status;
    private int taskId;
    private String videoUrl;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
}
