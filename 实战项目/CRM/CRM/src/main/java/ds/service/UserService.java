package ds.service;

import ds.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserService {

    /**
     * 验证用户是否存在/是否合法
     */
    Map<String,Object> selectOneUser(HttpServletRequest request, String loginAct, String loginPwd, String ip);
}
