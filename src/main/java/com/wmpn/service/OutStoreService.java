package com.wmpn.service;

import com.wmpn.entity.OutStore;
import com.wmpn.entity.Result;
import com.wmpn.page.Page;

public interface OutStoreService {

    /**
     * 添加出庫業務
     * @param outStore
     * @return
     */
    Result saveOutStore(OutStore outStore);

    /**
     * 分頁查詢出庫表
     * @param page
     * @param outStore
     * @return
     */
    Page queryOutStorePage(Page page,OutStore outStore);

    /**
     * 確認出庫
     * @param outStore
     * @return
     */
    Result outStoreConfirm(OutStore outStore);
}
