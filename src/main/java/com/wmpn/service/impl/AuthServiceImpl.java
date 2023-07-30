package com.wmpn.service.impl;

import com.alibaba.fastjson.JSON;
import com.wmpn.entity.Auth;
import com.wmpn.mapper.AuthMapper;
import com.wmpn.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@CacheConfig(cacheNames = "com.wmpn.service.impl.AuthServiceImpl")
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<Auth> authTreeByUid(Integer userId) {

        //先從redis中查詢緩存的用戶菜單樹
        String authTreeJson = redisTemplate.opsForValue().get("authTree:" + userId);
        if (StringUtils.hasText(authTreeJson))//說明redis有菜單樹的緩存
        {
            List<Auth> authTreeList = JSON.parseArray(authTreeJson, Auth.class);
            return authTreeList;
        }
        //redis中無菜單緩存的情況
        List<Auth> allauthList = authMapper.findAuthByUid(userId);
        //將所有菜單list<Auth>轉成菜單樹List<Auth>
        List<Auth> authTreeList = allAuthToAuthTree(allauthList, 0);
        //再將菜單樹存入redis
        redisTemplate.opsForValue().set("authTree:" + userId, JSON.toJSONString(authTreeList));


        return authTreeList;
    }

    @Cacheable(key = "'all:authtree'")
    @Override
    public List<Auth> queryAllAuthTree() {
        List<Auth> allAuthList = authMapper.findAllAuthTree();
        List<Auth> authListTree = allAuthToAuthTree(allAuthList, 0);

        return authListTree;
    }

    private List<Auth> allAuthToAuthTree(List<Auth> allAuthList, Integer pid) {

        List<Auth> firstLevelAuth = new ArrayList<>();
        for (Auth auth : allAuthList) {
            if (auth.getParentId().equals(pid)) {
                firstLevelAuth.add(auth);
            }

        }
        for (Auth auth : firstLevelAuth) {
            List<Auth> secondLevelAuth = allAuthToAuthTree(allAuthList, auth.getAuthId());
            auth.setChildAuth(secondLevelAuth);
        }
        return firstLevelAuth;
    }
}
