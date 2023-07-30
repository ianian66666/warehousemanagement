package com.wmpn.service.impl;

import com.wmpn.entity.Place;
import com.wmpn.mapper.PlaceMapper;
import com.wmpn.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@CacheConfig(cacheNames = "com.wmpn.service.impl.PlaceServiceImpl")
@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceMapper placeMapper;


    @Cacheable(key = "'all:place'")
    @Override
    public List<Place> queryAllPlace() {
        return placeMapper.findAllPlace();
    }
}
