package com.wmpn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InStore {

    private Integer insId;
    private Integer storeId;
    private Integer productId;
    private Integer inNum;
    private Integer createBy;
    private Date createTime;
    private Integer isIn;
}


