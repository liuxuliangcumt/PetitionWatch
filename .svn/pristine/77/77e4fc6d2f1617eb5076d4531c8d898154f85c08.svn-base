package com.realpower.petitionwatch.net;


import com.realpower.petitionwatch.net.param.AddAlarmParam;
import com.realpower.petitionwatch.net.param.AddGpsParam;
import com.realpower.petitionwatch.net.param.AddTaskParam;
import com.realpower.petitionwatch.net.param.AlarmAllParam;
import com.realpower.petitionwatch.net.param.AlarmParam;
import com.realpower.petitionwatch.net.param.ChangePhoneParam;
import com.realpower.petitionwatch.net.param.CodeParam;
import com.realpower.petitionwatch.net.param.DealParam;
import com.realpower.petitionwatch.net.param.IdParam;
import com.realpower.petitionwatch.net.param.LoginParam;
import com.realpower.petitionwatch.net.param.NewMonitorTaskParam;
import com.realpower.petitionwatch.net.param.PagingParam;
import com.realpower.petitionwatch.net.param.ResetPassworParam;
import com.realpower.petitionwatch.net.param.SearchByCriteriaParam;
import com.realpower.petitionwatch.net.param.SearchGpsByCriteriaParam;
import com.realpower.petitionwatch.net.param.SearchMonitoredParam;
import com.realpower.petitionwatch.net.param.StringIdParam;
import com.realpower.petitionwatch.net.param.TaskIdParam;
import com.realpower.petitionwatch.net.param.TaskParam;
import com.realpower.petitionwatch.net.param.TaskSelectorStatusParam;
import com.realpower.petitionwatch.net.param.TaskStateChangeParam;
import com.realpower.petitionwatch.net.result.AddGpsResult;
import com.realpower.petitionwatch.net.result.AlarmBeanResult;
import com.realpower.petitionwatch.net.result.AlarmDetailResult;
import com.realpower.petitionwatch.net.result.AlarmListResult;
import com.realpower.petitionwatch.net.result.CameraResult;
import com.realpower.petitionwatch.net.result.CityTaskResult;
import com.realpower.petitionwatch.net.result.ContactResult;
import com.realpower.petitionwatch.net.result.CountyMonitoredsResult;
import com.realpower.petitionwatch.net.result.DistrictResult;
import com.realpower.petitionwatch.net.result.GpsPointResult;
import com.realpower.petitionwatch.net.result.HomeTopResult;
import com.realpower.petitionwatch.net.result.LoginResult;
import com.realpower.petitionwatch.net.result.MonitorTaskResult;
import com.realpower.petitionwatch.net.result.MonitoredResult;
import com.realpower.petitionwatch.net.result.MyTaskResult;
import com.realpower.petitionwatch.net.result.OnlineResult;
import com.realpower.petitionwatch.net.result.PetitionDetailResult;
import com.realpower.petitionwatch.net.result.PetitionResult;
import com.realpower.petitionwatch.net.result.SixMonthMonitoredResult;
import com.realpower.petitionwatch.net.result.SuggestionDetailResult;
import com.realpower.petitionwatch.net.result.SuggestionResult;
import com.realpower.petitionwatch.net.result.SupervisorResult;
import com.realpower.petitionwatch.net.result.TaskDetailResult;
import com.realpower.petitionwatch.net.result.TaskStatusListResult;
import com.realpower.petitionwatch.net.result.TasksResult;
import com.realpower.petitionwatch.net.result.MonitoredsResult;
import com.realpower.petitionwatch.net.result.StringResult;
import com.realpower.petitionwatch.net.result.TrackPersonResult;
import com.realpower.petitionwatch.net.result.WatcherTaskResult;
import com.realpower.petitionwatch.modelwatch.bean.MonitoredBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 */
public interface ApiService {

    @POST("login")
    Call<LoginResult> login(@Body LoginParam param);

    //获取监控 任务列表
    @POST("supervisorapp/task/getAllTask")
    Call<TasksResult> getAllTask();

