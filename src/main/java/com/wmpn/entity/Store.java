package com.wmpn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Store implements Serializable {

    private Integer storeId;
    private String storeName;
    private String storeNum;
    private String storeAddress;
    private String concat;
    private String phone;

}
