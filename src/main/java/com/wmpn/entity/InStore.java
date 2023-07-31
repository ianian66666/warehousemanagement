package com.wmpn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
    @JsonFormat(pattern =  "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private Integer isIn;

    //--------追加屬性

    private String productName;
    private String startTime;
    private String endTime;
    private String storeName;
    private BigDecimal inPrice;
    private String userCode;
}


