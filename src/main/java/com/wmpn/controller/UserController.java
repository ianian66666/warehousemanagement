package com.wmpn.controller;

import com.wmpn.dto.AssignRoleDto;
import com.wmpn.entity.*;
import com.wmpn.page.Page;
import com.wmpn.service.RoleService;
import com.wmpn.service.UserRoleService;
import com.wmpn.service.UserService;
import com.wmpn.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;


    /**
     * 分頁查詢員工
     *
     * @param page
     * @param user
     * @return
     */
    @RequestMapping("/user-list")
    public Result userList(Page page, User user) {
        Page page1 = userService.queryuserByPage(page, user);
        return Result.ok(page1);
    }

    /**
     * 增加用戶
     *
     * @param user
     * @param token
     * @return
     */
    @RequestMapping("/addUser")
    public Result saveUser(@RequestBody User user) {
        Result result = userService.insertUser(user);
        return result;
    }

    /**
     * 修改用戶的狀態
     *
     * @param user
     * @return
     */
    @PutMapping("/updateState")
    public Result updateState(@RequestBody User user) {
        Result result = userService.updateState(user);
        return result;
    }

    /**
     * 查詢用戶的所有角色
     *
     * @param userId
     * @return
     */
    @GetMapping("/user-role-list/{userId}")
    public Result userRoleList(@PathVariable Integer userId) {
        List<Role> roleByIdList = roleService.queryRoleByuserId(userId);

        return Result.ok(roleByIdList);
    }

    @PutMapping("/assignRole")
    public Result assignRole(@RequestBody AssignRoleDto assignRoleDto) {
        userService.assignRole(assignRoleDto);
        return Result.ok("添加角色成功");

    }

    /**
     * 根據用戶id刪除用戶
     *
     * @param userId
     * @return
     */
    @DeleteMapping("/deleteUser/{userId}")
    public Result deleteuserById(@PathVariable Integer userId) {

        Result result = userService.removeUserByIds(Arrays.asList(userId));
        return result;
    }

    /**
     * 批量刪除用戶操作
     *
     * @param userIds
     * @return
     */
    @DeleteMapping("/deleteUserList")
    public Result deleteuserById(@RequestBody List<Integer> userIds) {

        Result result = userService.removeUserByIds(userIds);
        return result;
    }

    /**
     * 根據用戶id修改用戶
     *
     * @param user
     * @return
     */
    @PutMapping("/updateUser")
    public Result setUserNameByUid(@RequestBody User user) {
        Result result = userService.updateUserByUid(user);
        return result;
    }

    /**
     * 重置密碼
     * @param userId
     * @return
     */
    @PutMapping("/updatePwd/{userId}")
    public Result setuserPwdByUid(@PathVariable Integer userId){
        Result result = userService.updateUserPwdbyUid(userId);
        return result;

    }



}
