package com.realpower.petitionwatch.view.addressview;


import android.util.Log;

import com.realpower.petitionwatch.bean.CityInfoModel;
import com.realpower.petitionwatch.bean.DistrictInfoModel;
import com.realpower.petitionwatch.bean.ProvinceInfoModel;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/7 0007.
 */
public class AddrXmlParser extends DefaultHandler {

    private List<ProvinceInfoModel> provinceList = new ArrayList<ProvinceInfoModel>();


    public List<ProvinceInfoModel> getDataList() {
        return provinceList;
    }

    @Override
    public void startDocument() throws SAXException {
        Log.e("aaa","startDocument");

    }

    ProvinceInfoModel provinceModel = new ProvinceInfoModel();
    CityInfoModel cityModel = new CityInfoModel();
    DistrictInfoModel districtModel = new DistrictInfoModel();

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        if (qName.equals("province")) {
            provinceModel = new ProvinceInfoModel();
            provinceModel.setName(attributes.getValue(0));
            provinceModel.setCityList(new ArrayList<CityInfoModel>());
            Log.e("aaa","province  "+attributes.getValue(0));
        } else if (qName.equals("city")) {
            cityModel = new CityInfoModel();
            cityModel.setName(attributes.getValue(0));
            cityModel.setDistrictList(new ArrayList<DistrictInfoModel>());
            Log.e("aaa","city"+attributes.getValue(0));

        } else if (qName.equals("district")) {
            districtModel = new DistrictInfoModel();
            districtModel.setName(attributes.getValue(0));
            districtModel.setZipcode(attributes.getValue(1));
            Log.e("aaa","district"+attributes.getValue(0));

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (qName.equals("district")) {
            cityModel.getDistrictList().add(districtModel);
            Log.e("aaa","endElement district");

        } else if (qName.equals("city")) {
            provinceModel.getCityList().add(cityModel);
            Log.e("aaa","endElement city");

        } else if (qName.equals("province")) {
            Log.e("aaa","endElement province");

            provinceList.add(provinceModel);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {


    }
}
