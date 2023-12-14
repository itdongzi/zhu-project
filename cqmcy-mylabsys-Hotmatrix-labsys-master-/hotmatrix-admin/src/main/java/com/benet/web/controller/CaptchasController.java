package com.benet.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletResponse;

import com.benet.common.constant.JwtConstants;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.utils.data.VrfyCodeUtils;
import com.benet.common.utils.security.Base64Utils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.framework.utils.RedisUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码操作处理
 * 
 * @author yoxking
 */
@Api(value = "web", tags = "验证码操作处理控制器")
@RestController
@RequestMapping("/web")
public class CaptchasController
{
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 生成验证码
     */
    @GetMapping("/codeImage")
    public AjaxResult getCode(HttpServletResponse response) throws IOException
    {
        // 生成随机字串
        String verifyCode = VrfyCodeUtils.generateVerifyCode(4);
        // 唯一标识
        String uuid = UuidUtils.simpleUUID();
        String verifyKey = JwtConstants.CAPTCHA_CODE_KEY + uuid;

        redisUtils.setCacheObject(verifyKey, verifyCode, JwtConstants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 生成图片
        int w = 111, h = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VrfyCodeUtils.outputImage(w, h, stream, verifyCode);
        try
        {
            AjaxResult ajax = AjaxResult.success();
            ajax.put("uuid", uuid);
            ajax.put("img", Base64Utils.encode(stream.toByteArray()));
            return ajax;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
        finally
        {
            stream.close();
        }
    }
}
