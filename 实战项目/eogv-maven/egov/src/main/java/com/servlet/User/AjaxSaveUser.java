package com.servlet.User;

import com.ServiceImp.UserServiceImp;
import com.Util.*;
import com.domain.User;
import com.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = {"/servlet/AjaxSaveUser"})
public class AjaxSaveUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        //封装参数
        User user =new User();
        WebUtil.fromQuestToobj(request,user);
        String datetime= DateUtil.nowFormat(Const.DATE_FORMAT_ALL);
        user.setRegdate(datetime);
        //调用代理对象
        UserService userService=(UserService) new MyHandler(new UserServiceImp()).getProxy();
        response.setContentType("text/html;charset=GBK");
        PrintWriter out=response.getWriter();
        response.setContentType("");
        //判断是否存在用户
        userService.SelectOneUser(request,request.getParameter("usercode"));
        if (request.getAttribute("user")!=null){
            out.print("<font color='red'>该用户已经存在，请重新输入</font>");
        }else{
            out.print("<font color='green'>该用户可以使用</font>");
        }


    }
}
