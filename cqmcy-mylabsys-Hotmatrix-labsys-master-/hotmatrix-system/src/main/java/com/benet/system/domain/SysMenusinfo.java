package com.benet.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDateTime;


/**
 * 控制台菜单信息对象 sys_menusinfo
 * 
 * @author yoxking
 * @date 2022-09-08 14:13:52
 */
public class SysMenusinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 菜单编号 */
    @Excel(name = "菜单编号")
    private String menuNo;

    /** 菜单名称 */
    @Excel(name = "菜单名称")
    private String menuName;

    /** 菜单编码 */
    @Excel(name = "菜单编码")
    private String menuCode;

    /** 菜单类型 */
    @Excel(name = "菜单类型")
    private String menuType;

    /** 菜单图标 */
    @Excel(name = "菜单图标")
    private String menuIcon;

    /** 菜单图片 */
    @Excel(name = "菜单图片")
    private String menuImage;

    /** 父菜单ID */
    @Excel(name = "父菜单ID")
    private String parentNo;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer orderNo;

    /** 路径地址 */
    @Excel(name = "路径地址")
    private String pathUrl;

    /** 页面组件 */
    @Excel(name = "页面组件")
    private String component;

    /** 角色类型 */
    @Excel(name = "角色类型")
    private String roleType;

    /** 用户类型(01咨询师02用户) */
    @Excel(name = "用户类型(01咨询师02用户)")
    private String userType;

    /** 权限标识 */
    @Excel(name = "权限标识")
    private String pmtsData;

    /** 打开目标 */
    @Excel(name = "打开目标")
    private String target;

    /** 显示状态（0显示 1隐藏） */
    @Excel(name = "显示状态", readConverterExp = "0=显示,1=隐藏")
    private String visible;

    /** 快速连接（0否 1是） */
    @Excel(name = "快速连接", readConverterExp = "0=否,1=是")
    private String quickFlag;

    /** 状态（1正常 0停用） */
    @Excel(name = "状态", readConverterExp = "1=正常,0=停用")
    private String checkState;

    /** 删除标志（1代表存在 0代表删除） */
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

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMenuNo(String menuNo) 
    {
        this.menuNo = menuNo;
    }

    public String getMenuNo() 
    {
        return menuNo;
    }
    public void setMenuName(String menuName) 
    {
        this.menuName = menuName;
    }

    public String getMenuName() 
    {
        return menuName;
    }
    public void setMenuCode(String menuCode) 
    {
        this.menuCode = menuCode;
    }

    public String getMenuCode() 
    {
        return menuCode;
    }
    public void setMenuType(String menuType) 
    {
        this.menuType = menuType;
    }

    public String getMenuType() 
    {
        return menuType;
    }
    public void setMenuIcon(String menuIcon) 
    {
        this.menuIcon = menuIcon;
    }

    public String getMenuIcon() 
    {
        return menuIcon;
    }
    public void setMenuImage(String menuImage) 
    {
        this.menuImage = menuImage;
    }

    public String getMenuImage() 
    {
        return menuImage;
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
    public void setPathUrl(String pathUrl) 
    {
        this.pathUrl = pathUrl;
    }

    public String getPathUrl() 
    {
        return pathUrl;
    }
    public void setComponent(String component) 
    {
        this.component = component;
    }

    public String getComponent() 
    {
        return component;
    }
    public void setRoleType(String roleType) 
    {
        this.roleType = roleType;
    }

    public String getRoleType() 
    {
        return roleType;
    }
    public void setUserType(String userType) 
    {
        this.userType = userType;
    }

    public String getUserType() 
    {
        return userType;
    }
    public void setPmtsData(String pmtsData) 
    {
        this.pmtsData = pmtsData;
    }

    public String getPmtsData() 
    {
        return pmtsData;
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
    public void setQuickFlag(String quickFlag) 
    {
        this.quickFlag = quickFlag;
    }

    public String getQuickFlag() 
    {
        return quickFlag;
    }
    public void setCheckState(String checkState) 
    {
        this.checkState = checkState;
    }

    public String getCheckState() 
    {
        return checkState;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("menuNo", getMenuNo())
            .append("menuName", getMenuName())
            .append("menuCode", getMenuCode())
            .append("menuType", getMenuType())
            .append("menuIcon", getMenuIcon())
            .append("menuImage", getMenuImage())
            .append("parentNo", getParentNo())
            .append("orderNo", getOrderNo())
            .append("pathUrl", getPathUrl())
            .append("component", getComponent())
            .append("roleType", getRoleType())
            .append("userType", getUserType())
            .append("pmtsData", getPmtsData())
            .append("target", getTarget())
            .append("visible", getVisible())
            .append("quickFlag", getQuickFlag())
            .append("checkState", getCheckState())
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
