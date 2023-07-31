package com.wmpn.service;

import com.wmpn.entity.InStore;
import com.wmpn.entity.Result;
import com.wmpn.page.Page;

public interface InStoreService {

    /**
     * 添加入庫單
     */
    Result saveInStore(InStore inStore,Integer buyId);

    /**
     * 分頁查詢入庫單
     * @param page
     * @param inStore
     * @return
     */
    Page queryInStorePage(Page page,InStore inStore);

    Result inStoreConfirm(InStore inStore);
}
