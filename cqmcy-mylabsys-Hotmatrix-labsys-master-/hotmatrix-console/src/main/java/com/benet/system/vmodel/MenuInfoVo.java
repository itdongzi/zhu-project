package com.benet.system.vmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuInfoVo {

    private String mid;
    private String name;
    private String code;
    private String path;
    private boolean hidden;
    private String redirect;
    private boolean alwaysShow;
    private String component;
    private MenuMeta meta;
    private String parentId;
    private List<MenuInfoVo> children;
}
