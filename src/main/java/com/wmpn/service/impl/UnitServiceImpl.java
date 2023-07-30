package com.wmpn.service.impl;

import com.wmpn.entity.Unit;
import com.wmpn.mapper.UnitMapper;
import com.wmpn.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@CacheConfig(cacheNames = "com.wmpn.service.impl.UnitServiceImpl")
@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    private UnitMapper unitMapper;

    @Cacheable(key = "'all:unit'")
    @Override
    public List<Unit> queryAllUnit() {
        return unitMapper.findAllUnit();
    }
}
