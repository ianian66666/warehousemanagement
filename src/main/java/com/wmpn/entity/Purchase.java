package com.wmpn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * buyList表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    private Integer buyId;
    private Integer productId;
    private Integer storeId;
    private Integer buyNum;
    private Integer factBuyNum;
    @JsonFormat(pattern =  "yyyy-MM-dd HH:mm:ss")
    private Date buyTime;
    private Integer supplyId;
    private Integer placeId;
    private String buyUser;
    private String phone;
    private Integer isIn;

    //------追加屬性
    private String startTime;
    private String endTime;
    private String storeName;
    private String productName;


}
