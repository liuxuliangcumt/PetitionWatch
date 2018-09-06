package com.realpower.petitionwatch.modelcounty.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/22.
 */

public class MyTaskBean implements Serializable{

    /**
     * isFirstPage : true
     * isLastPage : true
     * list : [{"area":{"areaId":130502,"areaName":"桥东区","nodeId":"130502","nodeName":"桥东区","nodeParentId":""},"areaId":130502,"district":{"areaId":130502,"districtId":1,"districtPhone":"15911101626","districtRealname":"桥东区"},"districtId":1,"endtime":"2018-03-31 00:00:00","info":":34:3,23:34:3","municipalId":1,"starttime":"2018-03-15 00:00:00","status":1,"taskId":1,"title":"监控重点人"},{"area":{"areaId":130502,"areaName":"桥东区","nodeId":"130502","nodeName":"桥东区","nodeParentId":""},"areaId":130502,"district":{"areaId":130502,"districtId":1,"districtPhone":"15911101626","districtRealname":"桥东区"},"districtId":1,"endtime":"2018-04-16 00:00:00","info":":18:49,18:18:49","municipalId":1,"starttime":"2018-03-17 00:00:00","status":1,"taskId":2,"title":"监控"}]
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

    public static class ListBean implements Serializable{
        /**
         * area : {"areaId":130502,"areaName":"桥东区","nodeId":"130502","nodeName":"桥东区","nodeParentId":""}
         * areaId : 130502
         * district : {"areaId":130502,"districtId":1,"districtPhone":"15911101626","districtRealname":"桥东区"}
         * districtId : 1
         * endtime : 2018-03-31 00:00:00
         * info : :34:3,23:34:3
         * municipalId : 1
         * starttime : 2018-03-15 00:00:00
         * status : 1
         * taskId : 1
         * title : 监控重点人
         */

        private AreaBean area;
        private int areaId;
        private DistrictBean district;
        private int districtId;
        private String endtime;
        private String info;
        private int municipalId;
        private String starttime;
        private int status;
        private int taskId;
        private String title;
        /**
         * municipalName : 市领导
         */

        private String municipalName;

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

        public DistrictBean getDistrict() {
            return district;
        }

        public void setDistrict(DistrictBean district) {
            this.district = district;
        }

        public int getDistrictId() {
            return districtId;
        }

        public void setDistrictId(int districtId) {
            this.districtId = districtId;
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

        public int getMunicipalId() {
            return municipalId;
        }

        public void setMunicipalId(int municipalId) {
            this.municipalId = municipalId;
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

        public String getMunicipalName() {
            return municipalName;
        }

        public void setMunicipalName(String municipalName) {
            this.municipalName = municipalName;
        }

        public static class AreaBean implements Serializable{
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

        public static class DistrictBean implements Serializable{
            /**
             * areaId : 130502
             * districtId : 1
             * districtPhone : 15911101626
             * districtRealname : 桥东区
             */

            private int areaId;
            private int districtId;
            private String districtPhone;
            private String districtRealname;

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

            public String getDistrictPhone() {
                return districtPhone;
            }

            public void setDistrictPhone(String districtPhone) {
                this.districtPhone = districtPhone;
            }

            public String getDistrictRealname() {
                return districtRealname;
            }

            public void setDistrictRealname(String districtRealname) {
                this.districtRealname = districtRealname;
            }
        }
    }
}
