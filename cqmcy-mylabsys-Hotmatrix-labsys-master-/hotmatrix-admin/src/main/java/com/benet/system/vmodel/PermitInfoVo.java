package com.benet.system.vmodel;

import java.util.List;

public class PermitInfoVo {

    /** id */
    private String id;
    private String key;
    private String title;
    private String value;

    /** 权限id */
    private String permitNo;

    /** 权限名称 */
    private String permitName;

    /** 父权限id */
    private String parentNo;

    /** 图标 */
    private String menuIcon;

    /** 权限标识 */
    private String permitCode;

    /** 组件路径 */
    private String component;

    /** 显示顺序 */
    private Integer orderNo;

    /** 备注 */
    private String comments;

    private List<PermitInfoVo> children;

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

    public String getPermitNo() {
        return permitNo;
    }

    public void setPermitNo(String permitNo) {
        this.permitNo = permitNo;
    }

    public String getPermitName() {
        return permitName;
    }

    public void setPermitName(String permitName) {
        this.permitName = permitName;
    }

    public String getParentNo() {
        return parentNo;
    }

    public void setParentNo(String parentNo) {
        this.parentNo = parentNo;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getPermitCode() {
        return permitCode;
    }

    public void setPermitCode(String permitCode) {
        this.permitCode = permitCode;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<PermitInfoVo> getChildren() {
        return children;
    }

    public void setChildren(List<PermitInfoVo> children) {
        this.children = children;
    }
}
