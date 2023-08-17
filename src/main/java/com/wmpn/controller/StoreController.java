package com.wmpn.controller;

import com.wmpn.entity.Result;
import com.wmpn.entity.Store;
import com.wmpn.page.Page;
import com.wmpn.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    /**
     * 分頁查詢倉庫列表
     *
     * @param store
     * @param page
     * @return
     */
    @GetMapping("/store-page-list")
    public Result storePage(Store store, Page page) {
        page = storeService.queryStoreByPage(store, page);
        return Result.ok(page);
    }

    /**
     * 添加倉庫時確認編號是否重複
     *
     * @param storeNum
     * @return
     */
    @GetMapping("/store-num-check")
    public Result checkStoreNum(String storeNum) {
        Result result = storeService.checkStoreNum(storeNum);
        return result;
    }

    /**
     * 添加倉庫
     * @param store
     * @return
     */
    @PostMapping("/store-add")
    public Result addStore(@RequestBody Store store){
        Result result = storeService.addStore(store);
        return result;
    }

    /**
     * 刪除倉庫
     * @param id
     * @return
     */
    @DeleteMapping("/store-delete/{id}")
    public Result removeStore(@PathVariable Integer id){
            Result result = storeService.removeStoreById(id);
            return  result;
    }

    /**
     * 修改倉庫訊息
     * @param store
     * @return
     */
    @PutMapping("/store-update")
    public Result  setStoreById(@RequestBody Store store){
        Result result = storeService.updateStoreById(store);
        return  result;
    }

}
