package com.wmpn.controller;

import com.wmpn.entity.Result;
import com.wmpn.service.StoreService;
import com.wmpn.vo.StoreCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/statistics")
@RestController
public class StatisticsController {
    @Autowired
    private StoreService storeService;


    /**
     * 查詢一個供應商的商品量
     * @return
     */
    @RequestMapping("/store-invent")
    public Result storeInvent(){
        List<StoreCountVo> storeCountVos = storeService.queryStoreCount();
        return Result.ok(storeCountVos);
    }
}
