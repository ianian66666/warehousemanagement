package com.wmpn.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 修改權限時需接收數據的dto
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignAuthDto {
    private Integer roleId;
    private List<Integer> authIds;
}
