package com.wmpn.mapper;

import com.wmpn.entity.Unit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UnitMapper {

    /**
     * 查詢所有的單位
     * @return
     */
    @Select("select *from warehouse.unit")
    List<Unit> findAllUnit();
}
