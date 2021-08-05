package com.servlet.User;

import com.ServiceImp.UserServiceImp;
import com.Util.Const;
import com.Util.DateUtil;
import com.Util.MyHandler;
import com.Util.WebUtil;
import com.domain.User;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = {"/servlet/Updateuser"})
public class Updateuser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("GBk");
        User user =new User();
        WebUtil.fromQuestToobj(request,user);
        user.setRegdate(DateUtil.nowFormat(Const.DATE_FORMAT_ALL,new Date()));
        //处理业务
        System.out.println(user);
        UserService userService=(UserService) new MyHandler(new UserServiceImp()).getProxy();
        int count=userService.UpdateOneUser(user);

        //跳转页面
        if (count==1)
        response.sendRedirect(request.getContextPath()+"/servlet/PageQueryUserWL");

    }
}
