package com.wmpn.service;

import com.wmpn.entity.Store;

import java.util.List;

public interface StoreService {

    /**
     * 查詢所有倉庫
     * @return
     */
    List<Store> findAllStore();
}
