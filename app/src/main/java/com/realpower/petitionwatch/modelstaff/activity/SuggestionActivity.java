package com.realpower.petitionwatch.modelstaff.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.modelstaff.adapter.SuggestionAdapter;
import com.realpower.petitionwatch.modelstaff.bean.SuggestionBean;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.DealParam;
import com.realpower.petitionwatch.net.param.PagingParam;
import com.realpower.petitionwatch.net.result.StringResult;
import com.realpower.petitionwatch.net.result.SuggestionResult;
import com.realpower.petitionwatch.util.MyToastUtils;
import com.realpower.petitionwatch.util.SystemInfoUtils;
import com.realpower.petitionwatch.view.CustomDialog;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

@EActivity
public class SuggestionActivity extends BaseActivity {
    SuggestionAdapter adapter;
    private List<SuggestionBean.ListBean> data;
    @ViewById
    ListView lv_suggest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        setTitleName("意见列表");
        data = new ArrayList<>();
        adapter = new SuggestionAdapter(this, data);
        lv_suggest.setAdapter(adapter);
        lv_suggest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                SuggestionDetailActivity_.intent(SuggestionActivity.this).suggestionId(data.get(position).getSuggestionId()).start();
            }
        });
        adapter.setViewClick(new SuggestionAdapter.OnViewClick() {
            @Override
            public void onBtnClick(int position) {
                showDeelDailog(position);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        Call<SuggestionResult> call = apiService.getSuggestion(new PagingParam("1"));
        call.enqueue(new MyCallback<SuggestionResult>() {
            @Override
            public void onSuccessRequest(SuggestionResult result) {
                if ("1".equals(result.getStatus())) {
                    data.clear();
                    data.addAll(result.getMessage().getList());
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<SuggestionResult> call, Throwable t) {

            }
        });
    }

    private void showDeelDailog(final int position) {
        final CustomDialog dialog = new CustomDialog(this, R.style.customDialog, R.layout.dialog_deel);
        dialog.show();
        dialog.getWindow().setWindowAnimations(R.style.customDialog);
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setGravity(Gravity.CENTER);
        layoutParams.width = (int) (SystemInfoUtils.getWindowsWidth(this) * 0.8);
        layoutParams.height = (int) (SystemInfoUtils.getWindowsWidth(this) * 0.8);
        dialog.getWindow().setAttributes(layoutParams);
        TextView tv_cancel = (TextView) dialog.getCustomView().findViewById(R.id.tv_cancel);
        TextView tv_ok = (TextView) dialog.getCustomView().findViewById(R.id.tv_ok);
        final EditText et_content = (EditText) dialog.getCustomView().findViewById(R.id.et_content);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = et_content.getText().toString();
                if (content.trim().length() != 0) {
                    deelingPitition(content, position, dialog);
                } else {
                    MyToastUtils.showToast("请输入反馈内容");
                }
            }
        });
    }

    private void deelingPitition(String content, int position, final CustomDialog dialog) {
        showMyDialog("请稍后");
        List<String> stringList = new ArrayList<>();
        stringList.add(data.get(position).getSuggestionId() + "");
        Call<StringResult> call = apiService.suggestionFeedback(new DealParam(stringList, content));
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("1".equals(result.getStatus())) {
                    if ("2".equals(result.getDesc().getCode())) {
                        MyToastUtils.showToast(result.getDesc().getDescription());
                        dialog.dismiss();
                    }
                }
            }

            @Override
            public void afterRequest() {
                hideDialog();
            }

            @Override
            public void onFailureRequest(Call<StringResult> call, Throwable t) {

            }
        });

    }
}
