package com.wmpn.service.impl;

import com.wmpn.entity.OutStore;
import com.wmpn.entity.Result;
import com.wmpn.mapper.OutStoreMapper;
import com.wmpn.service.OutStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutStoreServiceImpl  implements OutStoreService {

    @Autowired
    private OutStoreMapper outStoreMapper;
    @Override
    public Result saveOutStore(OutStore outStore) {
        int i = outStoreMapper.insertOutStore(outStore);
        if(i>0){
            return  Result.ok("出庫單添加成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"出庫單添加失敗");
    }
}
