package com.realpower.petitionwatch.bean;

import java.io.Serializable;

/**
 * Created by ruipu on 2018/6/21.
 */
//联系人bean
public class ContactBean implements Serializable {

    /**
     * area : {"areaId":1305,"areaName":"邢台市","nodeId":"1305","nodeName":"邢台市","nodeParentId":""}
     * areaId : 1305
     * id : 16
     * roleId : 1
     * sysRole : {"id":16,"name":"市领导"}
     * sysUser : {"id":16,"mobilePhone":"18631978888","nickname":"张树甫"}
     * userId : 22
     */
    private String firstLetter;
    private String pinyin;
    private String txAccount;

    public String getTxAccount() {
        return txAccount;
    }

    public void setTxAccount(String txAccount) {
        this.txAccount = txAccount;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    private AreaBean area;
    private int areaId;
    private int id;
    private int roleId;
    private SysRoleBean sysRole;
    private SysUserBean sysUser;
    private int userId;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public SysRoleBean getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRoleBean sysRole) {
        this.sysRole = sysRole;
    }

    public SysUserBean getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUserBean sysUser) {
        this.sysUser = sysUser;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static class AreaBean implements Serializable {
        /**
         * areaId : 1305
         * areaName : 邢台市
         * nodeId : 1305
         * nodeName : 邢台市
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

    public static class SysRoleBean implements Serializable {
        /**
         * id : 16
         * name : 市领导
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class SysUserBean implements Serializable {
        /**
         * id : 16
         * mobilePhone : 18631978888
         * nickname : 张树甫
         */

        private int id;
        private String mobilePhone;
        private String nickname="#";

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
