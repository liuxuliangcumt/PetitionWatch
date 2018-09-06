package com.realpower.petitionwatch.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/26.
 */

public class AddGpsBean {

    /**
     * latitude : 40.047024
     * longitude : 116.305814
     * monitoredId : 1
     * queues : [{"id":1}]
     */

    private String latitude = "";
    private String longitude = "";
    private String monitoredId;
    private List<QueuesBean> queues;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMonitoredId() {
        return monitoredId;
    }

    public void setMonitoredId(String monitoredId) {
        this.monitoredId = monitoredId;
    }

    public List<QueuesBean> getQueues() {
        return queues;
    }

    public void setQueues(List<QueuesBean> queues) {
        this.queues = queues;
    }

    public static class QueuesBean {
        /**
         * id : 1
         */

        private int id;
        private String districtId;
        private String info;
        private String issend;
        private String title;
        private String monitoredId;
        private String supervisorId;

        @Override
        public String toString() {
            return "QueuesBean{" +
                    "id=" + id +
                    ", districtId='" + districtId + '\'' +
                    ", info='" + info + '\'' +
                    ", issend='" + issend + '\'' +
                    ", title='" + title + '\'' +
                    ", monitoredId='" + monitoredId + '\'' +
                    ", supervisorId='" + supervisorId + '\'' +
                    '}';
        }

        public String getDistrictId() {
            return districtId;
        }

        public void setDistrictId(String districtId) {
            this.districtId = districtId;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getIssend() {
            return issend;
        }

        public void setIssend(String issend) {
            this.issend = issend;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public int getId() {

            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    @Override
    public String toString() {
        return "AddGpsBean{" +
                "latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", monitoredId='" + monitoredId + '\'' +
                ", queues=" + queues +
                '}';
    }
}
