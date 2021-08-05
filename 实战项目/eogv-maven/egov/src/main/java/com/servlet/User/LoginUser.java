package com.servlet.User;

import com.ServiceImp.UserServiceImp;
import com.Util.MyHandler;
import com.Util.WebUtil;
import com.domain.User;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(urlPatterns = {"/servlet/LoginUser"})
public class LoginUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //封装参数
        User user =new User();
        WebUtil.fromQuestToobj(request,user);
        //调用代理对象
        UserService userService=(UserService) new MyHandler(new UserServiceImp()).getProxy();
        User user1=userService.LoginCheck(user);
        if (user1!=null){
            HttpSession session=request.getSession();
            session.setAttribute("user",user1);
            response.sendRedirect(request.getContextPath()+"/main.html");
        }else{
            request.setAttribute("error","用户名或者密码输入错误！");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }
}
