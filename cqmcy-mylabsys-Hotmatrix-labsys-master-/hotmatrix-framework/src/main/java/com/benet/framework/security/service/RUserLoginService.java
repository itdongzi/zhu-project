package com.benet.framework.security.service;

import com.benet.common.constant.JwtConstants;
import com.benet.common.constant.PubConstants;
import com.benet.common.exception.base.BaseException;
import com.benet.common.exception.user.CaptchaException;
import com.benet.common.exception.user.UserPasswordNotMatchException;
import com.benet.common.utils.data.MessageUtils;
import com.benet.framework.manager.AsyncManager;
import com.benet.framework.manager.factory.AsyncFactory;
import com.benet.framework.security.LoginUser;
import com.benet.framework.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 系统登录校验方法
 *
 * @author yoxking
 */
@Component
public class RUserLoginService {
    @Autowired
    private MyJwtokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid)
    {
        String verifyKey = JwtConstants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisUtils.getCacheObject(verifyKey);
        redisUtils.deleteObject(verifyKey);

        if (captcha == null)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfo(username, PubConstants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
            throw new CaptchaException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfo(username, PubConstants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
        // 用户验证
        Authentication authentication = null;
        try
        {
            // 该方法会去调用MyUserDetailsService.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfo(username, PubConstants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfo(username, PubConstants.LOGIN_FAIL, e.getMessage()));
                throw new BaseException(e.getMessage());
            }
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfo(username, PubConstants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(loginUser);
    }
}
