package com.ServiceImp;

import com.Util.SqlSessionUtil;
import com.dao.AuthDao;
import com.domain.Auth;
import com.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.FactoryConfigurationError;

public class AuthServiceImp implements AuthService {
    private  AuthDao authDao= SqlSessionUtil.getCurrentSqlSession().getMapper(AuthDao.class);
    /**
     * 保存核准件信息
     * @param obj
     * @return
     */
    @Override
    public boolean saveAuto(Object obj) {
            int count=authDao.saveAuth1((Auth)obj);
            if (count==1){
                return true;
            }
            return false;
    }

    /**
     * 查询核准件的相关信息
     * @param request
     * @param authno
     */
    @Override
    public boolean SelctAuth(HttpServletRequest request, String authno) {
        Auth auth=authDao.SelectAuth(authno);
        System.out.println(auth);
        if (auth!=null){
            request.setAttribute("auth",auth);
            return true;
        }
        return false;
    }

    /**
     * 修改核准件的信息
     * @param authno
     * @return
     */
    @Override
    public boolean UpdateAuthFeek(String authno) {
        int count=authDao.UpdateAuthFeek(authno);
        if (count==1){
            return true;
        }
        return false;
    }
}
