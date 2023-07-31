package com.wmpn.service.impl;

import com.wmpn.entity.OutStore;
import com.wmpn.entity.Result;
import com.wmpn.mapper.OutStoreMapper;
import com.wmpn.mapper.ProductMapper;
import com.wmpn.page.Page;
import com.wmpn.service.OutStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OutStoreServiceImpl  implements OutStoreService {

    @Autowired
    private OutStoreMapper outStoreMapper;
    @Autowired
    private ProductMapper productMapper;
    @Override
    public Result saveOutStore(OutStore outStore) {
        int i = outStoreMapper.insertOutStore(outStore);
        if(i>0){
            return  Result.ok("出庫單添加成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"出庫單添加失敗");
    }

    @Override
    public Page queryOutStorePage(Page page, OutStore outStore) {
        Integer outStoreCount = outStoreMapper.findOutStoreCount(outStore);
        List<OutStore> outStorePage = outStoreMapper.findOutStorePage(page, outStore);
        page.setTotalNum(outStoreCount);
        page.setResultList(outStorePage);


        return page;
    }

    @Transactional
    @Override
    public Result outStoreConfirm(OutStore outStore) {
        int inventById = productMapper.findInventById(outStore.getProductId());
            if(inventById <outStore.getOutNum()){
                return Result.err(Result.CODE_ERR_BUSINESS,"商品庫存不足");
            }
        int i = outStoreMapper.setIsoutById(outStore.getOutsId());
            if(i>0){
                int j = productMapper.setInventById(outStore.getProductId(), -outStore.getOutNum());
                if(j>0){
                    return Result.ok("確認出庫成功");
                }
                return Result.err(Result.CODE_ERR_BUSINESS,"確認出庫失敗");
            }
        return Result.err(Result.CODE_ERR_BUSINESS,"確認出庫失敗");
    }
}
