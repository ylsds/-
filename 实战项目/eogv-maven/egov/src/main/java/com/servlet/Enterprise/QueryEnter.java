package com.servlet.Enterprise;

import com.ServiceImp.EnterServiceImp;
import com.ServiceImp.InvServiceImp;
import com.Util.MyHandler;
import com.service.EnterService;
import com.service.InvService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/servlet/QueryEnter"})
public class QueryEnter extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageno=Integer.parseInt(request.getParameter("pageNo")==null?"1":request.getParameter("pageNo"));
        String orgcode=request.getParameter("orgcode");
        String cnname=request.getParameter("cnname");
        String startdate=request.getParameter("startdate");
        String enddate=request.getParameter("enddate");
        Map<String,Object> map=new HashMap<>();
        map.put("orgcode",orgcode);
        map.put("cnname",cnname);
        map.put("startdate",startdate);
        map.put("enddate",enddate);
        EnterService enterService=(EnterService)new MyHandler(new EnterServiceImp()).getProxy();
        enterService.dynamicSelectEnter(request,map,3,pageno);
        System.out.println("================"+request.getParameter("page"));
        if ("1".equals(request.getParameter("page"))){
            request.getRequestDispatcher("/auth/orgcodeSelect.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("/auth/openAccountAuthDetail.jsp").forward(request,response);
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
