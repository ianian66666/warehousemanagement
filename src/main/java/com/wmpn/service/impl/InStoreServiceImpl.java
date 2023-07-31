package com.wmpn.service.impl;

import com.wmpn.entity.InStore;
import com.wmpn.entity.Result;
import com.wmpn.mapper.InStoreMapper;
import com.wmpn.mapper.ProductMapper;
import com.wmpn.mapper.PurchaseMapper;
import com.wmpn.page.Page;
import com.wmpn.service.InStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InStoreServiceImpl implements InStoreService {
    @Autowired
    private InStoreMapper inStoreMapper;
    @Autowired
    private PurchaseMapper purchaseMapper;
    @Autowired
    private ProductMapper productMapper;

    @Transactional
    @Override
    public Result saveInStore(InStore inStore, Integer buyId) {
        int i = inStoreMapper.insertInstore(inStore);
        if (i > 0) {
            int j = purchaseMapper.setIsInById(buyId);
            if (j > 0) {
                return Result.ok("入庫單已新增完成");
            }
            return Result.err(Result.CODE_ERR_BUSINESS, "入庫單新增失敗");

        }

        return Result.err(Result.CODE_ERR_BUSINESS, "入庫單新增失敗");
    }

    @Override
    public Page queryInStorePage(Page page, InStore inStore) {
        Integer inStoreCount = inStoreMapper.findInStoreCount(inStore);
        List<InStore> inStorePage = inStoreMapper.findInStorePage(page, inStore);
        page.setTotalNum(inStoreCount);
        page.setResultList(inStorePage);
        return page;
    }

    @Transactional
    @Override
    public Result inStoreConfirm(InStore inStore) {
        int i = inStoreMapper.setIsInById(inStore.getInsId());
        if (i > 0) {
            //修改商品庫存
            int j = productMapper.setInventById(inStore.getProductId(), inStore.getInNum());
            if (j > 0) {
                return Result.ok("入庫單確認完成");
            }
            return Result.err(Result.CODE_ERR_BUSINESS, "入庫單確認失敗");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "入庫單確認失敗");
    }
}
