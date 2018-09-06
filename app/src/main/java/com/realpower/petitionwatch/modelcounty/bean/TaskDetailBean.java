package com.realpower.petitionwatch.modelcounty.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/22.
 */

public class TaskDetailBean {

    /**
     * areaId : 130502
     * card : 13042719861218411x
     * cardImage : 暂无
     * districtId : 1
     * districtTaskId : 0
     * endtime : 2018-03-24 00:00:00
     * info : 00:27:41,23:39:41
     * keyValues : [{"label":"监控工作人员","value":"1"}]
     * lasttime : 2018-03-15 13:54:58
     * level : 安全
     * monitoredAreaName : 邢台市桥东区南长街街道
     * monitoredId : 1
     * monitoredName : 王武帅
     * monitoredNum : No390051
     * phone : 18600333914
     * postAddress : 暂无
     * risk : 1
     * sex : 1
     * starttime : 2018-03-15 00:00:00
     * status : 1
     * supervisorId : 1
     * taskAreaName : 邢台市桥东区
     * taskId : 1
     * taskSuperName : 监控工作人员
     * title : 监控行动
     */

    private int areaId;
    private String card;
    private String cardImage;
    private int districtId;
    private int districtTaskId;
    private String endtime;
    private String info;
    private String lasttime;
    private String level;
    private String monitoredAreaName;
    private int monitoredId;
    private String monitoredName;
    private String monitoredNum;
    private String phone;
    private String postAddress;
    private String risk;
    private String sex;
    private String starttime;
    private int status;
    private String supervisorId;
    private String taskAreaName;
    private int taskId;
    private String taskSuperName;
    private String title;
    private List<KeyValuesBean> keyValues;

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCardImage() {
        return cardImage;
    }

    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getDistrictTaskId() {
        return districtTaskId;
    }

    public void setDistrictTaskId(int districtTaskId) {
        this.districtTaskId = districtTaskId;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMonitoredAreaName() {
        return monitoredAreaName;
    }

    public void setMonitoredAreaName(String monitoredAreaName) {
        this.monitoredAreaName = monitoredAreaName;
    }

    public int getMonitoredId() {
        return monitoredId;
    }

    public void setMonitoredId(int monitoredId) {
        this.monitoredId = monitoredId;
    }

    public String getMonitoredName() {
        return monitoredName;
    }

    public void setMonitoredName(String monitoredName) {
        this.monitoredName = monitoredName;
    }

    public String getMonitoredNum() {
        return monitoredNum;
    }

    public void setMonitoredNum(String monitoredNum) {
        this.monitoredNum = monitoredNum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getTaskAreaName() {
        return taskAreaName;
    }

    public void setTaskAreaName(String taskAreaName) {
        this.taskAreaName = taskAreaName;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskSuperName() {
        return taskSuperName;
    }

    public void setTaskSuperName(String taskSuperName) {
        this.taskSuperName = taskSuperName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<KeyValuesBean> getKeyValues() {
        return keyValues;
    }

    public void setKeyValues(List<KeyValuesBean> keyValues) {
        this.keyValues = keyValues;
    }

    public static class KeyValuesBean {
        /**
         * label : 监控工作人员
         * value : 1
         */

        private String label;
        private String value;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
