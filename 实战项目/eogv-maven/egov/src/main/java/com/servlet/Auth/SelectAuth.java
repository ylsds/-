package com.servlet.Auth;

import com.ServiceImp.AuthServiceImp;
import com.Util.MyHandler;
import com.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = {"/servlet/SelectAuth"})
public class SelectAuth extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authno=request.getParameter("authno");
        System.out.println(authno);
        AuthService authService=(AuthService)new MyHandler(new AuthServiceImp()).getProxy();
        boolean ok=authService.SelctAuth(request,authno);
        response.setContentType("text/html;charset=GBK");
        if (ok){
            request.getRequestDispatcher("/authresponse/authResponseView.jsp").forward(request,response);
        }else{
            response.getWriter().print("<font color='red'>该核准件编号不存在！</font>");
        }
    }
}
