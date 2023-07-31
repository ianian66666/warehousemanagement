package com.wmpn.service;

import com.wmpn.entity.Purchase;
import com.wmpn.entity.Result;
import com.wmpn.page.Page;

public interface PurchaseService {

    /**
     * 添加採購單
     * @param purchase
     * @return
     */


    Result savePurchase(Purchase purchase);

    /**
     * 分頁查詢採購表
     * @param page
     * @param purchase
     * @return
     */
    public Page queryPurchasePage(Page page,Purchase purchase);

    /**
     * 依據buy_list id刪除採購單
     * @param buyId
     * @return
     */
    Result deletePurchaseById(Integer buyId);

    /**
     * 根據id修改預計採購數量及實際採購數量
     * @param purchase
     * @return
     */
    Result updatePurchaseById(Purchase purchase);
}
