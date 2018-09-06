package com.realpower.petitionwatch.modelcounty.bean;

/**
 * Created by Administrator on 2018/3/27.
 */

public class AlarmDetailBean {

    /**
     * alarmId : 4
     * areaId : 130502
     * areaName : 邢台市桥东区
     * category : 一键报警
     * id : 6
     * monitoredId : 1
     * monitoredNames : 王武帅
     * num : No.39005868
     * supervisorAlarm : {"areaId":130502,"description":"测试其它信息","id":4,"imageUrl":"36d47cfc-e26b-4c43-adfa-5e3f52e46656.jpg","monitoredId":"1","status":1,"supervisorId":1,"videoUrl":"a08c01f5-bb11-4fbb-b999-d8a4f750178c.mp4","voiceUrl":"ad6474a3-897a-41a5-8583-f1692a5eeeb3.amr"}
     * supervisorId : 1
     * supervisorName : 监控工作人员
     * time : 2018-03-27 14:25:53
     */
    private int commitStatus;//2 未上报  1已上报

    public int getCommitStatus() {
        return commitStatus;
    }

    public void setCommitStatus(int commitStatus) {
        this.commitStatus = commitStatus;
    }

    private int alarmId;
    private String areaId;
    private String areaName;
    private String category;
    private int id;
    private String monitoredId;
    private String monitoredNames;
    private String num;
    private SupervisorAlarmBean supervisorAlarm;
    private String supervisorId;
    private String supervisorName;
    private String time;

    public int getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonitoredId() {
        return monitoredId;
    }

    public void setMonitoredId(String monitoredId) {
        this.monitoredId = monitoredId;
    }

    public String getMonitoredNames() {
        return monitoredNames;
    }

    public void setMonitoredNames(String monitoredNames) {
        this.monitoredNames = monitoredNames;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public SupervisorAlarmBean getSupervisorAlarm() {
        return supervisorAlarm;
    }

    public void setSupervisorAlarm(SupervisorAlarmBean supervisorAlarm) {
        this.supervisorAlarm = supervisorAlarm;
    }

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static class SupervisorAlarmBean {
        /**
         * areaId : 130502
         * description : 测试其它信息
         * id : 4
         * imageUrl : 36d47cfc-e26b-4c43-adfa-5e3f52e46656.jpg
         * monitoredId : 1
         * status : 1
         * supervisorId : 1
         * videoUrl : a08c01f5-bb11-4fbb-b999-d8a4f750178c.mp4
         * voiceUrl : ad6474a3-897a-41a5-8583-f1692a5eeeb3.amr
         */

        private int areaId;
        private String description = "";
        private int id;
        private String imageUrl = "";
        private String monitoredId;
        private int status;
        private int supervisorId;
        private String videoUrl = "";
        private String voiceUrl = "";

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
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

        public int getSupervisorId() {
            return supervisorId;
        }

        public void setSupervisorId(int supervisorId) {
            this.supervisorId = supervisorId;
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
}
