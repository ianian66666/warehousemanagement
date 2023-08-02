package com.wmpn.mapper;

import com.wmpn.annotation.AutoFill;
import com.wmpn.entity.User;
import com.wmpn.enumeration.OperationType;
import com.wmpn.page.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * table user_info
 */
@Mapper
public interface UserMapper {


    User findUserByCode(String userCode);



    //查詢總用戶
    public Integer findRowCount(User user);

    //分頁查詢用戶
    List<User> findUserByPage(@Param("page") Page page,@Param("user") User user);

    /**
     * 添加用戶
     * @param user
     * @return
     */
    @AutoFill(value = OperationType.INSERT)
    int insertUser(User user);

    /**
     * 更改用戶狀態
     * @param user
     * @return
     */
    @AutoFill(value = OperationType.UPDATE)
    @Update("update warehouse.user_info set user_state =#{userState},update_by=#{updateBy} ,update_time = #{updateTime}  where user_id =#{userId} ")
    int updateState(User user);

    /**
     * 刪除用戶
     * @param userIds
     * @return
     */
    int deleteUserList(List<Integer> userIds);

    /**
     * 根據用戶id修改用戶
     * @param user
     * @return
     */
    @AutoFill(value = OperationType.UPDATE)
    @Update("update warehouse.user_info set user_name=#{userName},update_by=#{updateBy},update_time=#{updateTime}  where user_id=#{userId}")
    int updateUserByUid(User user);

    /**
     * 根據用戶id重置密碼
     * @param id
     * @param password
     * @return
     */
    @Update("update warehouse.user_info set user_pwd=#{password} ,update_by=   where  user_id=#{id}")
    int updateUserPwdbyUid(Integer id,String password);
}
