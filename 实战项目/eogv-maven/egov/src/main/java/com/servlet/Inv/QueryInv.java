package com.servlet.Inv;

import com.ServiceImp.InvServiceImp;
import com.Util.MyHandler;
import com.domain.Invest;
import com.service.InvService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/servlet/QueryInv"})
public class QueryInv extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageno=Integer.parseInt(request.getParameter("pageNo")==null?"1":request.getParameter("pageNo"));
        Integer invregnum=null;
        if (!request.getParameter("invregnum").equals("")){
            invregnum=Integer.parseInt(request.getParameter("invregnum"));

        }
        String invname=request.getParameter("invname");
        String startdate=request.getParameter("startdate");
        String enddate=request.getParameter("enddate");
        Map<String,Object> map=new HashMap<>();
        map.put("invregnum",invregnum);
        map.put("invname",invname);
        map.put("startdate",startdate);
        map.put("enddate",enddate);
        InvService invService=(InvService) new MyHandler(new InvServiceImp()).getProxy();
       invService.dynamicSelectInv(request,map,3,pageno);

        /**
         * 根据路径，选择不同的重定向路径
         */
        String path=request.getParameter("goPage");
        if (request.getAttribute("list")!=null){
            if ("2".equals(path)){
                request.getRequestDispatcher("/basicinfo/exoticOrgList.jsp").forward(request,response);
            }else{
                request.getRequestDispatcher("/foreignExchange/orgcodeSelect.jsp").forward(request,response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
