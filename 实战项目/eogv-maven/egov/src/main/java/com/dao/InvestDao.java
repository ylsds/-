package com.dao;


import com.domain.Invest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface InvestDao {
        /**
         * ���Ͷ������Ϣ
         */
        int SaveInvInf(Map<String, String> map);//@Param("usercode") String usercode

        /**
         * ʹ�ö�̬SQL��ѯͶ������Ϣ(�������)
         */
        List<Invest> querySelectInv(Map<String, Object> map);
        /**
         * �Ƕ�̬Sql��ѯĳ��Ͷ���˵������Ϣ(����Ͷ���˵ǼǱ��)
         */
        Invest SelectOne(int Invregnum);


}
