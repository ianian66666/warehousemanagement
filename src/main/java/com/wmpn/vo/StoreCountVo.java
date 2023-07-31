package com.wmpn.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreCountVo {
    private Integer storeId;
    private String storeName;
    private Integer totalInvent;//一個倉庫的總數量

}
