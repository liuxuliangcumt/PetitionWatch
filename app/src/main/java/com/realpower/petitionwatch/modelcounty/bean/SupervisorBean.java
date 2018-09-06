package com.realpower.petitionwatch.modelcounty.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/19.
 */

public class SupervisorBean {

    /**
     * isFirstPage : true
     * isLastPage : true
     * list : [{"area":{"areaId":130502,"areaName":"桥东区","nodeId":"130502","nodeName":"桥东区","nodeParentId":""},"areaId":130502,"assessorStatus":1,"isInTask":"监控中","supervisorDeparno":"no data","supervisorId":1,"supervisorPhone":"13331040525","supervisorPwd":"6DCD4EAE8C09FD02BE1FA976362E2940","supervisorRealname":"监控工作人员","sysUserId":4},{"area":{"areaId":130502,"areaName":"桥东区","nodeId":"130502","nodeName":"桥东区","nodeParentId":""},"areaId":130502,"assessorStatus":1,"isInTask":"待分配","supervisorDeparno":"no data","supervisorId":2,"supervisorPhone":"13940406367","supervisorPwd":"654321","supervisorRealname":"大王","sysUserId":9}]
     * pageNum : 1
     * pageSize : 10
     * pages : 1
     * total : 2
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
         * assessorStatus : 1
         * isInTask : 监控中
         * supervisorDeparno : no data
         * supervisorId : 1
         * supervisorPhone : 13331040525
         * supervisorPwd : 6DCD4EAE8C09FD02BE1FA976362E2940
         * supervisorRealname : 监控工作人员
         * sysUserId : 4
         */
        private boolean isCheck;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        private AreaBean area;
        private int areaId;
        private int assessorStatus;
        private String isInTask;
        private String supervisorDeparno;
        private int supervisorId;
        private String supervisorPhone;
        private String supervisorPwd;
        private String supervisorRealname;
        private int sysUserId;

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

        public int getAssessorStatus() {
            return assessorStatus;
        }

        public void setAssessorStatus(int assessorStatus) {
            this.assessorStatus = assessorStatus;
        }

        public String getIsInTask() {
            return isInTask;
        }

        public void setIsInTask(String isInTask) {
            this.isInTask = isInTask;
        }

        public String getSupervisorDeparno() {
            return supervisorDeparno;
        }

        public void setSupervisorDeparno(String supervisorDeparno) {
            this.supervisorDeparno = supervisorDeparno;
        }

        public int getSupervisorId() {
            return supervisorId;
        }

        public void setSupervisorId(int supervisorId) {
            this.supervisorId = supervisorId;
        }

        public String getSupervisorPhone() {
            return supervisorPhone;
        }

        public void setSupervisorPhone(String supervisorPhone) {
            this.supervisorPhone = supervisorPhone;
        }

        public String getSupervisorPwd() {
            return supervisorPwd;
        }

        public void setSupervisorPwd(String supervisorPwd) {
            this.supervisorPwd = supervisorPwd;
        }

        public String getSupervisorRealname() {
            return supervisorRealname;
        }

        public void setSupervisorRealname(String supervisorRealname) {
            this.supervisorRealname = supervisorRealname;
        }

        public int getSysUserId() {
            return sysUserId;
        }

        public void setSysUserId(int sysUserId) {
            this.sysUserId = sysUserId;
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
    }
}
