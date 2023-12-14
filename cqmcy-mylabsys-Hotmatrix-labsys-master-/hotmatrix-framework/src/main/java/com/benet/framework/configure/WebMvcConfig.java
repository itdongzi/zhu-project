package com.benet.framework.configure;

import com.benet.framework.interceptor.ResubmitInterceptor;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.constant.PubConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 静态资源访问配置
 *
 * @author yoxking
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{

    @Autowired
    private ResubmitInterceptor resubmitInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        /** 本地文件上传路径 */
        registry.addResourceHandler(PubConstants.RESOURCE_PREFIX + "/**").addResourceLocations("file:" + GlobalConfig.getProfile() + "/");

        /** swagger配置 */
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 自定义拦截规则
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(resubmitInterceptor).addPathPatterns("/**");
    }
}
