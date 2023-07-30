package com.wmpn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {

    private Integer serRoleId;
    private Integer roleId;
    private Integer userId;
}
