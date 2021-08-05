package com.Util;

import java.util.ResourceBundle;

public class StringUtil {
    private static ResourceBundle bundle1= ResourceBundle.getBundle("Orgtype");
    private static ResourceBundle bundle2= ResourceBundle.getBundle("Ctytype");
    private static ResourceBundle bundle3= ResourceBundle.getBundle("Ctytype");

    /**
     * ��ȡ�������͹�����
     * @param code
     * @return
     */
    public static String getTextByCode(String code){
        return bundle1.getString(code);
    }

    /**
     * ��ȡ����/�������͹�����
     * @param code
     * @return
     */
    public static String getCtyByCode(String code){
        return bundle2.getString(code);
    }

    /**
     * �������͹�����
     */
    public static String getregcryByCode(String code){
        return bundle3.getString(code);
    }

}
