package com.wmpn.controller;

import com.wmpn.entity.CurrentUser;
import com.wmpn.entity.OutStore;
import com.wmpn.entity.Result;
import com.wmpn.service.OutStoreService;
import com.wmpn.utils.TokenUtils;
import com.wmpn.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/outstore")
public class OutStoreController {
    @Autowired
    private OutStoreService outStoreService;
    @Autowired
    private TokenUtils tokenUtils;

    /**
     * 添加出庫單表
     *
     * @param outStore
     * @param token
     * @return
     */

    @PostMapping("/outstore-add")
    public Result addOutStore(@RequestBody OutStore outStore, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        outStore.setCreateBy(currentUser.getUserId());
        Result result = outStoreService.saveOutStore(outStore);
        return result;

    }


}
