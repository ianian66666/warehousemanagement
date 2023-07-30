package com.wmpn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Place  implements Serializable {
    private Integer placeId;
    private String placeName;
    private String placeNum;
    private String introduce;
    private String isDelete;
}
