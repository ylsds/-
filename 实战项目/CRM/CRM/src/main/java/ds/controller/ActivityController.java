package ds.controller;

import ds.Utils.DateTimeUtil;
import ds.Utils.UUIDUtil;
import ds.domain.Activity;
import ds.domain.ActivityRemark;
import ds.domain.Page;
import ds.domain.User;
import ds.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.DataOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/activity")
@Controller
public class ActivityController {

    @Autowired
    private ActivityService service;


    @ResponseBody
    @RequestMapping("/selectUsers")
    public List<User> findUser(){
        return service.selectUsers();
    }

    @ResponseBody
    @RequestMapping("/save.do")
    public Map<String,Object> saveActivity(HttpServletRequest request, Activity activity){
        activity.setId(UUIDUtil.getUUID());
        activity.setCreateTime(DateTimeUtil.getSysTime());
        activity.setCreateBy(((User)request.getSession(false).getAttribute("user")).getName());
        boolean ok=service.saveActivity(activity);
        Map<String,Object> map=new HashMap<>();
        map.put("success",ok);
        return map;
    }


    @ResponseBody
    @RequestMapping("/queryActivity")
    public List<Object> queryActivity(Page page,Activity activity){
        System.out.println(activity);
        return service.queryActivity(page,activity);
    }


    @ResponseBody
    @RequestMapping("/deleteActivity")
    public Boolean deleteActivity(HttpServletRequest request){
        String[] parms=request.getParameterValues("id");
        return service.deleteActivitys(parms);
    }

    @ResponseBody
    @RequestMapping("/selectActivity")
    public Map<String,Object> selectActivity(String id){
        return service.selectActivity(id);
    }

    @ResponseBody
    @RequestMapping("/updateActivity")
    public Map<String,Object> updateActivity(HttpServletRequest request,Activity activity){
        activity.setEditTime(DateTimeUtil.getSysTime());
        activity.setEditBy(((User)request.getSession(false).getAttribute("user")).getId());
        boolean ok=service.updateActivity(activity);
        Map<String,Object> map=new HashMap<>();
        map.put("success",ok);
        return map;
    }
    @RequestMapping("/detail.do")
    public ModelAndView updateActivity(String id){
        ModelAndView mv=new ModelAndView();
        Map<String,Object> map=service.selectActivity(id);
        mv.addObject("activityMap",map);
        mv.setViewName("/workbench/activity/detail.jsp");
       return mv;
    }

    @ResponseBody
    @RequestMapping("/addRemark")
    public Boolean addRemark(HttpServletRequest request,ActivityRemark activityRemark){
        activityRemark.setCreateTime(DateTimeUtil.getSysTime());
        activityRemark.setCreateBy(((User)request.getSession(false).getAttribute("user")).getId());
        activityRemark.setId(UUIDUtil.getUUID());
        return service.addRemark(activityRemark);
    }

    @ResponseBody
    @RequestMapping("/selectRemark")
    public List<Map<String,Object>> selectRemark(String activityId){
        return service.selectRemark(activityId);
    }


}
