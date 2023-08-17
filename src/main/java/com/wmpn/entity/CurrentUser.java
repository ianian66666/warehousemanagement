package com.wmpn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUser {

    private int userId;//用户id

    private String userCode;//用户名

    private String userName;//真實姓名
}
