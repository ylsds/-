package com.service;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    /**
     * �����׼����Ϣ
     */
    boolean saveAuto(Object obj);

    /**
     * ��ѯ��׼������ϸ��Ϣ
     */
    boolean SelctAuth(HttpServletRequest request, String authno);

    /**
     * �޸ĺ�׼���ķ���Ϊ��
     */
    boolean UpdateAuthFeek(String authno);
}
