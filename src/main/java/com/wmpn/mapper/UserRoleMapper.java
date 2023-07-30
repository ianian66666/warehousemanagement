package com.wmpn.mapper;

import com.wmpn.entity.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserRoleMapper {

    //根據用戶id刪除用戶所有角色

    @Delete("delete  from  warehouse.user_role where user_id =#{userId}")
    int removeUserRoleByUid(Integer userId);

    //添加用戶新所分配到的角色

    @Insert("insert into warehouse.user_role(role_id, user_id) VALUES (#{roleId},#{userId})")
    int insertUserRole(UserRole userRole);


}
