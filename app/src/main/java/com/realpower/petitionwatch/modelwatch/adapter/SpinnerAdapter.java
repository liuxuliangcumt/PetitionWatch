package com.realpower.petitionwatch.modelwatch.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.util.SystemInfoUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/11/28.
 */

public class SpinnerAdapter extends BaseAdapter {
    private List<String> data;
    private Context context;

    public SpinnerAdapter(List<String> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setText(data.get(position));
        textView.setPadding(0, SystemInfoUtils.dp2px(context, 10), 0, SystemInfoUtils.dp2px(context, 10));
        textView.setTextSize(14);
        textView.setHeight(SystemInfoUtils.dp2px(context, 38));
        textView.setTextColor(context.getResources().getColor(R.color.c333333));
        textView.setGravity(Gravity.CENTER);
        return textView;
    }


}
