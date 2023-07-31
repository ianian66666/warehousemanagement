package com.wmpn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutStore {

    private Integer outsId;
    private Integer productId;
    private Integer storeId;
    private Integer tallyId;
    private BigDecimal outPrice;
    private Integer outNum;
    private Integer createBy;
    private Date createTime;
    private  String isOut;
    //---------追加字段
    private  BigDecimal salePrice; //接收前端數據

}
