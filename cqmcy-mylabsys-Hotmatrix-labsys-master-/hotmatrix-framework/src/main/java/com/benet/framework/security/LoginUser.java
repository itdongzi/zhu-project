package com.benet.framework.security;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.benet.system.domain.SysRoleinfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 登录用户身份权限
 * 
 * @author yoxking
 */
public class LoginUser implements UserDetails
{
    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 呢称
     */
    private String userCnname;
    /**
     * 登陆时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddress;

    /**
     * 登录地点
     */
    private String location;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 用户状态
     */
    private String checkState;

    /**
     * 租户编号
     */
    private String appCode;

    /**
     * 角色列表
     */
    private List<SysRoleinfo> roles;

    /**
     * 权限列表
     */
    private Set<String> permits;


    public LoginUser()
    {
    }

    public LoginUser(String userNo,String userType,String username,String password,String avatar,String userCnname,String checkState,String appCode, Set<String> permits)
    {
        this.userNo=userNo;
        this.userType=userType;
        this.username=username;
        this.password=password;
        this.avatar = avatar;
        this.userCnname = userCnname;
        this.checkState=checkState;
        this.appCode=appCode;
        this.permits = permits;
    }

    public LoginUser(String token, String userNo, String userType,String username, String password, String avatar, String userCnname, Long loginTime, Long expireTime, String ipaddress, String location, String browser, String os, String checkState, String appCode, List<SysRoleinfo> roles, Set<String> permits) {

        this.token = token;
        this.userNo = userNo;
        this.userType=userType;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.userCnname = userCnname;
        this.loginTime = loginTime;
        this.expireTime = expireTime;
        this.ipaddress = ipaddress;
        this.location = location;
        this.browser = browser;
        this.os = os;
        this.checkState = checkState;
        this.appCode = appCode;
        this.roles = roles;
        this.permits = permits;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 账户是否未过期,过期无法验证
     */
    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return checkState.equals("0")?false:true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return
     */
    @JSONField(serialize = false)
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return checkState.equals("0")?false:true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserCnname() {
        return userCnname;
    }

    public void setUserCnname(String userCnname) {
        this.userCnname = userCnname;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getCheckState() {
        return checkState;
    }

    public void setCheckState(String checkState) {
        this.checkState = checkState;
    }

    public List<SysRoleinfo> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRoleinfo> roles) {
        this.roles = roles;
    }

    public Set<String> getPermits() {
        return permits;
    }

    public void setPermits(Set<String> permits) {
        this.permits = permits;
    }
}
