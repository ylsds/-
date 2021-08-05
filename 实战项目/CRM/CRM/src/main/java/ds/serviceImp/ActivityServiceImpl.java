package ds.serviceImp;

import ds.dao.ActivityDao;
import ds.domain.Activity;
import ds.domain.ActivityRemark;
import ds.domain.Page;
import ds.domain.User;
import ds.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Override
    public List<User> selectUsers() {
        return activityDao.selectUsers();
    }

    @Override
    public boolean saveActivity(Activity activity) {
        int count=activityDao.saveActivity(activity);
        if (count!=1){
            return false;
        }
        return true;
    }

    @Override
    public List<Object> queryActivity(Page page,Activity activity) {

        Map<String,Object> map=new HashMap<>();
        map.put("name",activity.getName());
        map.put("owner",activity.getOwner());
        map.put("startDate",activity.getStartDate());
        map.put("endDate",activity.getEndDate());

        int pagetotel=(activityDao.selectActivitys(map)).size();
        int pagecount=(pagetotel%page.getPagesize()==0)?pagetotel/page.getPagesize():(pagetotel/page.getPagesize())+1;
        int begin=((page.getPageno()-1)*page.getPagesize()>pagetotel)?(pagecount-1)*page.getPagesize():(page.getPageno()-1)*page.getPagesize();
        System.out.println(pagecount);
        System.out.println(pagetotel);
        System.out.println(begin);
        page.setBegin(begin);
        page.setPagecount(pagecount);
        page.setPagetotel(pagetotel);
        System.out.println("===============");
        System.out.println(pagecount);
        System.out.println("==============");

        //动态sql
        map.put("begin",begin);
        map.put("pagesize",page.getPagesize());
        List<Object> list= activityDao.synamicSelectActivitys(map);
        list.add(page);
        return list;
    }

    @Transactional(rollbackFor ={Exception.class})
    @Override
    public boolean deleteActivitys(String[] args) {
        int count=activityDao.deleteActivity(args);
        count+=activityDao.deleteActivityRemark(args);
        if (count>0){
            return true;
        }
        return false;
    }

    @Override
    public Map<String,Object> selectActivity(String id) {
              List<Map<String,Object>> list=  activityDao.selectActivity(id);
                 return list.get(0);
    }

    @Override
    public boolean updateActivity(Activity activity) {
        int count=activityDao.updateActivity(activity);
        if (count==1){
            return true;
        }
        return false;
    }

    @Override
    public boolean addRemark(ActivityRemark activityRemark) {
        int count=activityDao.addRemark(activityRemark);
        if (count==1){
            return true;
        }
        return false;
    }

    @Override
    public List<Map<String, Object>> selectRemark(String activityId) {
        return activityDao.selectRemark(activityId);
    }


}
