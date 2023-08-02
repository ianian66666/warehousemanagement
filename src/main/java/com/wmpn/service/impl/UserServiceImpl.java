package com.wmpn.service.impl;

import com.wmpn.dto.AssignRoleDto;
import com.wmpn.entity.Result;
import com.wmpn.entity.User;
import com.wmpn.entity.UserRole;
import com.wmpn.mapper.RoleMapper;
import com.wmpn.mapper.UserMapper;
import com.wmpn.mapper.UserRoleMapper;
import com.wmpn.page.Page;
import com.wmpn.service.RoleService;
import com.wmpn.service.UserRoleService;
import com.wmpn.service.UserService;
import com.wmpn.utils.DigestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public User queryUserByCode(String userCode) {
        return userMapper.findUserByCode(userCode);
    }

    @Override
    public Page queryuserByPage(Page page, User user) {
        List<User> userByPage = userMapper.findUserByPage(page, user);
        Integer count = userMapper.findRowCount(user);
        page.setTotalNum(count);
        page.setResultList(userByPage);
        return page;
    }

    @Override
    public Result insertUser(User user) {
        User userFlag = userMapper.findUserByCode(user.getUserCode());
        if(userFlag != null) return Result.err(Result.CODE_ERR_BUSINESS,"帳號已存在！");
        String pwd = DigestUtil.hmacSign(user.getUserPwd());
        user.setUserPwd(pwd);
        int flag =  userMapper.insertUser(user);
        if(flag>0){
            return Result.ok("用戶添加成功");
        }else {
            return Result.err(Result.CODE_ERR_BUSINESS,"用戶添加失敗");
        }
    }

    @Override
    public Result  updateState(User user) {
        int flag = userMapper.updateState(user);
        if(flag>0){
            return  Result.ok("用戶禁用成功");
        }else{
            return  Result.err(Result.CODE_ERR_BUSINESS,"狀態修改失敗");
        }
    }

    @Transactional
    @Override
    public void assignRole(AssignRoleDto assignRoleDto) {
       userRoleMapper.removeUserRoleByUid(assignRoleDto.getUserId());

        List<String> roleCheckList = assignRoleDto.getRoleCheckList();
        for (String roleName : roleCheckList) {
            Integer roleId = roleMapper.findRoleIdByName(roleName);
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(assignRoleDto.getUserId());
            userRoleMapper.insertUserRole(userRole);
        }
    }

    @Override
    public Result removeUserByIds(List<Integer> userIds) {
        int i = userMapper.deleteUserList(userIds);
        if(i>0){
            return Result.ok("用戶已成功刪除");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"用戶刪除失敗");
    }

    @Override
    public Result updateUserByUid(User user) {
        int i = userMapper.updateUserByUid(user);
        if(i>0){
            return Result.ok("用戶修改成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"用戶修改失敗");
    }

    @Override
    public Result updateUserPwdbyUid(Integer userId) {
        String pwd = DigestUtil.hmacSign("123456");
        int i = userMapper.updateUserPwdbyUid(userId, pwd);
        if(i>0){
            return Result.ok("用戶密碼修改成功");
        }
        return  Result.err(Result.CODE_ERR_BUSINESS,"用戶密碼修改失敗");
    }
}
