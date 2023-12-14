package com.benet.common.core.domain;

import java.io.Serializable;
import java.util.List;

public class TreeModel implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String label;
    private String value;
    private String parentId;
    private boolean isLeaf;
    private boolean disabled;
    private List<TreeModel> children;

    public TreeModel() {
    }

    public TreeModel(String value, String label, String parentId) {
        this.label = label;
        this.value = value;
        this.parentId = parentId;
        this.isLeaf=false;
        this.disabled=false;
        this.children=null;
    }

    public TreeModel(String value, String label, String parentId, boolean isLeaf, boolean disabled) {
        this.label = label;
        this.value = value;
        this.parentId = parentId;
        this.isLeaf=isLeaf;
        this.disabled=disabled;
        this.children=null;
    }

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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public List<TreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<TreeModel> children) {
        this.children = children;
    }
}
