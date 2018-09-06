package com.realpower.petitionwatch.modelcity.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.adapter.MyBaseAdapter;
import com.realpower.petitionwatch.modelcity.bean.DistrictBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */

public class AddTaskAdapter extends MyBaseAdapter<DistrictBean.ListBean> {
    public AddTaskAdapter(Context context, List<DistrictBean.ListBean> data) {
        super(context, data);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_addtask, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
            holder.name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.cb_chose);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.checkBox.setChecked(data.get(position).isChoose());

        holder.name.setText(data.get(position).getDistrictRealname());

        return convertView;
    }

    private class ViewHolder {
        TextView name;
        CheckBox checkBox;
    }
}
