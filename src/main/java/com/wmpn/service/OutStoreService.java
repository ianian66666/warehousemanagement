package com.wmpn.service;

import com.wmpn.entity.OutStore;
import com.wmpn.entity.Result;

public interface OutStoreService {

    /**
     * 添加出庫業務
     * @param outStore
     * @return
     */
    Result saveOutStore(OutStore outStore);
}
