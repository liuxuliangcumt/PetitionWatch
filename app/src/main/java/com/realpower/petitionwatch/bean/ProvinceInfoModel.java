package com.realpower.petitionwatch.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/7 0007.
 */
public class ProvinceInfoModel {
    private String name;
    private List<CityInfoModel> cityList;

    public ProvinceInfoModel() {
        super();
    }

    public ProvinceInfoModel(String name, List<CityInfoModel> cityList) {
        super();
        this.name = name;
        this.cityList = cityList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityInfoModel> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityInfoModel> cityList) {
        this.cityList = cityList;
    }

    @Override
    public String toString() {
        return "ProvinceInfoModel [name=" + name + ", cityList=" + cityList + "]";
    }
}
