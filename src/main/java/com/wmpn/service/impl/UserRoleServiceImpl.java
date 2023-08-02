package com.wmpn.service.impl;

import com.wmpn.entity.Role;
import com.wmpn.entity.UserRole;
import com.wmpn.mapper.UserRoleMapper;
import com.wmpn.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;


}
