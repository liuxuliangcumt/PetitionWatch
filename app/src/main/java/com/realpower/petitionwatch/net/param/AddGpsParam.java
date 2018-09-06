package com.realpower.petitionwatch.net.param;

/**
 * Created by Administrator on 2017/12/26.
 */

public class AddGpsParam {
    private String latitude;
    private String longitude;
    private String gpsTime;
    private String phone;

    public AddGpsParam(String latitude, String longitude) {

        this.latitude = latitude;
        this.longitude = longitude;
    }

}
