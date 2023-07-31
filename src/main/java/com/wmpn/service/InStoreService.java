package com.wmpn.service;

import com.wmpn.entity.InStore;
import com.wmpn.entity.Result;

public interface InStoreService {

    /**
     * 添加入庫單
     */
    Result saveInStore(InStore inStore,Integer buyId);
}
