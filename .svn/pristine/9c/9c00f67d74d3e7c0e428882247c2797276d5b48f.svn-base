package com.realpower.petitionwatch.util;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Button;

import com.realpower.petitionwatch.R;

/**
 * Created by Administrator on 2017/11/15.
 */

public class TimeCount extends CountDownTimer {
    private static final int TIME_TASCK = 1000;
    private Button button;
    private Context context;

    public TimeCount(Context context, long millisInFuture, Button view) {
        super(millisInFuture, TIME_TASCK);
        button = view;
        this.context = context;
    }

    @Override
    public void onFinish() {// 计时完毕
        button.setTextColor(context.getResources().getColor(R.color.white));
        button.setBackgroundResource(R.color.colorPrimary);
        button.setText("发送验证码");
        button.setClickable(true);
    }

    @Override
    public void onTick(long millisUntilFinished) {// 计时过程
        // button.setTextColor(context.getResources().getColor(R.color.home_item_count));
        button.setClickable(false);//防止重复点击
        button.setBackgroundResource(R.color.c6d6d6d);
        button.setText("剩余时间" + millisUntilFinished / TIME_TASCK);
    }
}
