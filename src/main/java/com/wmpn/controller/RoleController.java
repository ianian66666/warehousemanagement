package com.wmpn.controller;


import com.wmpn.dto.AssignAuthDto;
import com.wmpn.entity.*;
import com.wmpn.page.Page;
import com.wmpn.service.RoleService;
import com.wmpn.service.UserRoleService;
import com.wmpn.utils.TokenUtils;
import com.wmpn.utils.WarehouseConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private TokenUtils tokenUtils;

    /**
     * 查詢所有角色
     *
     * @return
     */
    @GetMapping("/role-list")
    public Result roleList() {
        log.info("查詢角色");
        List<Role> roleList = roleService.queryAllRole();

        return Result.ok(roleList);

    }

    /**
     * 查詢角色分頁
     *
     * @param role
     * @param page
     * @return
     */
    @GetMapping("role-page-list")
    public Result roleListPage(Role role, Page page) {

        page = roleService.findRolePageList(page, role);
        return Result.ok(page);
    }

    @PostMapping("/role-add")
    public Result inertRole(@RequestBody Role role) {
        Result result = roleService.saveRole(role);
        return result;

    }

    @PutMapping("/role-state-update")
    public Result updateRoleState(@RequestBody Role role ) {
        Result result = roleService.updateRoleState(role);
        return result;
    }

    @DeleteMapping("/role-delete/{roleId}")
    public Result deleteRoleByRid(@PathVariable Integer roleId) {
        Result result = roleService.deleteRoleById(roleId);

        return result;
    }

    /**
     * 根據角色id查詢角色所分配的權限id
     *
     * @param roleId
     * @return
     */

    @GetMapping("/role-auth")
    public Result getAuthByRid(Integer roleId) {
        List<Integer> authList = roleService.queryAuthIdsByRid(roleId);
        return Result.ok(authList);
    }

    /**
     * 修改角色的權限
     *
     * @param assignAuthDto
     * @return
     */
    @PutMapping("/auth-grant")
    public Result setAuthAuth(@RequestBody AssignAuthDto assignAuthDto) {
        roleService.updateRoleAuth(assignAuthDto);
        return Result.ok("權限已修改成功");
    }

    @PutMapping("/role-update")
    public Result updateRoleDesc(@RequestBody Role role ) {
        Result result = roleService.setRoleDescByRid(role);
        return result;
    }


}
