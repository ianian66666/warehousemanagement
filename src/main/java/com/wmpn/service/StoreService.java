package com.wmpn.service;

import com.wmpn.entity.Store;
import com.wmpn.vo.StoreCountVo;

import java.util.List;

public interface StoreService {

    /**
     * 查詢所有倉庫
     * @return
     */
    List<Store> findAllStore();

    /**
     * 查詢每個倉庫商品數量
     */
    List<StoreCountVo> queryStoreCount();
}
