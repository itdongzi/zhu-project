package com.benet.system.vmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PswdModelVo {
    private String userNo;
    private String oldPsword;
    private String newPsword;
    private String repPsword;
}
