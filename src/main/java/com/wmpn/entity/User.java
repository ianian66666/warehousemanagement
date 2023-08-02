package com.wmpn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * user_info表的實體類:
 */
@Data
public class User {

	private int userId;

	private String userCode;

	private String userName;

	private String userPwd;

	private String userType;

	private String userState;

	private String isDelete;

	private Integer createBy;

	//返回前端时,自動將Date轉换成指定格式的json字符串
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	private Integer updateBy;

	private LocalDateTime updateTime;


	//代表用戶的創建人
	private String getCode;

	public User() {

	}

	public User(int userId, String userCode, String userName, String userPwd,
			String userType, String userState, String isDelete, Integer createBy,
				LocalDateTime createTime, Integer updateBy, LocalDateTime updateTime) {
		this.userId = userId;
		this.userCode = userCode;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userType = userType;
		this.userState = userState;
		this.isDelete = isDelete;
		this.createBy = createBy;
		this.createTime = createTime;
		this.updateBy = updateBy;
		this.updateTime = updateTime;
	}
}
