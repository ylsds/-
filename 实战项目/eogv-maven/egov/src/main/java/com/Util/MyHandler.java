package com.Util;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyHandler implements InvocationHandler {

    private Object target;
    public MyHandler(Object target){
        this.target=target;
    }


    /**
     * 获取代理对象
     */
    public Object getProxy(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }
    /**
     * 动态代理实现事务的处理
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SqlSession sqlSession = null;
        Object value=null;
        try {
            //开启事务
            sqlSession = SqlSessionUtil.getCurrentSqlSession();
            System.out.println(sqlSession);
            value=method.invoke(target, args);
            //提交事务
            sqlSession.commit();
        } catch (Exception e) {
            //回滚事务
            SqlSessionUtil.RollBack(sqlSession);
            e.printStackTrace();
        } finally {
            SqlSessionUtil.close(sqlSession);
        }

        return value;
    }
    }

