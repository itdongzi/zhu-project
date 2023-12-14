package com.benet.framework.security.service;

import java.util.Set;

import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.system.domain.SysRoleinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * 首创 自定义权限实现
 * 
 * @author yoxking
 */
@Service("ps")
public class MyPermitService
{
    /** 所有权限标识 */
    private static final String ALL_permit = "*:*:*";

    /** 管理员角色权限标识 */
    private static final String SUPER_ADMIN = "admin";

    private static final String ROLE_DELIMETER = ",";

    private static final String PERMIT_DELIMETER = ",";

    @Autowired
    private MyJwtokenService tokenService;

    /**
     * 验证用户是否具备某权限
     * 
     * @param permit 权限字符串
     * @return 用户是否具备某权限
     */
    public boolean hasPermit(String permit)
    {
        if (StringUtils.isEmpty(permit))
        {
            return false;
        }
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getPermits()))
        {
            return false;
        }
        return hasPermits(loginUser.getPermits(), permit);
    }

    /**
     * 验证用户是否不具备某权限，与 hasPermi逻辑相反
     *
     * @param permit 权限字符串
     * @return 用户是否不具备某权限
     */
    public boolean lacksPermit(String permit)
    {
        return hasPermit(permit) != true;
    }

    /**
     * 验证用户是否具有以下任意一个权限
     *
     * @param permits 以 permit_NAMES_DELIMETER 为分隔符的权限列表
     * @return 用户是否具有以下任意一个权限
     */
    public boolean hasAnyPermit(String permits)
    {
        if (StringUtils.isEmpty(permits))
        {
            return false;
        }
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getPermits()))
        {
            return false;
        }
        Set<String> authorities = loginUser.getPermits();
        for (String permit : permits.split(PERMIT_DELIMETER))
        {
            if (permit != null && hasPermits(authorities, permit))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断用户是否拥有某个角色
     * 
     * @param role 角色字符串
     * @return 用户是否具备某角色
     */
    public boolean hasRole(String role)
    {
        if (StringUtils.isEmpty(role))
        {
            return false;
        }
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getRoles()))
        {
            return false;
        }
        for (SysRoleinfo sysRole : loginUser.getRoles())
        {
            String roleCode = sysRole.getRoleCode();
            if (SUPER_ADMIN.contains(roleCode) || roleCode.contains(StringUtils.trim(role)))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证用户是否不具备某角色，与 isRole逻辑相反。
     *
     * @param role 角色名称
     * @return 用户是否不具备某角色
     */
    public boolean lacksRole(String role)
    {
        return hasRole(role) != true;
    }

    /**
     * 验证用户是否具有以下任意一个角色
     *
     * @param roles 以 ROLE_NAMES_DELIMETER 为分隔符的角色列表
     * @return 用户是否具有以下任意一个角色
     */
    public boolean hasAnyRoles(String roles)
    {
        if (StringUtils.isEmpty(roles))
        {
            return false;
        }
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getRoles()))
        {
            return false;
        }
        for (String role : roles.split(ROLE_DELIMETER))
        {
            if (hasRole(role))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否包含权限
     * 
     * @param permits 权限列表
     * @param permit 权限字符串
     * @return 用户是否具备某权限
     */
    private boolean hasPermits(Set<String> permits, String permit)
    {
        return permits.contains(ALL_permit) || permits.contains(StringUtils.trim(permit));
    }
}
