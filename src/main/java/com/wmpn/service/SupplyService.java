package com.wmpn.service;

import com.wmpn.entity.Supply;

import java.util.List;

public interface SupplyService {

    /**
     * 查詢所有的供應商
     * @return
     */
    List<Supply> queryAllSupply();
}
