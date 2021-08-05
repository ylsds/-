package com.Util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.IOException;


public class SqlSessionUtil {
   private static SqlSessionFactory factory=null;
   private static ThreadLocal<SqlSession> local=new ThreadLocal<>();
   static {
       String fileName="Mybatis.xml";
       try {
           factory= new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(fileName));
       } catch (IOException e) {
           e.printStackTrace();
       }
   }


    /***
     * 获取sqlSession
     * @return
     */
   public static SqlSession getCurrentSqlSession() {
       SqlSession sqlSession = local.get();
       if (sqlSession == null) {
           sqlSession = factory.openSession();
           local.set(sqlSession);
       }
       return sqlSession;
   }

    /***
     * 回滚事务
     */
    public static void RollBack(SqlSession sql){
        if (sql!=null){
            sql.rollback();
        }
    }
    /**
     * 关闭连接对象,并接触与当前线程 的连接
     */
    public static void close(SqlSession sql){
        if (sql!=null){
            sql.close();
            local.remove();
        }
    }

}
