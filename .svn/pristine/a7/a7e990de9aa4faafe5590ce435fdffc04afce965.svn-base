package com.realpower.petitionwatch.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.adapter.CamerasAdapter;
import com.realpower.petitionwatch.bean.CameraBean;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.PagingParam;
import com.realpower.petitionwatch.net.result.CameraResult;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
@EActivity
public class CamerasActivity extends BaseActivity {

    CamerasAdapter adapter;
    @ViewById
    ListView lv_camera;
    private List<CameraBean.ListBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cameras);
        setTitleName("监控视频");
        if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(this))
            return;
        //  VideoView mVideoView = (VideoView) findViewById(R.id.surface_view);
        /*会有几秒延时*/
        data = new ArrayList<>();
        adapter = new CamerasAdapter(this, data);
        lv_camera.setAdapter(adapter);
        getData();
        lv_camera.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CamerasLiveActivity_.intent(CamerasActivity.this).path(data.get(i).getRtmpUrl()).start();
            }
        });
    }

    private void getData() {
        Call<CameraResult> call = apiService.camerasPage(new PagingParam("1"));
        call.enqueue(new MyCallback<CameraResult>() {
            @Override
            public void onSuccessRequest(CameraResult result) {
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
            public void onFailureRequest(Call<CameraResult> call, Throwable t) {

            }
        });
    }
}
