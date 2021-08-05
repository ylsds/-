package com.servlet.Inv;

import com.ServiceImp.InvServiceImp;
import com.Util.Const;
import com.Util.DateUtil;
import com.Util.MyHandler;
import com.Util.WebUtil;
import com.domain.Invest;
import com.domain.User;
import com.service.InvService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = {"/servlet/SaveInv"})
public class SaveInv extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Invest invest=new Invest();
        WebUtil.fromQuestToobj(request,invest);
        invest.setRegdate(DateUtil.nowFormat(Const.DATE_FORMAT,new Date()));

        HttpSession session=request.getSession(false);
        User user=(User)session.getAttribute("user");
        String usercode=user.getUsercode();
        InvService invService=(InvService) new MyHandler(new InvServiceImp()).getProxy();
        int  count=invService.SaveInv(invest,usercode);
        if (count==1){
            response.setContentType("text/html;charset=GBK");
            response.sendRedirect(request.getContextPath()+"/basicinfo/exoticOrgList.jsp");
        }
    }
}
