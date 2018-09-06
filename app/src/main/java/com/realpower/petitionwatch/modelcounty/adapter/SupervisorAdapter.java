package com.realpower.petitionwatch.modelcounty.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.adapter.MyBaseAdapter;
import com.realpower.petitionwatch.modelcounty.bean.SupervisorBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/30.
 */

public class SupervisorAdapter extends MyBaseAdapter<SupervisorBean.ListBean> {
    public SupervisorAdapter(Context context, List<SupervisorBean.ListBean> data) {
        super(context, data);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_supervisor, null);
            holder = new ViewHolder();
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            holder.name = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(data.get(position).getSupervisorRealname());
        holder.checkBox.setChecked(data.get(position).isCheck());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                data.get(position).setCheck(isChecked);
            }
        });
        return convertView;
    }

    private class ViewHolder {
        TextView name;
        CheckBox checkBox;

    }
}
