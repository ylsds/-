package ds.dao;

import ds.domain.Activity;
import ds.domain.ActivityRemark;
import ds.domain.Page;
import ds.domain.User;

import java.util.List;
import java.util.Map;

public interface ActivityDao {

    //查询所有的用户
    List<User> selectUsers();


    //添加活动信息
    int saveActivity(Activity activity);

    //查询所有的活动信息
    List<Object> selectActivitys(Map<String,Object> map);

    //动态sql查询活动信息
    List<Object> synamicSelectActivitys(Map<String,Object> map);

    //删除活动
    int deleteActivity(String[] args);

    //删除活动备注
    int deleteActivityRemark(String[] args);

    //查询活动信息
    List<Map<String,Object>> selectActivity(String id);

    //修改活动信息
    int updateActivity(Activity activity);

    //添加活动备注信息
    int addRemark(ActivityRemark activityRemark);

    //查询对应活动的所有的备注
    List<Map<String,Object>> selectRemark(String activityId);
}
