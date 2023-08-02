package com.wmpn.controller;

import com.wmpn.annotation.Log;
import com.wmpn.entity.*;
import com.wmpn.page.Page;
import com.wmpn.service.InStoreService;
import com.wmpn.service.PurchaseService;
import com.wmpn.service.StoreService;
import com.wmpn.utils.TokenUtils;
import com.wmpn.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private InStoreService inStoreService;
    @Autowired
    private TokenUtils tokenUtils;

    @Log
    @PostMapping("/purchase-add")
    public Result addPurchase(@RequestBody Purchase purchase){

        Result result = purchaseService.savePurchase(purchase);
        return  result;

    }

    /**
     * 查詢所有倉庫
     * @return
     */
    @GetMapping("/store-list")
    public Result getStoreAll(){
        List<Store> allStore = storeService.findAllStore();
        return  Result.ok(allStore);
    }
    @GetMapping("/purchase-page-list")
    public Result purchaseListPage(Page page,Purchase purchase){
         page = purchaseService.queryPurchasePage(page, purchase);
         return  Result.ok(page);
    }
    @Log
    @DeleteMapping("/purchase-delete/{buyId}")
    public Result deletePurchase(@PathVariable Integer buyId){
        Result result = purchaseService.deletePurchaseById(buyId);
        return result;
    }
    @Log
    @PutMapping("/purchase-update")
    public Result setPurchase(@RequestBody Purchase purchase){
        Result result = purchaseService.updatePurchaseById(purchase);
        return result;
    }

    /**
     * 生成入庫單
     * @param purchase
     * @return
     */
    @Log
    @PostMapping("/in-warehouse-record-add")
    public Result addInStore(@RequestBody Purchase purchase, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int createBy = currentUser.getUserId();
        InStore inStore = new InStore();
        inStore.setCreateBy(createBy);
        inStore.setStoreId(purchase.getStoreId());
        inStore.setProductId(purchase.getProductId());
        inStore.setInNum(purchase.getFactBuyNum());

        Result result = inStoreService.saveInStore(inStore, purchase.getBuyId());
        return result;
    }

}
