package com.wmpn.mapper;

import com.wmpn.entity.Store;
import com.wmpn.entity.User;
import com.wmpn.page.Page;
import com.wmpn.vo.StoreCountVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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


    public List<StoreCountVo> findStoreCount();

    //分頁查詢倉庫數量
    public Integer findRowCount(Store store);

    //分頁查詢倉庫
    List<Store> findStoreByPage(@Param("page") Page page, @Param("store") Store store);
}
