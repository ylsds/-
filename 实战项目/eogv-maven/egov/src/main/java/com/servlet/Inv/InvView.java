package com.servlet.Inv;

import com.ServiceImp.InvServiceImp;
import com.Util.MyHandler;
import com.service.InvService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/servlet/InvView"})
public class InvView extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer Invregnum=Integer.parseInt(request.getParameter("Invregnum"));
        InvService invService=(InvService)new MyHandler(new InvServiceImp()).getProxy();
        invService.SelectOneInv(request,Invregnum);
        request.getRequestDispatcher("/basicinfo/exoticOrgView.jsp").forward(request,response);

    }
}
