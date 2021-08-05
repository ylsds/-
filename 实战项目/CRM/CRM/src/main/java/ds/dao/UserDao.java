package ds.dao;

import ds.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    //通过账号和密码查询是否存在该用户/时候合法
    User selectOneUser(@Param("loginAct")String loginAct,@Param("loginPwd") String loginPwd);
}
