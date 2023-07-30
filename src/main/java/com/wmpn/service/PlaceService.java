package com.wmpn.service;

import com.wmpn.entity.Place;

import java.util.List;

public interface PlaceService {

    /**
     * 查詢所有的產地
     * @return
     */
    List<Place> queryAllPlace();
}
