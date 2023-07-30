package com.wmpn.service;

import com.wmpn.entity.Unit;

import java.util.List;

public interface UnitService {

    /**
     * 查詢所有的單位
     * @return
     */
    List<Unit> queryAllUnit();
}
