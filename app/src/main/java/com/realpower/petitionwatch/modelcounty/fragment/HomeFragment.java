package com.realpower.petitionwatch.modelcounty.fragment;

import android.graphics.Color;
import android.graphics.Matrix;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.fragment.BaseFragment;
import com.realpower.petitionwatch.modelcounty.bean.HomeTopBean;
import com.realpower.petitionwatch.modelcounty.bean.OnlineBean;
import com.realpower.petitionwatch.modelcounty.bean.SixMonthMonitoredBean;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.result.HomeTopResult;
import com.realpower.petitionwatch.net.result.OnlineResult;
import com.realpower.petitionwatch.net.result.SixMonthMonitoredResult;
import com.realpower.petitionwatch.view.CircularProgressView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 */
@EFragment(R.layout.fragment_home)
public class HomeFragment extends BaseFragment {

    @ViewById
    LineChart lineChart;

    @ViewById
    BarChart barChart;

    @ViewById
    CircularProgressView circular_1,circular_2,circular_3;

    @ViewById
    RadioButton rb_1_1, rb_1_2, rb_2_1, rb_2_2, rb_3_1, rb_3_2;

    @AfterViews
    void onInitViews() {
        setTitleName("统计");//#323232
        getCategoryTopThree();
        getSixMonthMonitored();
        getCurrentData();

        rb_1_1.setChecked(true);
        rb_2_1.setChecked(true);
        rb_3_1.setChecked(true);
       /* lineChart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                
                
                return false;
            }
        });*/

    }


    @CheckedChange({R.id.rb_1_1, R.id.rb_1_2, R.id.rb_2_1, R.id.rb_2_2, R.id.rb_3_1, R.id.rb_3_2})
    void onCheckedChange(RadioButton view, boolean isChecked) {
        Log.e("aaa", "onCheckedChange  " + view.getId() + "  " + isChecked);
        if (isChecked) {
            switch (view.getId()) {
                case R.id.rb_1_1:
                    changeRadioButton(rb_1_1, rb_1_2);
                    break;
                case R.id.rb_1_2:
                    changeRadioButton(rb_1_2, rb_1_1);

                    break;
                case R.id.rb_2_1:
                    changeRadioButton(rb_2_1, rb_2_2);

                    break;
                case R.id.rb_2_2:
                    changeRadioButton(rb_2_2, rb_2_1);

                    break;
                case R.id.rb_3_1:
                    changeRadioButton(rb_3_1, rb_3_2);

                    break;
                case R.id.rb_3_2:
                    changeRadioButton(rb_3_2, rb_3_1);

                    break;
            }
        }

    }

    private void changeRadioButton(RadioButton checkFalse, RadioButton checkTrue) {
        checkTrue.setTextColor(getResources().getColor(R.color.bigTextColor));
        checkFalse.setTextColor(getResources().getColor(R.color.white));

    }

