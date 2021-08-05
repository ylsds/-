package com.dao;

import com.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    /**
     * 添加系统用户
     */
    int insertUser(User user);
    /**
     * 获取所有的User并封装到List集合当中
     */
    List<User> selctAllUser1();

    /**
     *利用Limit语法，查询滚顶个数的用户
     */
    List<User> selctAllUser2(@Param("begin") int begin, @Param("size") int size);

    /**
     * 根据用户代码，查询用户的相关信息
     */
    User selectOneUser(String usercode);
    /**
     * 根据用户代码，修改用户的相关信息
     */
    int updateOneUser1(User user);

    /**
     *  根据用户代码，删除单个或者多个用户的相关信息
     */
    int deleteUser(String[] usercodes);
    /**
     * 根据用户代码，查询用户的相关信息
     */
    User selectOneUser2(User user);

}
