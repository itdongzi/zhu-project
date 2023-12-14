package com.benet.web.vmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuserInfoVo {

    private String userNo;
    private String userName;
    private String nickname;
    private String userType;
    private String sex;
    private String orgzName;
    private String postName;
    private String email;
    private String phone;
    private String avatar;
    private String loginDate;
    private String resume;
}
