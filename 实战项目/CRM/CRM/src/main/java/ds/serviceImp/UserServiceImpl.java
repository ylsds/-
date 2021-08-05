package ds.serviceImp;

import ds.dao.UserDao;
import ds.Utils.DateTimeUtil;
import ds.domain.User;
import ds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Map<String,Object> selectOneUser(HttpServletRequest request, String loginAct, String loginPwd, String ip) {
        User user=userDao.selectOneUser(loginAct,loginPwd);
        Map<String,Object> map=new HashMap<>();
        String msg="";
        boolean success=false;
        if (user!=null){
            if (user.getExpireTime().compareTo(DateTimeUtil.getSysTime())>0){
                if ("1".equals(user.getLockState())){
//                    if ("127.0.0.1".equals(ip)){
                        success=true;
                        HttpSession session=request.getSession(true);
                        session.setMaxInactiveInterval(30*60);
                        session.setAttribute("user",user);
//                    }else{
//                        msg+="IP地址受限制！";
//                    }
                }else {
                    msg+="账号被锁定";
                }
            }else{
                msg+="账号已失效！";
            }
        }else{
            msg="该账户不存在！请重新输入！";
        }
        map.put("msg",msg);
        map.put("success",success);
        return map;
    }
}
