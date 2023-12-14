package com.benet.framework.security.service;

import com.benet.common.enums.UserStatus;
import com.benet.common.exception.base.BaseException;
import com.benet.common.utils.string.StringUtils;
import com.benet.framework.security.LoginUser;
import com.benet.system.domain.SysSuserinfo;
import com.benet.system.service.ISysPermitinfoService;
import com.benet.system.service.ISysSuserinfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 系统用户验证处理
 *
 * @author yoxking
 */
@Service
public class SUserDetailsService implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(SUserDetailsService.class);

    @Autowired
    private ISysSuserinfoService userService;

    @Autowired
    private ISysPermitinfoService permitService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        SysSuserinfo user = userService.getRecordByLoginName(username);
        if (StringUtils.isNull(user))
        {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        else if (UserStatus.DELETED.getCode().equals(user.getDeleteFlag()))
        {
            log.info("登录用户：{} 已被删除.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已被删除");
        }
        else if (UserStatus.DISABLE.getCode().equals(user.getCheckState()))
        {
            log.info("登录用户：{} 已被停用.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已停用");
        }

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysSuserinfo user)
    {
        return new LoginUser(user.getUserNo(),user.getUserType(),user.getLoginName(),user.getPassword(),user.getAvatar(),user.getUserCnname(),user.getCheckState(), user.getAppCode(),permitService.getPermitCodesByUserNo(user.getAppCode(),user.getUserNo()));
    }
}