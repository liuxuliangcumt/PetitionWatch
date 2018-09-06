package com.realpower.petitionwatch.modelstaff.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/18.
 */

public class SuggestionBean {

    /**
     * isFirstPage : true
     * isLastPage : true
     * list : [{"area":{"areaId":130502001,"areaName":"南长街街道","nodeId":"130502001","nodeName":"南长街街道","nodeParentId":""},"areaId":130502001,"createtime":"2017-12-20 14:23:16","currentDepartno":0,"currentStatus":3,"finishtime":"string","leaderId":0,"monitored":{"areaId":130502001,"monitoredAddress":"详细地址的方法","monitoredId":7,"monitoredIdcard":"130434199110266056","monitoredPhone":"13331040525","monitoredRealname":"刘旭亮"},"monitoredId":7,"staffId":1,"suggestionCommentLevel":0,"suggestionContactInformation":"string","suggestionFinishedUrl":"string","suggestionId":1,"suggestionImageUrl":"http://220.195.3.19:8080/upload/222222.jpg","suggestionInfo":"string","suggestionNum":"9527","suggestionReminders":12,"suggestionRemindersLasttime":1514285371000,"suggestionResult":"今天天气很好","suggestionTitle":"string","suggestionVideoUrl":"string","suggestionVoiceUrl":"string"}]
     */

    private boolean isFirstPage;
    private boolean isLastPage;
    private List<ListBean> list;

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * area : {"areaId":130502001,"areaName":"南长街街道","nodeId":"130502001","nodeName":"南长街街道","nodeParentId":""}
         * areaId : 130502001
         * createtime : 2017-12-20 14:23:16
         * currentDepartno : 0
         * currentStatus : 3
         * finishtime : string
         * leaderId : 0
         * monitored : {"areaId":130502001,"monitoredAddress":"详细地址的方法","monitoredId":7,"monitoredIdcard":"130434199110266056","monitoredPhone":"13331040525","monitoredRealname":"刘旭亮"}
         * monitoredId : 7
         * staffId : 1
         * suggestionCommentLevel : 0
         * suggestionContactInformation : string
         * suggestionFinishedUrl : string
         * suggestionId : 1
         * suggestionImageUrl : http://220.195.3.19:8080/upload/222222.jpg
         * suggestionInfo : string
         * suggestionNum : 9527
         * suggestionReminders : 12
         * suggestionRemindersLasttime : 1514285371000
         * suggestionResult : 今天天气很好
         * suggestionTitle : string
         * suggestionVideoUrl : string
         * suggestionVoiceUrl : string
         */

        private AreaBean area;
        private int areaId;
        private String createtime;
        private int currentDepartno;
        private int currentStatus;
        private String finishtime;
        private int leaderId;
        private MonitoredBean monitored;
        private int monitoredId;
        private int staffId;
        private int suggestionCommentLevel;
        private String suggestionContactInformation;
        private String suggestionFinishedUrl;
        private int suggestionId;
        private String suggestionImageUrl;
        private String suggestionInfo;
        private String suggestionNum;
        private int suggestionReminders;
        private long suggestionRemindersLasttime;
        private String suggestionResult;
        private String suggestionTitle;
        private String suggestionVideoUrl;
        private String suggestionVoiceUrl;

        public AreaBean getArea() {
            return area;
        }

        public void setArea(AreaBean area) {
            this.area = area;
        }

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getCurrentDepartno() {
            return currentDepartno;
        }

        public void setCurrentDepartno(int currentDepartno) {
            this.currentDepartno = currentDepartno;
        }

        public int getCurrentStatus() {
            return currentStatus;
        }

        public void setCurrentStatus(int currentStatus) {
            this.currentStatus = currentStatus;
        }

        public String getFinishtime() {
            return finishtime;
        }

        public void setFinishtime(String finishtime) {
            this.finishtime = finishtime;
        }

        public int getLeaderId() {
            return leaderId;
        }

