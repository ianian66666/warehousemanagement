package com.wmpn.mapper;

import com.wmpn.entity.Place;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PlaceMapper {

    /**
     * 查詢所有的產地
     * @return
     */
    @Select("select *from warehouse.place where is_delete=0")
    List<Place> findAllPlace();
}
