package com.wmpn.service.impl;

import com.wmpn.entity.Store;
import com.wmpn.mapper.StoreMapper;
import com.wmpn.service.StoreService;
import com.wmpn.vo.StoreCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@CacheConfig(cacheNames = "com.wmpn.service.impl.StoreServiceImpl")
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;

    @Cacheable(key = " 'all:store' ")
    @Override
    public List<Store> findAllStore() {
        return storeMapper.findAllStore();
    }

    @Override
    public List<StoreCountVo> queryStoreCount() {
        return storeMapper.findStoreCount();
    }
}
