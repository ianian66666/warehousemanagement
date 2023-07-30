package com.wmpn.service;

import com.wmpn.entity.Product;
import com.wmpn.entity.Result;
import com.wmpn.page.Page;

import java.util.List;

public interface ProductService {

    /**
     *  分頁查詢商品
     * @param page
     * @param product
     * @return
     */
    Page queryProguctListPage(Page page, Product product);

    /**
     * 添加商品
     * @param product
     * @return
     */
    Result insertProduct(Product product);

    /**
     * 根據商品id修改商品狀態
     * @param product
     * @return
     */
    Result setProductState(Product product);

    /**
     * 刪除商品
     * @param productIds
     * @return
     */
    Result removeProductByIds(List<Integer> productIds);
}
