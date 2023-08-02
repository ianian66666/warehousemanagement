package com.wmpn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer productId;
    private Integer storeId;
    private Integer brandId;
    private String productName;
    private String productNum;
    private String productInvent;
    private Integer typeId;
    private Integer supplyId;
    private Integer placeId;
    private Integer unitId;
    private String introduce;
    private String upDownState;
    private Double inPrice;
    private Double salePrice;
    private Double memPrice;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer createBy;
    private Integer updateBy;
    private String imgs;
    @JsonFormat(pattern =  "yyyy-MM-dd")
    private Date productDate;
    @JsonFormat(pattern =  "yyyy-MM-dd")
    private Date suppDate;

//    ----追加屬性
    private String brandName;//品牌名稱
    private String supplyName;//供應商名稱
    private String placeName;//產地名
    private String typeName;//分類名稱
    private Integer isOverDate;//是否過期
    private String storeName;//倉庫名稱
    private String unitName;//單位


}