    private void getCurrentData() {
        Call<OnlineResult> call = apiService.getCurrentData();
        call.enqueue(new MyCallback<OnlineResult>() {
            @Override
            public void onSuccessRequest(OnlineResult result) {
                if ("1".equals(result.getStatus())) {
                    setCurrentOnlineData(result.getMessage());
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<OnlineResult> call, Throwable t) {

            }
        });
    }

    private void setCurrentOnlineData(OnlineBean message) {
      /*  String monitored = "<span style=\"color: #323232\"; \"font-size: 16px\";>重点人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=\"color: #999\">" +
                message.getTotalMonitorednum() + "</span><span>人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>";
        String monitoredEnd = "<span>在线</span><span style=\"color: #999\">" + message.getMonitorednum() + "</span><span>人</span>";
        tv_monitored.setText(Html.fromHtml(monitored));
        tv_monitoredEnd.setText(Html.fromHtml(monitoredEnd));
        String monitor = "<span style=\"color: #323232\"; \"font-size: 16px\";>工作人员&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=\"color: #999\">" +
                message.getTotalSupernum() + "</span><span>人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>";
        String monitorEnd = "<span>在线</span><span style=\"color: #999\">" + message.getSupernum() + "</span><span>人</span>";
        tv_monitor.setText(Html.fromHtml(monitor));
        tv_monitorEnd.setText(Html.fromHtml(monitorEnd));
        pb_monitor.setProgress((int) (Float.parseFloat(message.getSupernum()) /
                Float.parseFloat(message.getTotalSupernum())));
        pb_monitored.setProgress((int) (Float.parseFloat(message.getMonitorednum()) /
                Float.parseFloat(message.getTotalMonitorednum())));*/

    }

    private void getSixMonthMonitored() {
        Call<SixMonthMonitoredResult> call = apiService.getSixMonthMonitored();
        call.enqueue(new MyCallback<SixMonthMonitoredResult>() {
            @Override
            public void onSuccessRequest(SixMonthMonitoredResult result) {
                setMonthData(result.getMessage());
                setMonthBarData(result.getMessage());
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<SixMonthMonitoredResult> call, Throwable t) {

            }
        });

    }

    private void setMonthBarData(SixMonthMonitoredBean message) {
        List<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < message.getMonitored().size(); i++) {
            entries.add(new BarEntry(i,
                    Float.parseFloat(message.getMonitored().get(i))));
        }
        for (int i = 0; i < message.getMonitored().size(); i++) {
            entries.add(new BarEntry(6 + i,
                    Float.parseFloat(message.getMonitored().get(i))));
        }
        BarDataSet dataSet = new BarDataSet(entries, "Label1");
      /*  dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setCubicIntensity(0.2f);
        dataSet.setDrawFilled(true);  //设置包括的范围区域填充颜色
        dataSet.setDrawCircles(true);  //设置有圆点
        dataSet.setLineWidth(2f);    //设置线的宽度
        dataSet.setCircleSize(5f);   //设置小圆的大小*/
        //dataSet.setHighLightColor(Color.rgb(244, 117, 117));
        dataSet.setColor(Color.parseColor("#f77062"));    //设置曲线的颜色
        dataSet.setFormLineWidth(1f);
        dataSet.setFormSize(15.f);
        //  dataSet.setFillDrawable(getResources().getDrawable(R.drawable.bg_chart_fill));

        dataSet.setValueTextSize(14);
        // dataSet.setColors(Color.RED, Color.GRAY, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.CYAN); // 每个点之间线的颜色，还有其他几个方法，自己看
        dataSet.setValueFormatter(new IValueFormatter() {   // 将值转换为想要显示的形式，比如，某点值为1，变为“1￥”,MP提供了三个默认的转换器，
            // LargeValueFormatter:将大数字变为带单位数字；PercentFormatter：将值转换为百分数；StackedValueFormatter，对于BarChart，是否只显示最大值图还是都显示
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return "";
            }
        });

