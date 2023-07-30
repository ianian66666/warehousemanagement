package com.wmpn.mapper;

import com.wmpn.entity.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StoreMapper {

    /**
     * 查詢所有倉庫
     * @return
     */
    @Select("select *from warehouse.store")
    List<Store> findAllStore();
}
