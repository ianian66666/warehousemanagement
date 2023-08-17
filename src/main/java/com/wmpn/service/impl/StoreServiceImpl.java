package com.wmpn.service.impl;

import com.wmpn.entity.Store;
import com.wmpn.entity.User;
import com.wmpn.mapper.StoreMapper;
import com.wmpn.page.Page;
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

    @Override
    public Page queryStoreByPage(Store store, Page page) {
        Integer count = storeMapper.findRowCount(store);
        List<Store> storeByPage = storeMapper.findStoreByPage(page, store);
        page.setResultList(storeByPage);
        page.setTotalNum(count);



        return page;
    }
}
