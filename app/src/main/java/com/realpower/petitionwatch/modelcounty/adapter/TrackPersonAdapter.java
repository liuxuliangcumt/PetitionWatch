package com.realpower.petitionwatch.modelcounty.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.adapter.MyBaseAdapter;
import com.realpower.petitionwatch.modelcounty.bean.TrackPersonBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/26.
 */

public class TrackPersonAdapter extends MyBaseAdapter<TrackPersonBean> {
    public TrackPersonAdapter(Context context, List<TrackPersonBean> data) {
        super(context, data);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_trackperson, null);
            holder = new ViewHolder();
            holder.tv_idCard = (TextView) convertView.findViewById(R.id.tv_idCard);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
            holder.cb_chose = (CheckBox) convertView.findViewById(R.id.cb_chose);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_name.setText("姓        名: " + data.get(position).getMonitoredRealname());
        holder.tv_idCard.setText("身份证号: " + data.get(position).getMonitoredIdcard());
        holder.tv_phone.setText("手机号码: " + data.get(position).getMonitoredPhone());
        holder.cb_chose.setChecked(data.get(position).isChecked());
        holder.cb_chose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                data.get(position).setChecked(isChecked);
            }
        });

        return convertView;
    }

    private class ViewHolder {
        CheckBox cb_chose;
        TextView tv_name, tv_idCard, tv_phone;
    }
}