package com.realpower.petitionwatch.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/7 0007.
 */
public class CityInfoModel {

    private String name;
    private List<DistrictInfoModel> districtList;

    public CityInfoModel() {
        super();
    }

    public CityInfoModel(String name, List<DistrictInfoModel> districtList) {
        super();
        this.name = name;
        this.districtList = districtList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DistrictInfoModel> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<DistrictInfoModel> districtList) {
        this.districtList = districtList;
    }

    @Override
    public String toString() {
        return "CityInfoModel [name=" + name + ", districtList=" + districtList
                + "]";
    }
}
