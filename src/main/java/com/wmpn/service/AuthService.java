package com.wmpn.service;

import com.wmpn.entity.Auth;

import java.util.List;

public interface AuthService {

    /**
     * 查詢用戶菜單樹的業務方法
     *
     * 向redus緩存-- 鍵authTree:userId值，菜單樹List<Auth>轉json字串
     *
     * @param userId
     * @return
     */

    public  List<Auth> authTreeByUid(Integer userId);

    /**
     * 查詢所有權限菜單樹
     * @return
     */
    public List<Auth> queryAllAuthTree();
}
