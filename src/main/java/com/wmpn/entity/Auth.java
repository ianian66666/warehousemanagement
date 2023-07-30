package com.wmpn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

/**
 * table_info
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auth implements Serializable {

    private Integer authId;
    private Integer parentId;
    private String authName;
    private String authDesc;
    private Integer authGrade;
    private String authType;
    private String authUrl;
    private String authOrder;
    private String authState;
    private Integer createBy;
    private Date createTime;
    private Integer updateBy;
    private  Date updateTime;

    private List<Auth> childAuth;





}
