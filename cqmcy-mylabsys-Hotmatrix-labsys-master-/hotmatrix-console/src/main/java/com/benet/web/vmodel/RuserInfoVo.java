package com.benet.web.vmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuserInfoVo {

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
    private List<String> roles;
    private List<String> permissions;
}
