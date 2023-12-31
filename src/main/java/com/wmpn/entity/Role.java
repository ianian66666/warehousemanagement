package com.wmpn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role  implements Serializable {

    private Integer roleId;
    private String roleName;
    private String roleDesc;
    private String roleCode;
    private String roleState;
    private Integer createBy;
    @JsonFormat(pattern =  "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    private Integer updateBy;
    private LocalDateTime updateTime;
    private String getCode;


    private static final long serialVersionUID = -1475211948207318914L;
}
