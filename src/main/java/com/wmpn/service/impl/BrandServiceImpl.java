package com.wmpn.service.impl;

import com.wmpn.entity.Brand;
import com.wmpn.mapper.BrandMapper;
import com.wmpn.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@CacheConfig(cacheNames = "com.wmpn.service.impl.BrandServiceImpl")
@Service
public class BrandServiceImpl  implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Cacheable(key = "'all:brand'")
    @Override
    public List<Brand> queryAllbrand() {
        return brandMapper.findAllBrand();
    }
}
