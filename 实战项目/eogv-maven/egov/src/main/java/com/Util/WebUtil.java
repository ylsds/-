package com.Util;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;

public class WebUtil {
    /**
     * 利用反射机制，实现自动将请求参数赋值到domaim对象上
     * --使用前提：
     *      --该方法的形参只有一个，且都为String
     *      --domaim的成员变量名必须与网页中的form表单中的name属性一致
     *
     * @param request
     * @param obj
     */
    public static void fromQuestToobj(HttpServletRequest request,Object obj){

        //获取domain的字节码文件
        Class c=obj.getClass();

        //获取所有的 字段名
        Enumeration<String> fieldNames=request.getParameterNames();


        while (fieldNames.hasMoreElements()){

                //获取字段名
                String fieldName=fieldNames.nextElement();

                //拼接domain方法
                String methodName="set"+fieldName.toUpperCase().charAt(0)+fieldName.substring(1);
            try {
                //得到需要调用的方法
                Method method=c.getDeclaredMethod(methodName,String.class);

                //调用方法，动态将值赋值给domain对象
                method.invoke(obj,request.getParameter(fieldName));
            } catch (Exception e) {
                //e.printStackTrace();
            }

        }



    }
}