    //获取任务详情
    @POST("supervisorapp/task/getTaskById")
    Call<WatcherTaskResult> getTaskById(@Body TaskParam param);

    //更改任务状态
    @POST("supervisorapp/task/updateTaskStatus")
    Call<StringResult> updateTaskStatus(@Body TaskStateChangeParam param);

    //任务提前结束
    @POST("supervisorapp/task/endTaskEarly")
    Call<StringResult> endTaskEarly(@Body TaskIdParam param);

    //任务正常结束
    @POST("/supervisorapp/task/endTaskNormal")
    Call<StringResult> endTaskNormal(@Body TaskIdParam param);

    //获取重点人列表
    @POST("supervisorapp/monitored/getAllMonitored")
    Call<MonitoredsResult> getAllMonitored();

    //获取重点人详细信息
    @POST("supervisorapp/monitored/getMonitoredByid")
    Call<MonitoredResult> getMonitoredById(@Body IdParam param);

    //编辑重点人
    @POST("supervisorapp/monitored/updateMonitored")
    Call<StringResult> updateMonitored(@Body MonitoredBean bean);

    //模糊查询重点人
    @POST("supervisorapp/monitored/searchMonitored")
    Call<MonitoredsResult> searchMonitored(@Body SearchMonitoredParam param);

    //新建重点人
    @POST("supervisorapp/monitored/addMonitored")
    Call<StringResult> addMonitored(@Body MonitoredBean bean);

    //新建报警处的重点人
    @POST("supervisorapp/alarm/addTempMonitored")
    Call<StringResult> addAlarmMonitored(@Body MonitoredBean bean);

    //报警加载默认人
    @POST("supervisorapp/alarm/preAddAlarm")
    Call<AlarmBeanResult> preAddAlarm();

    //一键报警
    @POST("supervisorapp/alarm/addAlarm")
    Call<StringResult> addAlarm(@Body AlarmParam param);

    //模糊查询个人任务
    @POST("supervisorapp/task/searchTask")
    Call<TasksResult> searchTask(@Body SearchMonitoredParam param);

    //获取已完成任务
    @POST("supervisorapp/task/getFinishedTask")
    Call<TasksResult> getFinishedTask();

    //获取进行中任务
    @POST("supervisorapp/task/getUnFinishedTask")
    Call<TasksResult> getUnFinishedTask();

    //更换绑定手机号
    @POST("supervisorapp/supervisor/changePhone")
    Call<StringResult> changePhone(@Body ChangePhoneParam param);

    //更换绑定手机号发送验证码
    @POST("monitoredapp/sms/changesendCode")
    Call<StringResult> getCPhoneCode(@Body CodeParam param);

    //忘记密码/重置密码接口发送验证码
    @POST("monitoredapp/sms/fpwdsendCode")
    Call<StringResult> getResetPswCode(@Body CodeParam param);

    /*忘记密码 重置密码*/
    @POST("supervisorapp/supervisor/forgetPwd")
    Call<StringResult> resetPassword(@Body ResetPassworParam param);

    //添加Gps信息
    @POST("supervisorapp/supervisor/addGps")
    Call<AddGpsResult> addGps(@Body AddGpsParam param);


    /*工作人员接口*/
    //获取所有诉求
    @POST("staff/appeal/selectAll")
    Call<PetitionResult> getAppeal(@Body PagingParam param);

    //获取诉求详情
    @POST("staff/appeal/selectById")
    Call<PetitionDetailResult> appealDetail(@Body IdParam param);

    //诉求开始处理
    @POST("staff/appeal/startDealing")
    Call<StringResult> startDeal(@Body DealParam param);

    //诉求开始处理
    @POST("staff/appeal/endDealing")
    Call<StringResult> endDeal(@Body DealParam param);

    //h获取所有意见
    @POST("staff/suggestion/selectAll")
    Call<SuggestionResult> getSuggestion(@Body PagingParam param);

    //添加至重点人库
    @POST("staff/monitored/addToSerious")
    Call<StringResult> addToSerious(@Body DealParam param);

