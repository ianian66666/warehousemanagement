package com.wmpn.mapper;

import com.wmpn.entity.ProductType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface  ProductTypeMapper {

    /**
     * 查詢所有的商品分類樹
     * @return
     */
    @Select("select *from warehouse.product_type")
    List<ProductType> findAllProductTypeTree();

    /**
     * 根據分類編碼及分類名稱查詢是否有一樣的在資料庫
     * @return
     */
    ProductType findProductTypeBycodeorName(ProductType productType);

    /**
     * 添加商品分類
     * @param productType
     * @return
     */
    @Insert("insert into warehouse.product_type( parent_id, type_code, type_name, type_desc) VALUES (#{parentId} ,#{typeCode} ,#{typeName} ,#{typeDesc} )")
    int insertProductType(ProductType productType);

    /**
     * 刪除商品分類包含parentId
     * @param typeId
     * @return
     */
    @Delete("delete from warehouse.product_type where type_id=#{typeId} or parent_id=#{typeId} ")
    int removeProductType(Integer typeId);

    /**
     * 根據分類id修改商品分類
     * @param productType
     * @return
     */
    @Update("update warehouse.product_type set type_name=#{typeName} ,type_desc=#{typeDesc} where type_id=#{typeId} ")
    int updateProductType(ProductType productType);
}
