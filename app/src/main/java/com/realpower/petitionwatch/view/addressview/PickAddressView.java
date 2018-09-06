package com.realpower.petitionwatch.view.addressview;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.bean.AddressBean;
import com.realpower.petitionwatch.bean.ProvinceInfoModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/30.
 */

public class PickAddressView extends LinearLayout {
    /**
     * 与选择地址相关
     */
    protected ArrayList<String> mProvinceDatas = new ArrayList<>();
    protected Map<String, ArrayList<String>> mCitisDatasMap = new HashMap<String, ArrayList<String>>();
    protected Map<String, ArrayList<String>> mDistrictDatasMap = new HashMap<String, ArrayList<String>>();
    private WheelView mProvincePicker;
    private WheelView mCityPicker;
    private WheelView mCountyPicker;

    protected String mCurrentProviceName;
    protected String mCurrentCityName;
    protected String mCurrentDistrictName;
    private TextView cancel, ok;
    protected boolean isDataLoaded = false;
    private Context context;
    private PickAddressInterface pickAddressInterface;

    public PickAddressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        LayoutInflater.from(context).inflate(R.layout.address, this);
        this.context = context;
        initData();
    }

    public PickAddressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    private int cityPickerPosition, countyPickerPosition;

    private void initData() {
        mProvincePicker = (WheelView) findViewById(R.id.province);
        mCityPicker = (WheelView) findViewById(R.id.city);
        mCountyPicker = (WheelView) findViewById(R.id.county);
        cancel = (TextView) findViewById(R.id.box_cancel);
        ok = (TextView) findViewById(R.id.box_ok);


        mProvincePicker.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                String provinceText = mProvinceDatas.get(id);
                if (!mCurrentProviceName.equals(provinceText)) {
                    mCurrentProviceName = provinceText;

                    ArrayList<String> mDistrictData = new ArrayList<>();
                    for (int i = 0; i < cityChildsBeans.get(id).getChilds().size(); i++) {
                        mDistrictData.add(cityChildsBeans.get(id).getChilds().get(i).getName());
                    }
                    mCityPicker.resetData(mDistrictData);
                    mCityPicker.setDefault(0);
                    mCurrentCityName = mDistrictData.get(0);

                    ArrayList<String> mStreetData = new ArrayList<>();
                    countyChildsBeans = cityChildsBeans.get(id).getChilds();
                    for (int i = 0; i < countyChildsBeans.get(0).getChilds().size(); i++) {
                        mStreetData.add(countyChildsBeans.get(0).getChilds().get(i).getName());
                    }
                    mCountyPicker.resetData(mStreetData);
                    mCountyPicker.setDefault(0);
                    mCurrentDistrictName = mStreetData.get(0);
                    /* ArrayList<String> mCityData = mCitisDatasMap.get(mCurrentProviceName);
                    mCityPicker.resetData(mCityData);
                    mCityPicker.setDefault(0);
                    mCurrentCityName = mCityData.get(0);

                    ArrayList<String> mDistrictData = mDistrictDatasMap.get(mCurrentCityName);
                    mCountyPicker.resetData(mDistrictData);
                    mCountyPicker.setDefault(0);
                    mCurrentDistrictName = mDistrictData.get(0);*/
                }
            }

            @Override
            public void selecting(int id, String text) {
            }
        });

        mCityPicker.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {

                cityPickerPosition = id;
                ArrayList<String> mStreetData = new ArrayList<>();
                for (int i = 0; i < countyChildsBeans.get(id).getChilds().size(); i++) {
                    mStreetData.add(countyChildsBeans.get(id).getChilds().get(i).getName());
                }
                mCountyPicker.resetData(mStreetData);
                mCountyPicker.setDefault(0);
                mCurrentDistrictName = mStreetData.get(0);
                /* ArrayList<String> mCityData = mCitisDatasMap.get(mCurrentProviceName);
                String cityText = mCityData.get(id);
                if (!mCurrentCityName.equals(cityText)) {
                    mCurrentCityName = cityText;
                    ArrayList<String> mCountyData = mDistrictDatasMap.get(mCurrentCityName);
                    mCountyPicker.resetData(mCountyData);
                    mCountyPicker.setDefault(0);
                    mCurrentDistrictName = mCountyData.get(0);
                }*/
            }

            @Override
            public void selecting(int id, String text) {

            }
        });

        mCountyPicker.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
               /* ArrayList<String> mDistrictData = mDistrictDatasMap.get(mCurrentCityName);
                String districtText = mDistrictData.get(id);
                if (!mCurrentDistrictName.equals(districtText)) {
                    mCurrentDistrictName = districtText;
                }*/
                countyPickerPosition = id;
            }

            @Override
            public void selecting(int id, String text) {

            }
        });
        ok.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pickAddressInterface != null) {
                    pickAddressInterface.onOkClick(mProvinceDatas.get(mProvincePicker.getSelected())+" "
                            + mCityPicker.getSelectedText()+" "
                            + mCountyPicker.getSelectedText(),
                            cityChildsBeans.get(0).getChilds().get(cityPickerPosition)
                                    .getChilds().get(countyPickerPosition).getCode());
                   // pickAddressInterface.onCancelClick();
                }
            }
        });
        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pickAddressInterface != null) {
                    pickAddressInterface.onCancelClick();
                }
            }
        });

        // 启动线程读取数据
        new Thread() {
            @Override
            public void run() {
                // 读取数据
                isDataLoaded = readAddrDatas();

                // 通知界面线程
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < cityChildsBeans.size(); i++) {
                            mProvinceDatas.add(cityChildsBeans.get(i).getName());
                        }
                        mProvincePicker.setData(mProvinceDatas);
                        mProvincePicker.setDefault(0);
                        mCurrentProviceName = mProvinceDatas.get(0);

                        countyChildsBeans = cityChildsBeans.get(0).getChilds();
                        ArrayList<String> mCityData = new ArrayList<>();
                        for (int i = 0; i < countyChildsBeans.size(); i++) {
                            mCityData.add(countyChildsBeans.get(i).getName());
                        }
                        mCityPicker.setData(mCityData);
                        mCityPicker.setDefault(0);
                        mCurrentCityName = mCityData.get(0);

                        streetChildsBeans = countyChildsBeans.get(0).getChilds();
                        ArrayList<String> mDistrictData = new ArrayList<>();
                        for (int i = 0; i < streetChildsBeans.size(); i++) {
                            mDistrictData.add(streetChildsBeans.get(i).getName());
                        }
                        mCountyPicker.setData(mDistrictData);
                        mCountyPicker.setDefault(0);
                        mCurrentDistrictName = mDistrictData.get(0);
                      /*
                        mProvincePicker.setData(mProvinceDatas);
                        mProvincePicker.setDefault(0);
                        mCurrentProviceName = mProvinceDatas.get(0);

                        ArrayList<String> mCityData = mCitisDatasMap.get(mCurrentProviceName);
                        mCityPicker.setData(mCityData);
                        mCityPicker.setDefault(0);
                        mCurrentCityName = mCityData.get(0);

                        ArrayList<String> mDistrictData = mDistrictDatasMap.get(mCurrentCityName);
                        mCountyPicker.setData(mDistrictData);
                        mCountyPicker.setDefault(0);
                        mCurrentDistrictName = mDistrictData.get(0);*/


                    }
                }, 100);
            }
        }.start();

    }

    /**
     * 读取地址数据，请使用线程进行调用
     *
     * @return
     */
    List<AddressBean> cityChildsBeans;//城市
    List<AddressBean.ChildsBeanX> countyChildsBeans = new ArrayList<>();//区县
    List<AddressBean.ChildsBeanX.ChildsBean> streetChildsBeans = new ArrayList<>();//街道

    protected boolean readAddrDatas() {


        InputStreamReader inputStreamReader;
        try {
            inputStreamReader = new InputStreamReader(context.getAssets().open(
                    "cityaddress.json"), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);
            String line;
            Log.e("aaa", "开始时间 " + System.currentTimeMillis());
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            inputStreamReader.close();
            bufferedReader.close();
            Log.e("aaa", "结束时间 " + System.currentTimeMillis());
            Log.e("aaa", stringBuilder.toString());
            Gson gson = new Gson();
            Type type = new TypeToken<List<AddressBean>>() {
            }.getType();
            cityChildsBeans = gson.fromJson(stringBuilder.toString(), type);
            Log.e("aaa", "省份数  " + cityChildsBeans.size());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<ProvinceInfoModel> provinceList = null;
        AssetManager asset = context.getAssets();

        return true;
    }

    public void setOnTopClicklistener(PickAddressInterface pickAddressInterface) {
        this.pickAddressInterface = pickAddressInterface;
    }

}
