package com.realpower.petitionwatch.util;

import android.graphics.drawable.GradientDrawable;
import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * Created by ruipu on 2018/9/3.
 */
public class MyDrawableUtils {
    public static void createShape(View view, int color, int leftTop, int rightTop, int bottomLeft, int bottomRight) {
        float[] radii = {UIUtils.dpToPx(leftTop), UIUtils.dpToPx(leftTop),
                UIUtils.dpToPx(rightTop), UIUtils.dpToPx(rightTop),
                UIUtils.dpToPx(bottomLeft), UIUtils.dpToPx(bottomLeft),
                UIUtils.dpToPx(bottomRight), UIUtils.dpToPx(bottomRight),};
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(UIUtils.dpToPx(23));//设置4个角的弧度
        drawable.setColor(color);// 设置颜色
        drawable.setCornerRadii(radii);
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        ViewCompat.setBackground(view, drawable);

    }

    public static void createShape(View view, int color, int radius) {

        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(UIUtils.dpToPx(23));//设置4个角的弧度
        drawable.setColor(color);// 设置颜色
        drawable.setCornerRadius(radius);
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        ViewCompat.setBackground(view, drawable);

    }

    public static void createShape(View view, int[] colors,
                                   int leftTop, int rightTop,
                                   int bottomLeft, int bottomRight) {
        float[] radii = {UIUtils.dpToPx(leftTop), UIUtils.dpToPx(leftTop),
                UIUtils.dpToPx(rightTop), UIUtils.dpToPx(rightTop),
                UIUtils.dpToPx(bottomLeft), UIUtils.dpToPx(bottomLeft),
                UIUtils.dpToPx(bottomRight), UIUtils.dpToPx(bottomRight),};
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(UIUtils.dpToPx(23));//设置4个角的弧度
        drawable.setColors(colors);
        drawable.setCornerRadii(radii);
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        ViewCompat.setBackground(view, drawable);

    }
}
