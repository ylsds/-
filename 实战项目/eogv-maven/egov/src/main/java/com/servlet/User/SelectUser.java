package com.servlet.User;

import com.ServiceImp.UserServiceImp;
import com.Util.MyHandler;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = {"/servlet/SelectUser"})
public class SelectUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        String usercode=request.getParameter("usercode");
        //业务处理
        UserService userService=(UserService) new MyHandler(new UserServiceImp()).getProxy();
        userService.SelectOneUser(request,usercode);
        //页面的展示
       request.getRequestDispatcher("/system/userUpdate.jsp").forward(request,response);
    }
}
