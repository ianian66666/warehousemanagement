package com.wmpn.service;

import com.wmpn.entity.ProductType;
import com.wmpn.entity.Result;

import java.util.List;

public interface ProductTypeService {

    List<ProductType> queryProductTree();

    /**
     * 校驗分類編碼是否存在
     */
    Result checkTypeCode(String typeCode);

    /**
     * 添加商品分類
     * @param productType
     * @return
     */
    Result saveProductType(ProductType productType);

    /**
     * 刪除商品分類包含parentId
     * @param typeId
     * @return
     */
    Result removeProductType(Integer typeId);

    /**
     * 修改商品分類
     * @param productType
     * @return
     */
    Result updateproduct(ProductType productType);

}
