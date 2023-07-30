package com.wmpn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supply implements Serializable {
private Integer supplyId;
private String supplyNum;
private String supplyName;
private String supplyIntroduce;
private String concat;
private String phone;
private String address;
private String isDelete;


}
