package com.service;


import com.domain.Invest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface InvService {
    /**
     *���Ͷ������Ϣ
     */
    int SaveInv(Object obj1, String usercode);
    /**
     * ��ҳ��ѯ��ѯͶ������Ϣ
     */
    void dynamicSelectInv(HttpServletRequest request, Map<String, Object> map, int pageSize, int pageNo);
    /**
     * ��ѯĳ��Ͷ���˵������Ϣ������Ͷ���˵ǼǱ�ţ�
     */
    void SelectOneInv(HttpServletRequest request, int Invregnum);

}
