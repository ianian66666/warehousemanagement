package com.wmpn.service;

import com.wmpn.entity.Result;
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

    /**
     * 添加倉庫時確認編號是否重複
     * @param storeNum
     * @return
     */
    Result checkStoreNum(String storeNum);

    /**
     * 添加倉庫
     *
     * @param store
     * @return
     */
    Result addStore(Store store);

    /**
     * 刪除倉庫
     * @param id
     * @return
     */
    Result removeStoreById(Integer id);

    /**
     * 修改倉庫訊息
     * @param store
     * @return
     */
    Result updateStoreById(Store store);
}
