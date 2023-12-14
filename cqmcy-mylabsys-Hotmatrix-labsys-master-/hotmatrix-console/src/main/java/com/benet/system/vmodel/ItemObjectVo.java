package com.benet.system.vmodel;

import lombok.Data;

import java.util.List;

@Data
public class ItemObjectVo {
    private String label;
    private String value;

    private List<ItemObjectVo> children;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
