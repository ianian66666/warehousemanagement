package com.wmpn.service.impl;

import com.wmpn.entity.InStore;
import com.wmpn.entity.Result;
import com.wmpn.mapper.InStoreMapper;
import com.wmpn.mapper.PurchaseMapper;
import com.wmpn.service.InStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InStoreServiceImpl implements InStoreService {
    @Autowired
    private InStoreMapper inStoreMapper;
    @Autowired
    private PurchaseMapper purchaseMapper;
    @Transactional
    @Override
    public Result saveInStore(InStore inStore,Integer buyId) {
        int i = inStoreMapper.insertInstore(inStore);
        if(i>0){
            int j = purchaseMapper.setIsInById(buyId);
            if (j > 0) {
                return  Result.ok("入庫單已新增完成");
            }
            return Result.err(Result.CODE_ERR_BUSINESS,"入庫單新增失敗");

        }

        return Result.err(Result.CODE_ERR_BUSINESS,"入庫單新增失敗");
    }
}
