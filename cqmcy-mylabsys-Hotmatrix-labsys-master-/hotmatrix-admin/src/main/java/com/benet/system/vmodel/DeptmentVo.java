package com.benet.system.vmodel;

import java.util.List;

public class DeptmentVo {

    /** id */
    private String id;
    private String key;
    private String title;
    private String value;

    /** 部门id */
    private String deptNo;

    /** 部门名称 */
    private String deptName;

    /** 父部门id */
    private String parentNo;

    /** 显示顺序 */
    private Integer orderNo;

    /** 负责人 */
    private String leader;

    /** 联系电话 */
    private String telephone;

    /** 邮箱 */
    private String email;

    /** 备注 */
    private String comments;

    private List<DeptmentVo> children;

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

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<DeptmentVo> getChildren() {
        return children;
    }

    public void setChildren(List<DeptmentVo> children) {
        this.children = children;
    }
}
