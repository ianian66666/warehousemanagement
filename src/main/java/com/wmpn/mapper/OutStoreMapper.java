package com.wmpn.mapper;

import com.wmpn.entity.OutStore;
import com.wmpn.page.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OutStoreMapper {

    /**
     * 添加出庫單
     * @param outStore
     * @return
     */
    @Insert("INSERT INTO warehouse.out_store(product_id, store_id, out_price, out_num, create_by, create_time, is_out) VALUES " +
            "(#{productId} ,#{storeId} ,#{salePrice} ,#{outNum} ,#{createBy} ,now(),0)")
    int insertOutStore(OutStore outStore);

    //查詢行數
        Integer findOutStoreCount(OutStore outStore);
    //查詢出庫分頁數據
    List<OutStore> findOutStorePage(@Param("page") Page page,@Param("outStore") OutStore outStore);

    //根據id修改出庫單狀態
    @Update("update warehouse.out_store set is_out=1 where outs_id=#{outStoreId} ")
    int setIsoutById(Integer outStoreId);
}
