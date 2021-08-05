package com.dao;

import com.domain.En_Inv;
import com.domain.Enterprise;
import com.domain.Invest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface EnterDao {

    /**
     * ���ݻ��������ѯ�����Ϣ
     * @param orgcode
     * @return
     */
    Enterprise selectOrg(String orgcode);

    /**
     * ���湫˾�����Ϣ
     */
    int SaveEnter(Enterprise enterprise);
    /**
     * ��̬SQL���湫˾��Ͷ���˹�ϵ��
     */
    int SaveEnterandInv(List<En_Inv> list);

    /**
     *
     */
    List<Object> querySelectEnter(Map<String, Object> map);

}
