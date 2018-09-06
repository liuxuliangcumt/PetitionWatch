package com.realpower.petitionwatch.bean;

import java.util.List;

/**
 * Created by ruipu on 2018/7/13.
 */

public class CameraBean {

    /**
     * isFirstPage : true
     * isLastPage : true
     * list : [{"address":"中关村国际孵化园","areaId":130503,"id":1,"ip":"192.168.1.64","latitude":"37.056106","locationCode":"S01B10U1F5R012","longitude":"114.459016","name":"nameone","password":"123456","rtmpUrl":"rtmp://192.168.1.114:1935/live/1","rtspUrl":"rtsp://admin:123456@192.168.1.64","type":"人脸摄像头"}]
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
         * address : 中关村国际孵化园
         * areaId : 130503
         * id : 1
         * ip : 192.168.1.64
         * latitude : 37.056106
         * locationCode : S01B10U1F5R012
         * longitude : 114.459016
         * name : nameone
         * password : 123456
         * rtmpUrl : rtmp://192.168.1.114:1935/live/1
         * rtspUrl : rtsp://admin:123456@192.168.1.64
         * type : 人脸摄像头
         */

        private String address;
        private int areaId;
        private int id;
        private String ip;
        private String latitude;
        private String locationCode;
        private String longitude;
        private String name;
        private String password;
        private String rtmpUrl;
        private String rtspUrl;
        private String type;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLocationCode() {
            return locationCode;
        }

        public void setLocationCode(String locationCode) {
            this.locationCode = locationCode;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRtmpUrl() {
            return rtmpUrl;
        }

        public void setRtmpUrl(String rtmpUrl) {
            this.rtmpUrl = rtmpUrl;
        }

        public String getRtspUrl() {
            return rtspUrl;
        }

        public void setRtspUrl(String rtspUrl) {
            this.rtspUrl = rtspUrl;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