    //反馈意见
    @POST("staff/suggestion/startFeedback")
    Call<StringResult> suggestionFeedback(@Body DealParam param);

    //意见详情
    @POST("staff/suggestion/selectById")
    Call<SuggestionDetailResult> suggestionDetail(@Body IdParam param);

    @POST("supervisorapp/alarm/aotuAddAlarm")
    Call<StringResult> aotuAddAlarm(@Body AddAlarmParam param);

    /*
    * 县级领导模块
    * */
    //查询全部重点人
    @POST("district/monitored/selectAll")
    Call<CountyMonitoredsResult> monitoredAll(@Body PagingParam param);

    //查询全部监控人员
    @POST("district/supervisor/selectAll")
    Call<SupervisorResult> supervisorAll(@Body PagingParam param);

    //查询告警列表
    @POST("district/alarm/selectAll")
    Call<AlarmListResult> alarmAll(@Body AlarmAllParam param);

    //监控任务列表
    @POST("district/task/selectAll")
    Call<MonitorTaskResult> taskAll(@Body PagingParam param);

    //新建监控任务
    @POST("district/task/addTask")
    Call<StringResult> addTask(@Body NewMonitorTaskParam param);

    //监控任务详情
    @POST("district/task/selectById")
    Call<TaskDetailResult> taskDetail(@Body IdParam param);

    //查询任务状态
    @POST("district/task/selectStatus")
    Call<TaskStatusListResult> taskSelectStatus(@Body TaskSelectorStatusParam param);

    //查询上级分配的重点任务
    @POST("district/task/selectSelfTask")
    Call<MyTaskResult> selectSelfTask(@Body PagingParam param);

    //轨迹 获取人员信息
    @POST("district/map/searchByCriteria")
    Call<TrackPersonResult> searchByCriteria(@Body SearchByCriteriaParam param);

    //轨迹 获取坐标
    @POST("district/map/searchGpsByCriteria")
    Call<GpsPointResult> searchGpsByCriteria(@Body SearchGpsByCriteriaParam param);

    //告警详情
    @POST("district/alarm/selectById")
    Call<AlarmDetailResult> selectById(@Body StringIdParam param);

    //告警详情上报
    @POST("district/alarm/commitAlarm")
    Call<StringResult> commitAlarm(@Body StringIdParam param);

    //首页获取 分类前三
    @POST("common/getdata/getCategoryTopThree")
    Call<HomeTopResult> getCategoryTopThree();

    //首页重点人数变化
    @POST("common/getdata/getSixMonthMonitored")
    Call<SixMonthMonitoredResult> getSixMonthMonitored();

    //首页 获取在线人数
    @POST("common/getdata/getCurrentData")
    Call<OnlineResult> getCurrentData();

    //首页 获取满意度平均值
    @POST("common/getdata/getSatisfaction")
    Call<StringResult> getSatisfaction();

    //首页 获取诉求意见变化
    @POST("common/getdata/getAandS")
    Call<StringResult> getAandS();

    /*市领导*/
    // 市领导获取任务列表
    @POST("municipal/task/selectAll")
    Call<CityTaskResult> taskSelectAll(@Body PagingParam param);

    //获取上报的告警信息列表
    @POST("municipal/alarm/selectAll")
    Call<AlarmListResult> alarmSelectAll(@Body AlarmAllParam param);

    //市领导添加任务
    @POST("municipal/task/addTask")
    Call<StringResult> cityAddTask(@Body AddTaskParam param);

    //获取下级列表
    @POST("municipal/district/selectAll")
    Call<DistrictResult> districtSelectAll(@Body PagingParam param);

    //告警详情
    @POST("municipal/alarm/selectById")
    Call<AlarmDetailResult> alarmDetail(@Body StringIdParam param);

    //通讯录 http://192.168.1.114:8082/tls/getAllContacts
    @POST("tls/getAllContacts")
    Call<ContactResult> allContacts();

    @POST("camera/findCameraByPage")
    Call<CameraResult> camerasPage(@Body PagingParam param);
}
