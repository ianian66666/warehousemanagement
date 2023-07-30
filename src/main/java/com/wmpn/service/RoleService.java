package com.wmpn.service;

import com.wmpn.dto.AssignAuthDto;
import com.wmpn.entity.Auth;
import com.wmpn.entity.Result;
import com.wmpn.entity.Role;
import com.wmpn.page.Page;

import java.util.List;

public interface RoleService {
    /**
     * 查詢所有角色功能
     * @return
     */
    List<Role> queryAllRole();

    /**
     * 根據用戶id查詢其角色
     * @param userId
     * @return
     */

     List<Role> queryRoleByuserId(Integer userId);

    /**
     * 查詢角色並分頁
     * @param page
     * @param role
     * @return
     */
    Page findRolePageList(Page page ,Role role);

    /**
     * 添加角色
     * @param role
     * @return
     */
    Result saveRole(Role role);

    /**
     * 啟用或禁用角色
     * @param role
     * @return
     */
    Result updateRoleState(Role role);

    /**
     * 刪除角色
     * @param roleId
     * @return
     */
    Result deleteRoleById(Integer roleId);

    /**
     * 根據角色id查詢角色權限id
     * @param roleId
     * @return
     */
    List<Integer> queryAuthIdsByRid(Integer roleId);

    /**
     * 修改角色的權限
     * @param assignAuthDto
     */
    void updateRoleAuth(AssignAuthDto assignAuthDto);

    /**
     * 根據roleID修改角色資料
     * @param role
     * @return
     */
    Result setRoleDescByRid(Role role);
}
