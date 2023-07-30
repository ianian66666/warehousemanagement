package com.wmpn.service.impl;

import com.wmpn.dto.AssignAuthDto;
import com.wmpn.entity.Auth;
import com.wmpn.entity.Result;
import com.wmpn.entity.Role;
import com.wmpn.entity.RoleAuth;
import com.wmpn.mapper.AuthMapper;
import com.wmpn.mapper.RoleAuthMapper;
import com.wmpn.mapper.RoleMapper;
import com.wmpn.page.Page;
import com.wmpn.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//2.指定緩存中名稱（數據保存到redis中鍵的前綴,一般值給標註@CacheConfig註解的類的全路徑）
@CacheConfig(cacheNames = "com.wmpn.service.impl.RoleServiceImpl")
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleAuthMapper roleAuthMapper;
    @Autowired
    private AuthMapper authMapper;

    //3.查詢方法上標註@Cacheable指定緩存的鍵
    @Cacheable(key = "'all:role'")
    @Override
    public List<Role> queryAllRole() {
        return roleMapper.findAllRole();
    }

    @Override
    public List<Role> queryRoleByuserId(Integer userId) {
        return roleMapper.userRoleByUid(userId);
    }

    @Override
    public Page findRolePageList(Page page, Role role) {

        Integer roleRowCount = roleMapper.findRoleRowCount(role);
        List<Role> rolePageList = roleMapper.findRolePageList(role, page);
        page.setResultList(rolePageList);
        page.setTotalNum(roleRowCount);
        return page;
    }

    @CacheEvict(key = "'all:role'")
    @Override
    public Result saveRole(Role role) {
        Role roleByNameOrCode = roleMapper.findRoleByNameOrCode(role.getRoleName(), role.getRoleCode());
        if (roleByNameOrCode != null) {
            return Result.err(Result.CODE_ERR_BUSINESS, "角色已存在");
        }
        int i = roleMapper.insertRole(role);
        if (i >= 0) {
            return Result.ok("角色已添加成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "角色添加失敗");
    }

    @CacheEvict(key = "'all:role'")
    @Override
    public Result updateRoleState(Role role) {
        int i = roleMapper.setRoleStateByRid(role);
        if (1 > 0) {
            return Result.ok("角色狀態變更成功");
        } else {
            return Result.err(Result.CODE_ERR_BUSINESS, "角色狀態變更失敗");
        }
    }

    @Transactional
    @CacheEvict(key = "'all:role'")
    @Override
    public Result deleteRoleById(Integer roleId) {
        int i = roleMapper.removeRoleById(roleId);
        if (i > 0) {
            roleAuthMapper.deleteRoleAuthByRid(roleId);
            return Result.ok("刪除角色成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "刪除角色失敗");
    }

    @Override
    public List<Integer> queryAuthIdsByRid(Integer roleId) {
        return roleAuthMapper.findAuthIdsByRid(roleId);
    }
@Transactional
    @Override
    public void updateRoleAuth(AssignAuthDto assignAuthDto) {
        roleAuthMapper.deleteRoleAuthByRid(assignAuthDto.getRoleId());
        List<Integer> authIdsList = assignAuthDto.getAuthIds();
        for (Integer authId : authIdsList) {
            RoleAuth roleAuth = new RoleAuth();
            roleAuth.setAuthId(authId);
            roleAuth.setRoleId(assignAuthDto.getRoleId());
            int i = roleAuthMapper.insertRoleAuth(roleAuth);

        }

    }

    @CacheEvict(key = "'all:role'")
    @Override
    public Result setRoleDescByRid(Role role) {
        int i = roleMapper.updateRoleDescByRid(role);
        if(i>0){
            return Result.ok("角色已修改成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "角色修改失敗");
    }
}
