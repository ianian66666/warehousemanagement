package com.wmpn.mapper;

import com.wmpn.annotation.AutoFill;
import com.wmpn.entity.InStore;
import com.wmpn.enumeration.OperationType;
import com.wmpn.page.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface InStoreMapper {

    //添加入庫單
    @Insert("insert into warehouse.in_store(store_id, product_id, in_num, create_by, create_time, is_in) VALUES (#{storeId} ,#{productId} ,#{inNum} ,#{createBy} ,now(),0)")
    int insertInstore(InStore inStore);

    //查詢入庫單行數
    Integer findInStoreCount(InStore inStore);
    //分頁查詢入庫單
    List<InStore> findInStorePage(@Param("page") Page page,@Param("inStore") InStore inStore);
    /**
     * 根據id修改入庫單狀態為已入庫
     */
    @Update("update warehouse.in_store set is_in=1 where ins_id=#{inStoreId} ")
        int setIsInById(Integer inStoreId);

}
