package com.realpower.petitionwatch.modelcounty.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.adapter.MyBaseAdapter;
import com.realpower.petitionwatch.modelcounty.bean.SupervisorBean;

import java.util.List;

/**
 */

public class ManagerSupervisorAdapter extends MyBaseAdapter<SupervisorBean.ListBean> {
    public ManagerSupervisorAdapter(Context context, List<SupervisorBean.ListBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_county_supervisor, null);
            holder = new ViewHolder();
            holder.iv_state = (ImageView) convertView.findViewById(R.id.iv_state);
            holder.tv_state = (TextView) convertView.findViewById(R.id.tv_state);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_number = (TextView) convertView.findViewById(R.id.tv_number);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_name.setText(data.get(position).getSupervisorRealname());
        holder.tv_number.setText("手机号" + data.get(position).getSupervisorPhone());
        holder.tv_state.setText(data.get(position).getIsInTask());
        return convertView;
    }

    private class ViewHolder {
        ImageView iv_state;
        TextView tv_state, tv_name, tv_number, tv_department;
    }
}
