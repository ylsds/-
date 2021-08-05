package com.ServiceImp;

import com.Util.SqlSessionUtil;
import com.dao.UserDao;
import com.domain.Page;
import com.domain.User;
import com.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.ls.LSOutput;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImp implements UserService {
  private UserDao userDao=SqlSessionUtil.getCurrentSqlSession().getMapper(UserDao.class);
    /**
     * 实现添加系统用户
     * @return
     */
    @Override
    public int addUser(User user) {
        System.out.println(userDao);
        return userDao.insertUser(user);
    }

    /**
     * 逻辑分页，查询所有的用户
     * @return
     */
    @Override
    public void selectAllUser1(HttpServletRequest request,int pageSize,int pageNo) {
        //获取session
        HttpSession session=request.getSession();
        //从session中取大List集合
        List<User> biglist=(List<User>)session.getAttribute("BigList");
        //如果返回的大List集合为nu11则连接数据库执行查询语句返回结果集
        //遍历结果集封装用户对象
        //将用户对象存储到大List集合中
        //将大List集合存储到session对象中
        if (biglist==null){
            biglist=userDao.selctAllUser1();
            session.setAttribute("BigList",biglist);
        }
       // 根据页码从大List集合中取出小List集合将小List集合存储到request范围中
        List<User> smallList=new ArrayList<>();
        int beginIndex=(pageNo-1)*pageSize;
        int endIndex=(pageNo*pageSize);
        if(endIndex>biglist.size()){
            endIndex=biglist.size();
        }
        System.out.println(pageNo+","+beginIndex+","+endIndex);
        for (;beginIndex<endIndex;beginIndex++){
            smallList.add(biglist.get(beginIndex));
        }
        System.out.println(smallList.size());
        request.setAttribute("userList",smallList);
    }

    /**
     * 物理分页，实现查询所有的用户
     * @param request
     * @param pageSize
     * @param pageNo
     */
    @Override
    public void selectAllUser2(HttpServletRequest request, int pageSize, int pageNo) {

        //获取sql中的总记录条数
        List<User> list1=userDao.selctAllUser1();
        int total=list1.size();
        int pagecount=total%pageSize==0?total/pageSize:(total/pageSize)+1;
        pageNo=pageNo>pagecount?pagecount:pageNo;
        int begin=(pageNo-1)*pageSize;
        int size=(pageSize*pageNo)>list1.size()?(list1.size()-(pageNo-1)*pageSize):pageSize;

        //创建Page对象
        Page page=new Page();
        page.setPagecount(pagecount);
        page.setPageno(pageNo>pagecount?pagecount:pageNo);
        page.setPagesize(pageSize);
        page.setTotalsize(total);
        System.out.println(pagecount);

        //获取指定页数的user
        List<User> list2=userDao.selctAllUser2(begin,size);
        request.setAttribute("userList",list2);
        //记录页码信息
        request.setAttribute("page",page);





    }

    /**
     * 根据用户的代码，查询相关的信息
     * @param request
     * @param usercode
     */
    @Override
    public void SelectOneUser(HttpServletRequest request, String usercode) {
        User user=userDao.selectOneUser(usercode);
        request.setAttribute("user",user);
    }

    /**
     * 根据用户代码，修改该用户的信息
     * @param user
     */
    @Override
    public int UpdateOneUser(Object user) {
        return userDao.updateOneUser1((User)user);

    }

    /**
     * 根据用户代码，删除一个或者多个用户的信息
     * @param usercodes
     * @return
     */
    @Override
    public int DeleteUser(String[] usercodes) {
       return userDao.deleteUser(usercodes);
    }

    @Override
    public User LoginCheck(User user) {
       return userDao.selectOneUser2(user);
    }

}
