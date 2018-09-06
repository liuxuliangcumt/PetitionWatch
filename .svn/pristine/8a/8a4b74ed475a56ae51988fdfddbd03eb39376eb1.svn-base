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
 * Created by Administrator on 2017/12/4.
 */

public class RelativeAdapter extends BaseAdapter {
    private Context context;
    private List<MonitoredBean.RelativesBean> data;

    public RelativeAdapter(Context context, List<MonitoredBean.RelativesBean> data) {
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
            convertView = View.inflate(context, R.layout.item_listview_addkins, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.relation = (TextView) convertView.findViewById(R.id.tv_relatition);
            holder.number = (TextView) convertView.findViewById(R.id.tv_number);
            holder.phone = (TextView) convertView.findViewById(R.id.tv_phone);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(data.get(position).getRealname());
        holder.phone.setText(data.get(position).getPhone());
        holder.number.setText(data.get(position).getIdcard());
        holder.relation.setText(data.get(position).getRelationship());

        return convertView;
    }

    private class ViewHolder {
        TextView name, number, phone, relation;
    }
}
