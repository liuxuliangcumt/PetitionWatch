package com.realpower.petitionwatch.modelstaff.fragment;

import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.fragment.BaseFragment;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.DealParam;
import com.realpower.petitionwatch.net.param.PagingParam;
import com.realpower.petitionwatch.net.result.PetitionResult;
import com.realpower.petitionwatch.net.result.StringResult;
import com.realpower.petitionwatch.modelstaff.activity.PetitionDetailActivity_;
import com.realpower.petitionwatch.modelstaff.adapter.PetitionAdapter;
import com.realpower.petitionwatch.modelstaff.bean.PetitionBean;
import com.realpower.petitionwatch.util.MyToastUtils;
import com.realpower.petitionwatch.util.SystemInfoUtils;
import com.realpower.petitionwatch.view.CustomDialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by Administrator on 2018/1/15.
 */
@EFragment(R.layout.fragment_petition)
public class PetitionFragment extends BaseFragment {
    PetitionAdapter adapter;
    private List<PetitionBean.ListBean> data;
    @ViewById
    ListView lv_petition;

    @AfterViews
    void onInitView() {
        setTitleName("诉求列表");
        data = new ArrayList<>();
        adapter = new PetitionAdapter(getActivity(), data);
        lv_petition.setAdapter(adapter);
        lv_petition.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                PetitionDetailActivity_.intent(getActivity()).appealId(data.get(position).getAppealId()).start();
            }
        });
        adapter.setViewClick(new PetitionAdapter.OnViewClick() {
            @Override
            public void onBtnClick(int position) {
                deelingPitition(position, data.get(position).getAppealId() + "");

            }
        });
        getdata();
    }

    private void showDeelDailog(final int position) {
        final CustomDialog dialog = new CustomDialog(getActivity(), R.style.customDialog, R.layout.dialog_deel);
        dialog.show();
        dialog.getWindow().setWindowAnimations(R.style.customDialog);
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setGravity(Gravity.CENTER);
        layoutParams.width = (int) (SystemInfoUtils.getWindowsWidth(getActivity()) * 0.8);
        layoutParams.height = (int) (SystemInfoUtils.getWindowsWidth(getActivity()) * 0.8);
        dialog.getWindow().setAttributes(layoutParams);
        TextView tv_cancel = (TextView) dialog.getCustomView().findViewById(R.id.tv_cancel);
        TextView tv_ok = (TextView) dialog.getCustomView().findViewById(R.id.tv_ok);
        final EditText et_content = (EditText) dialog.getCustomView().findViewById(R.id.et_content);
        TextView tv_title = (TextView) dialog.getCustomView().findViewById(R.id.tv_title);
        tv_title.setText("诉求处理");
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
                    //   deelingPitition(content, position, dialog);
                } else {
                    MyToastUtils.showToast("请输入处理内容");
                }
            }
        });
    }

    private void deelingPitition(final int position, String criterias) {
        List<String> stringList = new ArrayList<>();
        stringList.add(criterias);
        Call<StringResult> call = apiService.startDeal(new DealParam(stringList));
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("1".equals(result.getStatus())) {
                    if ("2".equals(result.getDesc().getCode())) {
                        data.get(position).setCurrentStatus(2);
                        adapter.notifyDataSetChanged();
                        MyToastUtils.showToast("更新状态成功");
                    }
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<StringResult> call, Throwable t) {

            }
        });
    }

    private void getdata() {
        Call<PetitionResult> call = apiService.getAppeal(new PagingParam("46"));
        call.enqueue(new MyCallback<PetitionResult>() {
            @Override
            public void onSuccessRequest(PetitionResult result) {
                data.addAll(result.getMessage().getList());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<PetitionResult> call, Throwable t) {

            }
        });

    }

}
