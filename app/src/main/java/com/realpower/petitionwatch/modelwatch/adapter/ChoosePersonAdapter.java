package com.realpower.petitionwatch.modelwatch.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.modelwatch.bean.AlarmBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/1.
 */

public class ChoosePersonAdapter extends BaseAdapter {
    private Context context;
    private List<AlarmBean> data;

    public ChoosePersonAdapter(Context context, List<AlarmBean> data) {
        this.context = context;
        this.data = data;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_listview_choose, null);
            holder.name = (TextView) convertView.findViewById(R.id.title);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.cb_alarm);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(data.get(position).getMonitoredRealname()+"");
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                data.get(position).setChoose(isChecked);
            }
        });
        holder.checkBox.setChecked(data.get(position).isChoose());
        return convertView;
    }

    private class ViewHolder {
        TextView name;
        CheckBox checkBox;
        ImageView avater;
    }
}
