package com.realpower.petitionwatch.modelstaff.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.adapter.MyBaseAdapter;
import com.realpower.petitionwatch.modelstaff.bean.SuggestionBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/17.
 */

public class SuggestionAdapter extends MyBaseAdapter<SuggestionBean.ListBean> {
    public SuggestionAdapter(Context context, List<SuggestionBean.ListBean> data) {
        super(context, data);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_petition, null);
            holder = new ViewHolder();
            holder.iv_state = (ImageView) convertView.findViewById(R.id.iv_state);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.btn_deel = (Button) convertView.findViewById(R.id.btn_deel);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_title.setText(data.get(position).getSuggestionTitle());
        holder.tv_content.setText(data.get(position).getSuggestionInfo());
        holder.tv_time.setText(data.get(position).getCreatetime());


         if (data.get(position).getCurrentStatus()==1){
             holder.btn_deel.setVisibility(View.VISIBLE);
             holder.btn_deel.setText("反馈意见");
             holder.btn_deel.setClickable(true);
             holder.btn_deel.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     if (onViewClick != null) {
                         onViewClick.onBtnClick(position);
                     }
                 }
             });
         }else {
             holder.btn_deel.setVisibility(View.GONE);

         }

        return convertView;
    }

    private class ViewHolder {
        ImageView iv_state;
        TextView tv_title, tv_content, tv_time;
        Button btn_deel;
    }

    private OnViewClick onViewClick;

    public void setViewClick(OnViewClick onViewClick) {
        this.onViewClick = onViewClick;
    }

    public interface OnViewClick {
        void onBtnClick(int position);
    }
}
