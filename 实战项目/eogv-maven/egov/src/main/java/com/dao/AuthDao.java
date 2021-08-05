package com.dao;

import com.domain.Auth;

public interface AuthDao {
    /**
     * 向核准件信息表添加信息
     */
    int saveAuth1(Auth auth);

    /**
     * 根据核准件编号查询核准件的详细信息
     */
    Auth SelectAuth(String authno);

    /**
     * 修改核准件的信息
     */
    int UpdateAuthFeek(String authno);

}
