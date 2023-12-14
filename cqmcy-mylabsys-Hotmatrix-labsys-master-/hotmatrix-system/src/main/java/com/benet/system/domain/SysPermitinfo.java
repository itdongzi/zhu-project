package com.benet.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限对象 sys_permitinfo
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public class SysPermitinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 菜单ID */
    @Excel(name = "菜单ID")
    private String permitNo;

    /** 菜单名称 */
    @Excel(name = "菜单名称")
    private String permitName;

    /** 权限标识 */
    @Excel(name = "权限标识")
    private String permitCode;

    /** 菜单类型（M目录 C菜单 F按钮） */
    @Excel(name = "菜单类型", readConverterExp = "M=目录,C=菜单,F=按钮")
    private String permitType;

    /** 父菜单ID */
    @Excel(name = "父菜单ID")
    private String parentNo;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer orderNo;

    /** 链接类型（0内部链接 1外部链接） */
    @Excel(name = "链接类型", readConverterExp = "0内部链接,1外部链接")
    private String linkType;

    /** 菜单图标 */
    @Excel(name = "菜单图标")
    private String menuIcon;

    /** 请求地址 */
    @Excel(name = "请求地址")
    private String pathUrl;

    /** 组件路径 */
    @Excel(name = "组件路径")
    private String component;

    /** 重定向地址 */
    @Excel(name = "重定向地址")
    private String redirect;

    /** 打开方式（menuItem页签 menuBlank新窗口） */
    @Excel(name = "打开方式", readConverterExp = "m=enuItem页签,m=enuBlank新窗口")
    private String target;

    /** 显示状态（0显示 1隐藏） */
    @Excel(name = "显示状态", readConverterExp = "0=显示,1=隐藏")
    private String visible;

    /** 状态（1正常 0停用） */
    @Excel(name = "状态", readConverterExp = "1=正常,0=停用")
    private String checkState;

    /** 分支编号 */
    @Excel(name = "分支编号")
    private String branchNo;

    /** 删除标志（1代表存在 0代表删除） */
    @Excel(name = "删除标志", readConverterExp = "1=代表存在,0=代表删除")
    private String deleteFlag;

    /** 更新者 */
    @Excel(name = "更新者")
    private String comments;

    /** 应用编码 */
    @Excel(name = "应用编码")
    private String appCode;

    /** 版本号 */
    @Excel(name = "版本号")
    private Long version;

    /** 子菜单 */
    private List<SysPermitinfo> children = new ArrayList<SysPermitinfo>();

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPermitNo(String permitNo) 
    {
        this.permitNo = permitNo;
    }

    public String getPermitNo() 
    {
        return permitNo;
    }
    public void setPermitName(String permitName) 
    {
        this.permitName = permitName;
    }

    public String getPermitName() 
    {
        return permitName;
    }
    public void setPermitType(String permitType) 
    {
        this.permitType = permitType;
    }

    public String getPermitCode() {
        return permitCode;
    }

    public void setPermitCode(String permitCode) {
        this.permitCode = permitCode;
    }

    public String getPermitType()
    {
        return permitType;
    }
    public void setParentNo(String parentNo) 
    {
        this.parentNo = parentNo;
    }

    public String getParentNo() 
    {
        return parentNo;
    }
    public void setOrderNo(Integer orderNo) 
    {
        this.orderNo = orderNo;
    }

    public Integer getOrderNo() 
    {
        return orderNo;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getPathUrl() {
        return pathUrl;
    }

    public void setPathUrl(String pathUrl) {
        this.pathUrl = pathUrl;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public void setTarget(String target)
    {
        this.target = target;
    }

    public String getTarget() 
    {
        return target;
    }

    public void setVisible(String visible) 
    {
        this.visible = visible;
    }

    public String getVisible() 
    {
        return visible;
    }
    public void setCheckState(String checkState) 
    {
        this.checkState = checkState;
    }

    public String getCheckState() 
    {
        return checkState;
    }
    public void setBranchNo(String branchNo) 
    {
        this.branchNo = branchNo;
    }

    public String getBranchNo() 
    {
        return branchNo;
    }
    public void setDeleteFlag(String deleteFlag) 
    {
        this.deleteFlag = deleteFlag;
    }

    public String getDeleteFlag() 
    {
        return deleteFlag;
    }
    public void setComments(String comments) 
    {
        this.comments = comments;
    }

    public String getComments() 
    {
        return comments;
    }
    public void setAppCode(String appCode) 
    {
        this.appCode = appCode;
    }

    public String getAppCode() 
    {
        return appCode;
    }
    public void setVersion(Long version) 
    {
        this.version = version;
    }

    public Long getVersion() 
    {
        return version;
    }

    public List<SysPermitinfo> getChildren() {
        return children;
    }

    public void setChildren(List<SysPermitinfo> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("permitNo", getPermitNo())
            .append("permitName", getPermitName())
                .append("permitCode", getPermitCode())
            .append("permitType", getPermitType())
            .append("parentNo", getParentNo())
            .append("orderNo", getOrderNo())
                .append("linkType", getLinkType())
                .append("menuIcon", getMenuIcon())
            .append("pathUrl", getPathUrl())
                .append("component", getComponent())
            .append("target", getTarget())
            .append("visible", getVisible())
            .append("checkState", getCheckState())
            .append("branchNo", getBranchNo())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("deleteFlag", getDeleteFlag())
            .append("comments", getComments())
            .append("appCode", getAppCode())
            .append("version", getVersion())
            .toString();
    }
}
