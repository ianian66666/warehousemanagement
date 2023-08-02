package com.wmpn.mapper;

import com.wmpn.annotation.AutoFill;
import com.wmpn.entity.Product;
import com.wmpn.enumeration.OperationType;
import com.wmpn.page.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    /**
     * 分頁查詢 提交總數量
     * @param product
     * @return
     */
    Integer findProductRowCount(Product product);

    /**
     * 分頁查詢商品信息
     * @param page
     * @param product
     * @return
     */
    List<Product> findProductAllPage(@Param("page")Page page,@Param("product") Product product);


    /**
     * 添加商品
     * @param product
     * @return
     */
    @AutoFill(OperationType.INSERT)
    int insertProduct(Product product);

    /**
     * 根據商品型號查詢商品
     * @param productNum
     * @return
     */
    @Select("select *from warehouse.product where product_num =#{productNum} ")
    Product findProductByNum(String productNum);


    /**
     * 根據商品id修改商品狀態
     * @param product
     * @return
     */
    @AutoFill(OperationType.UPDATE)
    @Update("update warehouse.product set up_down_state=#{upDownState},update_by=#{updateBy} ,update_time= #{updateTime}   where product_id=#{productId}  ")
     int setProductStateByPid(Product product);

    /**
     * 刪除商品
     * @param productIdList
     * @return
     */
    int removeProductByPids(List<Integer> productIdList);

    /**
     * 根據商品id修改商品
     * @param product
     * @return
     */
    @AutoFill(OperationType.UPDATE)
    int setProductById(Product product);

    /**
     * 根據id修改商品庫存
     * @param productId
     * @param invent
     * @return
     */
    @Update("update warehouse.product set product_invent = product_invent + #{param2} where product_id=#{param1}")
    int setInventById(Integer productId,Integer invent);

    /**
     * 根據商品id查出庫存
     * @param productId
     * @return
     */
    @Select("select product_invent from warehouse.product where product_id=#{productId} ")
    int findInventById (Integer productId);
}
