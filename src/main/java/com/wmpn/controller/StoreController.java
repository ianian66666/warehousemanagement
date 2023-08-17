package com.wmpn.controller;

import com.wmpn.entity.Result;
import com.wmpn.entity.Store;
import com.wmpn.page.Page;
import com.wmpn.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping("/store-page-list")
    public Result storePage(Store store, Page page) {
        page = storeService.queryStoreByPage(store, page);
        return Result.ok(page);
    }

}
