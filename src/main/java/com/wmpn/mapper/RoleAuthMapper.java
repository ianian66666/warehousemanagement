package com.wmpn.mapper;

import com.wmpn.entity.Auth;
import com.wmpn.entity.RoleAuth;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleAuthMapper {


    /**
     * 根據角色id刪除其角色相關權限
     * @param roleId
     * @return
     */
    @Delete("delete from warehouse.role_auth where role_id =#{roleId} ")
    int deleteRoleAuthByRid(Integer roleId);

    /**
     * 根據角色id查詢分配權限
     * @param RoleId
     * @return
     */
    @Select("select auth_id from  warehouse.role_auth  where role_id=#{RoleId} ")
    List<Integer> findAuthIdsByRid(Integer RoleId);

    /**
     * 添加角色的權限
     * @param roleAuth
     * @return
     */
    @Insert("insert into warehouse.role_auth(role_id, auth_id) VALUES (#{roleId} ,#{authId} )")
    int insertRoleAuth(RoleAuth roleAuth);
}
