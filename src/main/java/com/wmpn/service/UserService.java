package com.wmpn.service;

import com.wmpn.dto.AssignRoleDto;
import com.wmpn.entity.Result;
import com.wmpn.entity.User;
import com.wmpn.page.Page;
import java.util.List;

public interface UserService {

    /**
     * 登入時根據用戶usercode查詢用戶是否存在
     * @param userCode
     * @return
     */
    public User queryUserByCode(String userCode);

    /**
     * 用戶列表分頁查詢
     * @param page
     * @param user
     * @return
     */
    public Page queryuserByPage(Page page ,User user);

    /**
     * 增加用戶功能
     *
     * @param user
     * @return
     */
    Result inserUser(User user);

    /**
     * 用戶的狀態修改
     * @param user
     * @return
     */
    Result updateState(User user);

    /**
     * 添加用戶新所分配到的角色
     * @param assignRoleDto
     * @return
     */
    void assignRole(AssignRoleDto assignRoleDto);

    Result removeUserByIds(List<Integer> userIds);

    Result updateUserByUid(User user);

    /**
     * 根據用戶id修改密碼
     * @param userId
     * @return
     */
    Result updateUserPwdbyUid(Integer userId);
}
