package com.benet.system.vmodel;

import java.util.List;

public class RolePermitVo {

    private String key;
    private String title;
    private String desc;
    private List<RolePermitVo> children;

    public RolePermitVo() {
    }

    public RolePermitVo(String key, String title, String desc, List<RolePermitVo> children) {
        this.key = key;
        this.title = title;
        this.desc = desc;
        this.children = children;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<RolePermitVo> getChildren() {
        return children;
    }

    public void setChildren(List<RolePermitVo> children) {
        this.children = children;
    }
}
