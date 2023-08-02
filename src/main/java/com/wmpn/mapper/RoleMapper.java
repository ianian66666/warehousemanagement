package com.wmpn.mapper;

import com.wmpn.annotation.AutoFill;
import com.wmpn.entity.Role;
import com.wmpn.enumeration.OperationType;
import com.wmpn.page.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper {

    @Select("select *from warehouse.role where role_state = 1 ")
    List<Role> findAllRole();

    //根據用戶id查詢用戶分配的所有角色
    @Select("select t1.* from warehouse.role t1 ,warehouse.user_role  t2 where  t1.role_id =t2.role_id and  t2.user_id=#{userId} and t1.role_state = 1")
    List<Role> userRoleByUid(Integer userId);

    //根據角色名查詢角色id
//    @Select("select * from warehouse.role where role_name like concat('%',#{roleName},'%'); ")
    @Select("select * from warehouse.role where role_name = #{roleName}")
    Integer findRoleIdByName(String roleName);

    //查詢角色幾筆
    Integer findRoleRowCount(Role role);

    //分頁查詢角色
    List<Role> findRolePageList(@Param("role") Role role, @Param("page") Page page);

    /**
     * 根據角色名稱及角色方法查詢是否有一樣的
     *
     * @param roleName
     * @param roleCode
     * @return
     */
    @Select("select *from warehouse.role where role_name =#{roleName} or role_code = #{roleCode}")
    Role findRoleByNameOrCode(String roleName, String roleCode);

    @AutoFill(OperationType.INSERT)
    int insertRole(Role role);

    /**
     * 根據角色id修改角色狀態
     *
     * @param role
     * @return
     */
    @AutoFill(OperationType.UPDATE)
    @Update("update warehouse.role set role_state=#{roleState},update_by=#{updateBy},update_time=#{updateTime}  where role_id = #{roleId} ")
    int setRoleStateByRid(Role role);


    @Delete("delete from warehouse.role where role_id =#{roleId} ")
    int removeRoleById(Integer roleId);

    /**
     * 根據roleId修改roleDesc
     *
     * @param role
     * @return
     */
    @AutoFill(OperationType.UPDATE)
    @Update("update warehouse.role set role_desc =#{roleDesc},update_by =#{updateBy} ,update_time= #{updateTime}   where role_id =#{roleId}  ")
    int updateRoleDescByRid(Role role);

}
