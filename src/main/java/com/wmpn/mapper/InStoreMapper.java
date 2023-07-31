package com.wmpn.mapper;

import com.wmpn.entity.InStore;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InStoreMapper {

    //添加入庫單
    @Insert("insert into warehouse.in_store(store_id, product_id, in_num, create_by, create_time, is_in) VALUES (#{storeId} ,#{productId} ,#{inNum} ,#{createBy} ,now(),0)")
    int insertInstore(InStore inStore);
}
