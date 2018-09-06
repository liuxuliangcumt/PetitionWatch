package com.realpower.petitionwatch.modelwatch.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.modelwatch.bean.MonitoredBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/23.
 */

public class KeyAdapter extends BaseAdapter {
    private Context context;
    private List<MonitoredBean> data;

    public KeyAdapter(Context context, List<MonitoredBean> data) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_key, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_name.setText(data.get(position).getMonitoredRealname());
        return convertView;
    }

    private class ViewHolder {
        TextView tv_name;
    }
}
