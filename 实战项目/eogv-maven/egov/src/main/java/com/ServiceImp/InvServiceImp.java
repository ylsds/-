package com.ServiceImp;

import com.Util.InvUtil;
import com.Util.SqlSessionUtil;
import com.dao.InvestDao;
import com.domain.Invest;
import com.domain.Page;
import com.service.InvService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class InvServiceImp implements InvService {
    private InvestDao investDao= SqlSessionUtil.getCurrentSqlSession().getMapper(InvestDao.class);
    /**
     * 保存投资人信息
     * @param obj1
     * @param usercode
     * @return
     */
    @Override
    public int SaveInv(Object obj1, String usercode) {
        Map<String,String> map=InvUtil.autoFromObjtoMap((Invest)obj1);
        map.put("usercode",usercode);
       return investDao.SaveInvInf(map);
    }

    /***
     * 动态sql查询投资人的信息
     * @param map
     * @return
     */
    @Override
    public void dynamicSelectInv(HttpServletRequest request, Map<String, Object> map, int pageSize, int pageNo) {
        List<Invest> list1=investDao.querySelectInv(map);
        int total=list1.size();
        int pagecount=total%pageSize==0?total/pageSize:(total/pageSize)+1;
        pageNo=pageNo>pagecount?pagecount:pageNo;
        int begin=(pageNo-1)*pageSize;
        int size=(pageSize*pageNo)>list1.size()?(list1.size()-(pageNo-1)*pageSize):pageSize;

        //创建Page对象
        Page page=new Page();
        page.setPagecount(pagecount);
        page.setPageno(pageNo>pagecount?pagecount:pageNo);
        page.setPagesize(pageSize);
        page.setTotalsize(total);

        map.put("begin",begin);
        map.put("size",size);
        //获取指定页数的user
        List<Invest> list2=investDao.querySelectInv(map);
        request.setAttribute("list",list2);
        request.setAttribute("page",page);
    }

    /**
     * 查询某个投资人的相关信息（根据投资人登记编号）
     * @param request
     * @param invregnum
     */
    @Override
    public void SelectOneInv(HttpServletRequest request, int invregnum) {
        Invest invest=investDao.SelectOne(invregnum);
        if (invest!=null){
            request.setAttribute("invest",invest);
        }

    }

}
