package com.wmpn.mapper;

import java.util.List;
import com.wmpn.entity.Auth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AuthMapper {


    public List<Auth> findAuthByUid(Integer userId);

    /**
     * 查出所有權限菜單
     */
    @Select("select * from warehouse.auth_info where  auth_state =1 ")
    List<Auth> findAllAuthTree();


}
