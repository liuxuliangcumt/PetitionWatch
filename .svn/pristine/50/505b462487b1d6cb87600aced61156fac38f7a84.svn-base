package com.realpower.petitionwatch.modelwatch.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */

public class WatcherTaskBean implements Serializable {


    /**
     * areaId : 1305
     * endtime : 2017-12-27 14:59:22
     * info : 注意隐蔽
     * lasttime : 2017-12-27 16:51:43
     * monitored : {"areaId":1305,"monitoredCategory":"21","monitoredCreatetime":"2018-01-08 18:06:55","monitoredCurrentaddress":"山东","monitoredId":1,"monitoredIdcard":"13043419911026605x","monitoredIdcardimage":"link.jpg","monitoredLastimage":"link.jpg,link.jpg,link.jpg","monitoredPhone":"15566667777","monitoredPostaddress":"河北","monitoredRealname":"张三","monitoredRisk":1,"monitoredSex":1,"monitoredUsedname":"三儿"}
     * monitoredId : 1
     * starttime : 2017-12-04 14:59:18
     * status : 1
     * supervisorId : 1,3
     * taskId : 1
     * title : 监控任务1
     */

    private int areaId;
    private String endtime;
    private String info;
    private String lasttime;
    private MonitoredBean monitored;
    private int monitoredId;
    private String starttime;
    private int status;//3正常结束 1未结束  2提前结束
    private String supervisorId;
    private int taskId;
    private String title;
    private List<StatusListBean> statusList=new ArrayList<>();

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLasttime() {
        return lasttime;
    }

    public void setLasttime(String lasttime) {
        this.lasttime = lasttime;
    }

    public MonitoredBean getMonitored() {
        return monitored;
    }

    public void setMonitored(MonitoredBean monitored) {
        this.monitored = monitored;
    }

    public int getMonitoredId() {
        return monitoredId;
    }

    public void setMonitoredId(int monitoredId) {
        this.monitoredId = monitoredId;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<StatusListBean> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<StatusListBean> statusList) {
        this.statusList = statusList;
    }

    public static class MonitoredBean implements Serializable {
        /**
         * areaId : 1305
         * monitoredCategory : 21
         * monitoredCreatetime : 2018-01-08 18:06:55
         * monitoredCurrentaddress : 山东
         * monitoredId : 1
         * monitoredIdcard : 13043419911026605x
         * monitoredIdcardimage : link.jpg
         * monitoredLastimage : link.jpg,link.jpg,link.jpg
         * monitoredPhone : 15566667777
         * monitoredPostaddress : 河北
         * monitoredRealname : 张三
         * monitoredRisk : 1
         * monitoredSex : 1
         * monitoredUsedname : 三儿
         */
        private String longitude = "";
        private String latitude = "";

        @Override
        public String toString() {
            return "MonitoredBean{" +
                    "longitude='" + longitude + '\'' +
                    ", latitude='" + latitude + '\'' +
                    ", areaId=" + areaId +
                    ", monitoredCategory='" + monitoredCategory + '\'' +
                    ", monitoredCreatetime='" + monitoredCreatetime + '\'' +
                    ", monitoredCurrentaddress='" + monitoredCurrentaddress + '\'' +
                    ", monitoredId=" + monitoredId +
                    ", monitoredIdcard='" + monitoredIdcard + '\'' +
                    ", monitoredIdcardimage='" + monitoredIdcardimage + '\'' +
                    ", monitoredLastimage='" + monitoredLastimage + '\'' +
                    ", monitoredPhone='" + monitoredPhone + '\'' +
                    ", monitoredPostaddress='" + monitoredPostaddress + '\'' +
                    ", monitoredRealname='" + monitoredRealname + '\'' +
                    ", monitoredRisk=" + monitoredRisk +
                    ", monitoredSex=" + monitoredSex +
                    ", monitoredUsedname='" + monitoredUsedname + '\'' +
                    '}';
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        private int areaId;
        private String monitoredCategory;
        private String monitoredCreatetime;
        private String monitoredCurrentaddress;
        private int monitoredId;
        private String monitoredIdcard;
        private String monitoredIdcardimage;
        private String monitoredLastimage;
        private String monitoredPhone;
        private String monitoredPostaddress;
        private String monitoredRealname;
        private int monitoredRisk;
        private int monitoredSex;
        private String monitoredUsedname;

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public String getMonitoredCategory() {
            return monitoredCategory;
        }

        public void setMonitoredCategory(String monitoredCategory) {
            this.monitoredCategory = monitoredCategory;
        }

        public String getMonitoredCreatetime() {
            return monitoredCreatetime;
        }

        public void setMonitoredCreatetime(String monitoredCreatetime) {
            this.monitoredCreatetime = monitoredCreatetime;
        }

        public String getMonitoredCurrentaddress() {
            return monitoredCurrentaddress;
        }

        public void setMonitoredCurrentaddress(String monitoredCurrentaddress) {
            this.monitoredCurrentaddress = monitoredCurrentaddress;
        }

        public int getMonitoredId() {
            return monitoredId;
        }

        public void setMonitoredId(int monitoredId) {
            this.monitoredId = monitoredId;
        }

        public String getMonitoredIdcard() {
            return monitoredIdcard;
        }

        public void setMonitoredIdcard(String monitoredIdcard) {
            this.monitoredIdcard = monitoredIdcard;
        }

        public String getMonitoredIdcardimage() {
            return monitoredIdcardimage;
        }

        public void setMonitoredIdcardimage(String monitoredIdcardimage) {
            this.monitoredIdcardimage = monitoredIdcardimage;
        }

        public String getMonitoredLastimage() {
            return monitoredLastimage;
        }

        public void setMonitoredLastimage(String monitoredLastimage) {
            this.monitoredLastimage = monitoredLastimage;
        }

        public String getMonitoredPhone() {
            return monitoredPhone;
        }

        public void setMonitoredPhone(String monitoredPhone) {
            this.monitoredPhone = monitoredPhone;
        }

        public String getMonitoredPostaddress() {
            return monitoredPostaddress;
        }

        public void setMonitoredPostaddress(String monitoredPostaddress) {
            this.monitoredPostaddress = monitoredPostaddress;
        }

        public String getMonitoredRealname() {
            return monitoredRealname;
        }

        public void setMonitoredRealname(String monitoredRealname) {
            this.monitoredRealname = monitoredRealname;
        }

        public int getMonitoredRisk() {
            return monitoredRisk;
        }

        public void setMonitoredRisk(int monitoredRisk) {
            this.monitoredRisk = monitoredRisk;
        }

        public int getMonitoredSex() {
            return monitoredSex;
        }

        public void setMonitoredSex(int monitoredSex) {
            this.monitoredSex = monitoredSex;
        }

        public String getMonitoredUsedname() {
            return monitoredUsedname;
        }

        public void setMonitoredUsedname(String monitoredUsedname) {
            this.monitoredUsedname = monitoredUsedname;
        }
    }


}
