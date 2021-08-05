package ds.service;


import ds.domain.Activity;
import ds.domain.ActivityRemark;
import ds.domain.Page;
import ds.domain.User;

import java.util.List;
import java.util.Map;

public interface ActivityService {

    //查询所有的用户信息

    List<User> selectUsers();

    //添加活动信息

    boolean saveActivity(Activity activity);

    //分页查询活动信息

    List<Object> queryActivity(Page page,Activity activity);

    //批量删除
    boolean deleteActivitys(String[] args);

    //根据id查询活动信息
    Map<String,Object> selectActivity(String id);

    //修改id信息
    boolean updateActivity(Activity activity);

    //添加活动备注
    boolean addRemark(ActivityRemark activityRemark);

    //查询所有的备注活动信息
    List<Map<String,Object>> selectRemark(String activityId);
}
