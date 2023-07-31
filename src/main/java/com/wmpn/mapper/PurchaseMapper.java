package com.wmpn.mapper;

import com.wmpn.entity.Purchase;
import com.wmpn.page.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PurchaseMapper {

    /**
     * 添加採購單
     * @param purchase
     * @return
     */
    @Insert("insert into warehouse.buy_list(product_id, store_id, buy_num, fact_buy_num, buy_time, supply_id, place_id, buy_user, phone, is_in) " +
            "VALUES (#{productId} ,#{storeId} ,#{buyNum} ,#{factBuyNum} ,now(),#{supplyId} ,#{placeId} ,#{buyUser} ,#{phone} ,0 )")
    int insertPurchase(Purchase purchase);

    //查詢採購單行數
    Integer findPurhaseCount(Purchase purchase);
    //分頁查詢採購單數據
    List<Purchase> findPurchasePage(@Param("page") Page page,@Param("purchase") Purchase purchase);

    /**
     * 刪除採購單
     * @param buyId
     * @return
     */
    @Delete("delete from warehouse.buy_list where buy_id=#{buyId} ")
    int removePurchaseById(Integer buyId);

    /**
     * 根據id修改預計採購數量及實際採購數量
     */
    @Update("update warehouse.buy_list set buy_num=#{buyNum} ,fact_buy_num=#{factBuyNum}  where buy_id=#{buyId} ")
    int setNumById(Purchase purchase);

    //根據id修改採購單狀態為已入庫

    @Update("update warehouse.buy_list set is_in= 1 where buy_id=#{buyId} ")
    int  setIsInById(Integer buyId);

}
