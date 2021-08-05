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

@WebServlet(urlPatterns = {"/servlet/DeleteUser"})
public class DeleteUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        String[] strs=request.getParameterValues("usercode");

        //创建代理对象
        UserService userService=(UserService) new MyHandler(new UserServiceImp()).getProxy();
        int count=userService.DeleteUser(strs);
        if (count>0){
            response.sendRedirect(request.getContextPath()+"/servlet/PageQueryUserWL");
        }
    }
}
