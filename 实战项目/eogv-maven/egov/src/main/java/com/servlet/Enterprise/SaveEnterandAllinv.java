package com.servlet.Enterprise;

import com.ServiceImp.EnterServiceImp;
import com.Util.*;
import com.domain.En_Inv;
import com.domain.Enterprise;
import com.domain.User;
import com.service.EnterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/servlet/SaveEnterandAllinv"})
public class SaveEnterandAllinv extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //封装Enterprise对象
        Enterprise enterprise=new Enterprise();
        WebUtil.fromQuestToobj(request,enterprise);
        String regdate= DateUtil.nowFormat(Const.DATE_FORMAT,new Date());
        enterprise.setRegdate(regdate);
        enterprise.setRegcap(Integer.parseInt(request.getParameter("regcap")));
        enterprise.setOutregcap(Integer.parseInt(request.getParameter("outregcap")));
        String usercode=((User)request.getSession(false).getAttribute("user")).getUsercode();
        enterprise.setUsercode(usercode);

        //封装En_Inv对象
        List<En_Inv> list=new ArrayList<>();
        String[] invregnum=request.getParameterValues("invregnum");
        String[] regcapItems=request.getParameterValues("regcapItem");
        String[] scales=request.getParameterValues("scale");
        for (int i = 0; i <invregnum.length ; i++) {
            En_Inv eninv=new En_Inv();
            eninv.setInvregnum(invregnum[i]);
            eninv.setOrgcode(request.getParameter("orgcode"));
            eninv.setScale(Integer.parseInt(scales[i]));
            eninv.setRegcap(Integer.parseInt(regcapItems[i]));
            list.add(eninv);
        }
        EnterService enterService=(EnterService)new MyHandler(new EnterServiceImp()).getProxy();
        boolean ok=enterService.SaveEnterandInv(request,enterprise,list);
        if (ok){
            response.sendRedirect(request.getContextPath()+"/foreignExchange/newInputOrg.jsp");
        }
    }
}
