package com.Util;

import com.domain.Invest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class InvUtil {
    private static Class aClass;
    static {
        try {
            aClass=Class.forName("com.domain.Invest");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Map<String,String> autoFromObjtoMap(Object obj){
        Map<String,String> map=new HashMap<>();
        Field[] fields=aClass.getDeclaredFields();
        for (Field f:fields) {
           String fieldName=f.getName();
            String methodName="get"+fieldName.toUpperCase().charAt(0)+fieldName.substring(1);
            try {
                Method method=aClass.getDeclaredMethod(methodName);
                String context=(String)method.invoke((Invest)obj);
                map.put(fieldName,context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
