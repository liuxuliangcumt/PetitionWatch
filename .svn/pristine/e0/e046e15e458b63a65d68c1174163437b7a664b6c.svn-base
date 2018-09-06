package com.realpower.petitionwatch.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Administrator on 2017/1/9 0009.
 */
public class CustomGridVeiw extends GridView {
    public CustomGridVeiw(Context context) {
        super(context);
    }

    public CustomGridVeiw(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomGridVeiw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