        BarData barData = new BarData(dataSet);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setTextColor(Color.parseColor("#333333"));
        xAxis.setTextSize(11f);
        xAxis.setAxisMinimum(0f);
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        xAxis.setGranularity(1f);//禁止放大x轴标签重绘
        xAxis.setLabelCount(entries.size());
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            list.add(String.valueOf(i + 1));
        }
        xAxis.setValueFormatter(new IndexAxisValueFormatter(list));
        float ratio = (float) entries.size() / (float) 12;
        barChart.zoom(ratio, 1f, 0, 0);
        barChart.animateX(1500);
        //设置XY轴动画
        barChart.animateXY(1500, 1500, Easing.EasingOption.EaseInSine, Easing.EasingOption.EaseInSine);

        //透明化图例
        Legend legend = barChart.getLegend();
        legend.setForm(Legend.LegendForm.NONE);
        legend.setTextColor(Color.WHITE);
        Description description = new Description();
        description.setText("月");
        barChart.setDescription(description);
        barChart.getAxisLeft().setDrawGridLines(false);
        //barChart.getAxisLeft().setDrawLabels(false);

        barChart.getAxisRight().setEnabled(false);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setScaleMinima(1.0f, 1.0f);
        barChart.setData(barData);
        barChart.invalidate();

    }

    private void setMonthData(final SixMonthMonitoredBean message) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < message.getMonitored().size(); i++) {
            entries.add(new Entry(i,
                    Float.parseFloat(message.getMonitored().get(i))));
        }
        for (int i = 0; i < message.getMonitored().size(); i++) {
            entries.add(new Entry(6 + i,
                    Float.parseFloat(message.getMonitored().get(i))));
        }
        LineDataSet dataSet = new LineDataSet(entries, "Label1");
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setCubicIntensity(0.2f);
        dataSet.setDrawFilled(true);  //设置包括的范围区域填充颜色
        dataSet.setDrawCircles(true);  //设置有圆点
        dataSet.setLineWidth(2f);    //设置线的宽度
        dataSet.setCircleSize(5f);   //设置小圆的大小
        //dataSet.setHighLightColor(Color.rgb(244, 117, 117));
        dataSet.setColor(Color.parseColor("#fec664"));    //设置曲线的颜色
        dataSet.setFormLineWidth(1f);
        dataSet.setFormSize(15.f);
        dataSet.setFillDrawable(getResources().getDrawable(R.drawable.bg_chart_fill));

        dataSet.setValueTextSize(14);
        // dataSet.setColors(Color.RED, Color.GRAY, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.CYAN); // 每个点之间线的颜色，还有其他几个方法，自己看
        dataSet.setValueFormatter(new IValueFormatter() {   // 将值转换为想要显示的形式，比如，某点值为1，变为“1￥”,MP提供了三个默认的转换器，
            // LargeValueFormatter:将大数字变为带单位数字；PercentFormatter：将值转换为百分数；StackedValueFormatter，对于BarChart，是否只显示最大值图还是都显示
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return (int) value + "人";
            }
        });

        LineData barData = new LineData(dataSet);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setTextColor(Color.parseColor("#333333"));
        xAxis.setTextSize(11f);
        xAxis.setAxisMinimum(0f);
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        xAxis.setGranularity(1f);//禁止放大x轴标签重绘
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            list.add(String.valueOf(i + 1).concat("月"));
        }
        xAxis.setValueFormatter(new IndexAxisValueFormatter(list));
        float ratio = (float) entries.size() / (float) 4;

        lineChart.zoom(ratio, 1f, 0, 0);
        lineChart.animateX(1500);
        //设置XY轴动画
        lineChart.animateXY(1500, 1500, Easing.EasingOption.EaseInSine, Easing.EasingOption.EaseInSine);

        //透明化图例
        Legend legend = lineChart.getLegend();
        legend.setForm(Legend.LegendForm.NONE);
        legend.setTextColor(Color.WHITE);
        Description description = new Description();
        description.setEnabled(false);
        lineChart.setDescription(description);
        // lineChart.getAxisLeft().setDrawAxisLine(false);
        lineChart.getAxisLeft().setEnabled(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setScaleMinima(1.0f, 1.0f);
        lineChart.setData(barData);
        lineChart.invalidate();
    }


    public void getCategoryTopThree() {
        Call<HomeTopResult> call = apiService.getCategoryTopThree();
        call.enqueue(new MyCallback<HomeTopResult>() {
            @Override
            public void onSuccessRequest(HomeTopResult result) {
                if ("1".equals(result.getStatus()))
                    setTopData(result.getMessage());
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<HomeTopResult> call, Throwable t) {

            }
        });

    }

    private void setTopData(List<HomeTopBean> message) {
       /* tv_name1.setText(message.get(0).getName());
        tv_name2.setText(message.get(1).getName());
        tv_name3.setText(message.get(2).getName());
        float total = 0;
        for (HomeTopBean topBean : message) {
            total = total + Float.parseFloat(topBean.getValue());
        }
        DecimalFormat df = new DecimalFormat("#0.000");
        float value1 = Float.parseFloat(message.get(0).getValue()) / total;
        float value2 = Float.parseFloat(message.get(1).getValue()) / total;
        float value3 = Float.parseFloat(message.get(2).getValue()) / total;
        tv_value1.setText(Float.parseFloat(df.format(value1)) * 100 + "%");
        tv_value2.setText(Float.parseFloat(df.format(value2)) * 100 + "%");
        tv_value3.setText(Float.parseFloat(df.format(value3)) * 100 + "%");*/
        float total = 0;
        for (HomeTopBean topBean : message) {
            total = total + Float.parseFloat(topBean.getValue());
        }
        DecimalFormat df = new DecimalFormat("#0.000");
        float value1 = Float.parseFloat(message.get(0).getValue()) / total;
        float value2 = Float.parseFloat(message.get(1).getValue()) / total;
        float value3 = Float.parseFloat(message.get(2).getValue()) / total;
        circular_1.setProgress(value1);
        circular_1.setRingWidth(20);
        circular_2.setProgress(value2);
        circular_2.setRingWidth(20);
        circular_3.setProgress(value3);
        circular_3.setRingWidth(20);

    }
}