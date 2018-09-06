package com.realpower.petitionwatch.modelcounty.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/19.
 */

public class AlarmBean {

    /**
     * isFirstPage : true
     * isLastPage : true
     * list : [{"areaId":"130502","areaName":"邢台市桥东区","category":"一键报警","id":1,"monitoredId":"1","monitoredNames":"王武帅","num":"No.39005702","supervisorId":"1","supervisorName":"监控工作人员","time":"2018-03-15 11:22:40"},{"areaId":"130502","areaName":"邢台市桥东区","category":"自动报警","id":2,"monitoredId":"1","monitoredNames":"王武帅","num":"No.39005423","supervisorId":"1","supervisorName":"监控工作人员","time":"2018-03-15 11:53:12"},{"areaId":"130502","areaName":"邢台市桥东区","category":"一键报警","id":3,"monitoredId":"1","monitoredNames":"王武帅","num":"No.39005446","supervisorId":"1","supervisorName":"监控工作人员","time":"2018-03-15 16:26:23"},{"areaId":"130502","areaName":"邢台市桥东区","category":"自动报警","id":4,"monitoredId":"1","monitoredNames":"王武帅","num":"No.39005246","supervisorId":"1","supervisorName":"监控工作人员","time":"2018-03-15 17:55:40"}]
     * pageNum : 1
     * pageSize : 10
     * pages : 1
     * total : 4
     */

    private boolean isFirstPage;
    private boolean isLastPage;
    private int pageNum;
    private int pageSize;
    private int pages;
    private int total;
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

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * areaId : 130502
         * areaName : 邢台市桥东区
         * category : 一键报警
         * id : 1
         * monitoredId : 1
         * monitoredNames : 王武帅
         * num : No.39005702
         * supervisorId : 1
         * supervisorName : 监控工作人员
         * time : 2018-03-15 11:22:40
         */
        private int commitStatus;

        public int getCommitStatus() {
            return commitStatus;
        }

        public void setCommitStatus(int commitStatus) {
            this.commitStatus = commitStatus;
        }

        private String areaId;
        private String areaName;
        private String category;
        private int id;
        private String monitoredId;
        private String monitoredNames;
        private String num;
        private String supervisorId;
        private String supervisorName;
        private String time;

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
    }
}
