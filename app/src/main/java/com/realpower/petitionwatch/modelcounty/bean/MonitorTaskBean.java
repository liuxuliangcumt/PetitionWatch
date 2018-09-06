package com.realpower.petitionwatch.modelcounty.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/21.
 */

public class MonitorTaskBean  {

    /**
     * isFirstPage : true
     * isLastPage : true
     * list : [{"area":{"areaId":130502,"areaName":"桥东区","nodeId":"130502","nodeName":"桥东区","nodeParentId":""},"areaId":130502,"districtId":1,"districtTaskId":0,"endtime":"2018-03-24 00:00:00","info":"00:27:41,23:39:41","lasttime":"2018-03-15 13:54:58","monitored":{"areaId":130502,"monitoredId":1,"monitoredIdcard":"13042719861218411x","monitoredRealname":"王武帅"},"monitoredId":1,"starttime":"2018-03-15 00:00:00","status":1,"supervisorId":"1","taskId":1,"taskSuperName":"监控工作人员","title":"监控行动"}]
     * pageNum : 1
     * pageSize : 10
     * pages : 1
     * total : 1
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
         * area : {"areaId":130502,"areaName":"桥东区","nodeId":"130502","nodeName":"桥东区","nodeParentId":""}
         * areaId : 130502
         * districtId : 1
         * districtTaskId : 0
         * endtime : 2018-03-24 00:00:00
         * info : 00:27:41,23:39:41
         * lasttime : 2018-03-15 13:54:58
         * monitored : {"areaId":130502,"monitoredId":1,"monitoredIdcard":"13042719861218411x","monitoredRealname":"王武帅"}
         * monitoredId : 1
         * starttime : 2018-03-15 00:00:00
         * status : 1
         * supervisorId : 1
         * taskId : 1
         * taskSuperName : 监控工作人员
         * title : 监控行动
         */

        private AreaBean area;
        private int areaId;
        private int districtId;
        private int districtTaskId;
        private String endtime;
        private String info;
        private String lasttime;
        private MonitoredBean monitored;
        private int monitoredId;
        private String starttime;
        private int status;
        private String supervisorId;
        private int taskId;
        private String taskSuperName;
        private String title;

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

        public static class AreaBean {
            /**
             * areaId : 130502
             * areaName : 桥东区
             * nodeId : 130502
             * nodeName : 桥东区
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
             * areaId : 130502
             * monitoredId : 1
             * monitoredIdcard : 13042719861218411x
             * monitoredRealname : 王武帅
             */

            private int areaId;
            private int monitoredId;
            private String monitoredIdcard;
            private String monitoredRealname;

            public int getAreaId() {
                return areaId;
            }

            public void setAreaId(int areaId) {
                this.areaId = areaId;
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

            public String getMonitoredRealname() {
                return monitoredRealname;
            }

            public void setMonitoredRealname(String monitoredRealname) {
                this.monitoredRealname = monitoredRealname;
            }
        }
    }
}
