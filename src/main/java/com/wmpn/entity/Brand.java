package com.wmpn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand implements Serializable {
    private Integer brandId;
    private String brandName;
    private String brandLeter;
    private String brandDesc;


}
