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


@WebServlet(urlPatterns = {"/servlet/Saveuser"})
public class Saveuser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("Utf-8");
        //封装参数
        User user = new User();
        WebUtil.fromQuestToobj(request, user);
        String datetime = DateUtil.nowFormat(Const.DATE_FORMAT_ALL);
        user.setRegdate(datetime);
        //调用代理对象
        UserService userService = (UserService) new MyHandler(new UserServiceImp()).getProxy();
        int count = userService.addUser(user);
        //进行页面展示
        //sql中设置的所有字段not null
        if (count == 1) {
            response.sendRedirect(request.getContextPath() + "/servlet/PageQueryUserWL");
        }

    }

}
