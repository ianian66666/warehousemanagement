package com.wmpn.mapper;

import com.wmpn.entity.Supply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SupplyMapper {

    /**
     * 查詢所有供應商
     * @return
     */
    @Select("select * from warehouse.supply where is_delete = 0;")
    public List<Supply> findAllSupply();
}
