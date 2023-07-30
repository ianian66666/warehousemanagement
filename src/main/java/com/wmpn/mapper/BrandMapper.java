package com.wmpn.mapper;

import com.wmpn.entity.Brand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BrandMapper {

    @Select("select *from warehouse.brand")
    List<Brand> findAllBrand();
}

