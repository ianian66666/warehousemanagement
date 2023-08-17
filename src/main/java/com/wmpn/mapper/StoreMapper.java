package com.wmpn.mapper;

import com.wmpn.entity.Store;
import com.wmpn.entity.User;
import com.wmpn.page.Page;
import com.wmpn.vo.StoreCountVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StoreMapper {

    /**
     * 查詢所有倉庫
     * @return
     */
    @Select("select *from warehouse.store")
    List<Store> findAllStore();


    public List<StoreCountVo> findStoreCount();

    //分頁查詢倉庫數量
    public Integer findRowCount(Store store);

    //分頁查詢倉庫
    List<Store> findStoreByPage(@Param("page") Page page, @Param("store") Store store);

    /**
     * 透過storeNum查詢倉庫
     * @param storeNum
     * @return
     */
    @Select("select *from warehouse.store where store_num=#{storeNum} ")
    Store getStoreByNum(String storeNum);

    /**
     * 添加倉庫
     * @param store
     * @return
     */
    @Insert("INSERT INTO warehouse.store(store_name, store_num, store_address, concat, phone) values (" +
            "#{storeName} ,#{storeNum} ,#{storeAddress} ,#{concat} ,#{phone} )")
    int insertStore(Store store);

    /**
     * 根據store_id刪除倉庫
     * @param id
     * @return
     */
    @Delete("DELETE from warehouse.store where store_id=#{id} ")
    int deleteStoreById(Integer id);

    /**
     * 根據store_id修改倉庫訊息
     * @param store
     * @return
     */
    @Update("update warehouse.store set store_name=#{storeName} ,store_address=#{storeAddress} ,concat=#{concat} ,phone=#{phone} where store_id=#{storeId}  ")
    int updateStoreById(Store store);
}
