package com.wmpn.service.impl;

import com.wmpn.entity.Supply;
import com.wmpn.mapper.SupplyMapper;
import com.wmpn.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@CacheConfig(cacheNames = "com.wmpn.service.impl.SupplyServiceImpl")
@Service
public class SupplyServiceImpl implements SupplyService {
    @Autowired
    private SupplyMapper supplyMapper;

    @Cacheable(key = "'all:supply'")
    @Override
    public List<Supply> queryAllSupply() {
        return supplyMapper.findAllSupply();
    }
}
