package com.wmpn.service;

import com.wmpn.entity.Store;
import com.wmpn.page.Page;
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

    /**
     * 查詢倉庫的分頁查詢
     */

    Page queryStoreByPage(Store store,Page page);

}
