package com.wmpn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductType implements Serializable {
    private  Integer typeId;
    private  Integer parentId;
    private String typeCode;
    private String typeName;
    private String typeDesc;
//---------追加屬性--------------
    private List<ProductType> childProductCategory;


}
