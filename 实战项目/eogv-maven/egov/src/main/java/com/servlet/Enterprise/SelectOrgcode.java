package com.servlet.Enterprise;

import com.ServiceImp.EnterServiceImp;
import com.Util.MyHandler;
import com.service.EnterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = {"/servlet/SelectOrgcode"})
public class SelectOrgcode extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询组织机构代码是否存在，若存在则可以进入下一步操作，否则返回该页面
        String orgcode=request.getParameter("orgcode");
        EnterService enterService=(EnterService)new MyHandler(new EnterServiceImp()).getProxy();
        boolean ok=enterService.selectOrgcode(orgcode);
        if (ok){
            request.getRequestDispatcher("/foreignExchange/inputOrgInfo.jsp").forward(request,response);
        }else{
            request.setAttribute("Error","组织机构代码已经存在，请重新输入！");
            request.getRequestDispatcher("/foreignExchange/newInputOrg.jsp").forward(request,response);
        }
    }
}
