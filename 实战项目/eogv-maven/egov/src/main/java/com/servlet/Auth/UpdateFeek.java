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
@WebServlet(urlPatterns = {"/servlet/UpdateFeek"})
public class UpdateFeek  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authno=request.getParameter("authno");
        AuthService authService=(AuthService)new MyHandler(new AuthServiceImp()).getProxy();
        boolean ok=authService.UpdateAuthFeek(authno);
        if (ok){
            request.getRequestDispatcher("/authresponse/authResponseList.jsp").forward(request,response);
        }else{
            response.sendRedirect(request.getContextPath()+"/Error.html");
        }
    }
}