        public void setLeaderId(int leaderId) {
            this.leaderId = leaderId;
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

        public int getStaffId() {
            return staffId;
        }

        public void setStaffId(int staffId) {
            this.staffId = staffId;
        }

        public int getSuggestionCommentLevel() {
            return suggestionCommentLevel;
        }

        public void setSuggestionCommentLevel(int suggestionCommentLevel) {
            this.suggestionCommentLevel = suggestionCommentLevel;
        }

        public String getSuggestionContactInformation() {
            return suggestionContactInformation;
        }

        public void setSuggestionContactInformation(String suggestionContactInformation) {
            this.suggestionContactInformation = suggestionContactInformation;
        }

        public String getSuggestionFinishedUrl() {
            return suggestionFinishedUrl;
        }

        public void setSuggestionFinishedUrl(String suggestionFinishedUrl) {
            this.suggestionFinishedUrl = suggestionFinishedUrl;
        }

        public int getSuggestionId() {
            return suggestionId;
        }

        public void setSuggestionId(int suggestionId) {
            this.suggestionId = suggestionId;
        }

        public String getSuggestionImageUrl() {
            return suggestionImageUrl;
        }

        public void setSuggestionImageUrl(String suggestionImageUrl) {
            this.suggestionImageUrl = suggestionImageUrl;
        }

        public String getSuggestionInfo() {
            return suggestionInfo;
        }

        public void setSuggestionInfo(String suggestionInfo) {
            this.suggestionInfo = suggestionInfo;
        }

        public String getSuggestionNum() {
            return suggestionNum;
        }

        public void setSuggestionNum(String suggestionNum) {
            this.suggestionNum = suggestionNum;
        }

        public int getSuggestionReminders() {
            return suggestionReminders;
        }

        public void setSuggestionReminders(int suggestionReminders) {
            this.suggestionReminders = suggestionReminders;
        }

        public long getSuggestionRemindersLasttime() {
            return suggestionRemindersLasttime;
        }

        public void setSuggestionRemindersLasttime(long suggestionRemindersLasttime) {
            this.suggestionRemindersLasttime = suggestionRemindersLasttime;
        }

        public String getSuggestionResult() {
            return suggestionResult;
        }

        public void setSuggestionResult(String suggestionResult) {
            this.suggestionResult = suggestionResult;
        }

        public String getSuggestionTitle() {
            return suggestionTitle;
        }

        public void setSuggestionTitle(String suggestionTitle) {
            this.suggestionTitle = suggestionTitle;
        }

        public String getSuggestionVideoUrl() {
            return suggestionVideoUrl;
        }

        public void setSuggestionVideoUrl(String suggestionVideoUrl) {
            this.suggestionVideoUrl = suggestionVideoUrl;
        }

        public String getSuggestionVoiceUrl() {
            return suggestionVoiceUrl;
        }

        public void setSuggestionVoiceUrl(String suggestionVoiceUrl) {
            this.suggestionVoiceUrl = suggestionVoiceUrl;
        }

        public static class AreaBean {
            /**
             * areaId : 130502001
             * areaName : 南长街街道
             * nodeId : 130502001
             * nodeName : 南长街街道
             * nodeParentId :
             */

            private int areaId;
            private String areaName;
            private String nodeId;
            private String nodeName;
            private String nodeParentId;

            public int getAreaId() {
                return areaId;
            }

            public void setAreaId(int areaId) {
                this.areaId = areaId;
            }

            public String getAreaName() {
                return areaName;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }

            public String getNodeId() {
                return nodeId;
            }

            public void setNodeId(String nodeId) {
                this.nodeId = nodeId;
            }

            public String getNodeName() {
                return nodeName;
            }

            public void setNodeName(String nodeName) {
                this.nodeName = nodeName;
            }

            public String getNodeParentId() {
                return nodeParentId;
            }

            public void setNodeParentId(String nodeParentId) {
                this.nodeParentId = nodeParentId;
            }
        }

        public static class MonitoredBean {
            /**
             * areaId : 130502001
             * monitoredAddress : 详细地址的方法
             * monitoredId : 7
             * monitoredIdcard : 130434199110266056
             * monitoredPhone : 13331040525
             * monitoredRealname : 刘旭亮
             */

            private int areaId;
            private String monitoredAddress;
            private int monitoredId;
            private String monitoredIdcard;
            private String monitoredPhone;
            private String monitoredRealname;

            public int getAreaId() {
                return areaId;
            }

            public void setAreaId(int areaId) {
                this.areaId = areaId;
            }

            public String getMonitoredAddress() {
                return monitoredAddress;
            }

            public void setMonitoredAddress(String monitoredAddress) {
                this.monitoredAddress = monitoredAddress;
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

            public String getMonitoredPhone() {
                return monitoredPhone;
            }

            public void setMonitoredPhone(String monitoredPhone) {
                this.monitoredPhone = monitoredPhone;
            }

            public String getMonitoredRealname() {
                return monitoredRealname;
            }

            public void setMonitoredRealname(String monitoredRealname) {
                this.monitoredRealname = monitoredRealname;
            }
        }
    }
}
