package com.wmpn.mapper;

import com.wmpn.entity.OutStore;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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
}
