package com.wmpn.mapper;

import com.wmpn.entity.ProductType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductTypeMapper {

    /**
     * 查詢所有的商品分類樹
     * @return
     */
    @Select("select *from warehouse.product_type")
    List<ProductType> findAllProductTypeTree();
}
