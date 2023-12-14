package com.benet.system.vmodel;

import lombok.Data;

import java.util.List;

@Data
public class ItemObjectVo {
    private String id;
    private String key;
    private String title;
    private String value;

    private List<ItemObjectVo> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<ItemObjectVo> getChildren() {
        return children;
    }

    public void setChildren(List<ItemObjectVo> children) {
        this.children = children;
    }
}
