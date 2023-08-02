package com.wmpn.controller;

import com.wmpn.annotation.Log;
import com.wmpn.entity.CurrentUser;
import com.wmpn.entity.OutStore;
import com.wmpn.entity.Result;
import com.wmpn.entity.Store;
import com.wmpn.page.Page;
import com.wmpn.service.OutStoreService;
import com.wmpn.service.StoreService;
import com.wmpn.utils.TokenUtils;
import com.wmpn.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/outstore")
public class OutStoreController {
    @Autowired
    private OutStoreService outStoreService;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private StoreService storeService;

    /**
     * 添加出庫單表
     *
     * @param outStore
     * @param token
     * @return
     */
    @Log
    @PostMapping("/outstore-add")
    public Result addOutStore(@RequestBody OutStore outStore, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        outStore.setCreateBy(currentUser.getUserId());
        Result result = outStoreService.saveOutStore(outStore);
        return result;

    }

    @GetMapping("/store-list")
    public Result storeList() {
        List<Store> allStore = storeService.findAllStore();
        return Result.ok(allStore);
    }

    /**
     * 分頁查詢出庫表
     *
     * @param page
     * @param outStore
     * @return
     */
    @GetMapping("/outstore-page-list")
    public Result outStoreListPage(Page page, OutStore outStore) {
        page = outStoreService.queryOutStorePage(page, outStore);
        return Result.ok(page);

    }
    @Log
    @PutMapping("/outstore-confirm")
    public Result confirmOutStore(@RequestBody OutStore outStore){
        Result result = outStoreService.outStoreConfirm(outStore);
        return result;
    }

}
