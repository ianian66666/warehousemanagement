package com.wmpn.service.impl;

import com.wmpn.entity.Result;
import com.wmpn.entity.Store;
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

    @Override
    public Result checkStoreNum(String storeNum) {
        Store store = storeMapper.getStoreByNum(storeNum);
        return Result.ok(store == null);
    }

    @Override
    public Result addStore(Store store) {
        int i = storeMapper.insertStore(store);
        if(i > 0){
            return Result.ok("添加倉庫完成");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"添加倉庫失敗");
    }

    @Override
    public Result removeStoreById(Integer id) {
        int i = storeMapper.deleteStoreById(id);
        if(i > 0){
            return Result.ok("刪除倉庫完成");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"刪除倉庫失敗");
    }

    @Override
    public Result updateStoreById(Store store) {
        int i = storeMapper.updateStoreById(store);
        if(i > 0){
            return Result.ok("修改倉庫完成");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"修改倉庫失敗");
    }
}
