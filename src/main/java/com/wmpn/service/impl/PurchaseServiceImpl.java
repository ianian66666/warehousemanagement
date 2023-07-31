package com.wmpn.service.impl;

import com.wmpn.entity.Purchase;
import com.wmpn.entity.Result;
import com.wmpn.mapper.PurchaseMapper;
import com.wmpn.page.Page;
import com.wmpn.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
   private PurchaseMapper purchaseMapper;
    @Override
    public Result savePurchase(Purchase purchase) {



        int i = purchaseMapper.insertPurchase(purchase);
        if(i>0){
            return  Result.ok("添加採購單成功");
        }


        return Result.err(Result.CODE_ERR_BUSINESS,"添加採購單失敗");
    }

    @Override
    public Page queryPurchasePage(Page page, Purchase purchase) {
        Integer purhaseCount = purchaseMapper.findPurhaseCount(purchase);
        List<Purchase> purchasePageList = purchaseMapper.findPurchasePage(page, purchase);
                page.setTotalNum(purhaseCount);
                page.setResultList(purchasePageList);
        return page;
    }

    @Override
    public Result deletePurchaseById(Integer buyId) {
        int i = purchaseMapper.removePurchaseById(buyId);
        if(i>0){
            return Result.ok("採購單刪除成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"採購單刪除失敗");
    }

    @Override
    public Result updatePurchaseById(Purchase purchase) {
        int i = purchaseMapper.setNumById(purchase);
        if(i>0){
            return  Result.ok("採購單修改成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"採購單修改失敗");
    }
}
