package com.wmpn.controller;

import com.wmpn.annotation.Log;
import com.wmpn.entity.InStore;
import com.wmpn.entity.Result;
import com.wmpn.entity.Store;
import com.wmpn.page.Page;
import com.wmpn.service.InStoreService;
import com.wmpn.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instore")
public class InStoreController {
    @Autowired
    private InStoreService inStoreService;
    @Autowired
    private StoreService storeService;

    /**
     * 查詢倉庫列表
     * @return
     */
    @GetMapping("/store-list")
    public Result storeList(){
        List<Store> allStore = storeService.findAllStore();
        return Result.ok(allStore);
    }
    /**
     * 分頁查詢入庫表
     */
    @GetMapping("/instore-page-list")
    public Result inStoreListPage(Page page, InStore inStore){
         page = inStoreService.queryInStorePage(page, inStore);
         return Result.ok(page);
    }
    /**
     * 確認入庫
     */
    @Log
    @PutMapping("/instore-confirm")
    public Result confirmInStore(@RequestBody InStore inStore){
        Result result = inStoreService.inStoreConfirm(inStore);
        return  result;
    }
}
