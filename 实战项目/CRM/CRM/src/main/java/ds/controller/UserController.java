package ds.controller;

import ds.Utils.MD5Util;
import ds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value="/login.do")
    public Map<String,Object> selectUser(HttpServletRequest request,String loginAct, String loginPwd){
        String ip=request.getRemoteAddr();
        loginPwd= MD5Util.getMD5(loginPwd);
        System.out.println(ip);
        Map<String,Object> map=userService.selectOneUser(request,loginAct,loginPwd,ip);
        return map;
    }


}
