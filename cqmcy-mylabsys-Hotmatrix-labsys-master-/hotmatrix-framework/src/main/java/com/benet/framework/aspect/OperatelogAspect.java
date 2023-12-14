package com.benet.framework.aspect;

import com.alibaba.fastjson.JSON;
import com.benet.common.annotation.Oplog;
import com.benet.common.enums.BusinessStatus;
import com.benet.common.utils.net.IpnetUtils;
import com.benet.common.utils.spring.SpringUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.manager.AsyncManager;
import com.benet.framework.manager.factory.AsyncFactory;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.system.domain.SysOperatelog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 操作日志记录处理
 * 
 * @author yoxking
 */
@Aspect
@Component
public class OperatelogAspect
{
    private static final Logger log = LoggerFactory.getLogger(OperatelogAspect.class);

    // 配置织入点
    @Pointcut("@annotation(com.benet.common.annotation.Oplog)")
    public void logPointCut()
    {
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult)
    {
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e)
    {
        handleLog(joinPoint, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult)
    {
        try
        {
            // 获得注解
            Oplog controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null)
            {
                return;
            }

            // 获取当前的用户
            LoginUser loginUser = SpringUtils.getBean(MyJwtokenService.class).getLoginUser(ServletUtils.getRequest());

            // *========数据库日志=========*//
            SysOperatelog opertLog = new SysOperatelog();
            opertLog.setOpertStatus(BusinessStatus.SUCCESS.ordinal()+"");
            // 请求的地址
            String ip = IpnetUtils.getIpAddr(ServletUtils.getRequest());
            opertLog.setOpertIp(ip);
            // 返回参数
            opertLog.setJsonResult(JSON.toJSONString(jsonResult));

            opertLog.setOpertUrl(ServletUtils.getRequest().getRequestURI());
            if (loginUser != null)
            {
                opertLog.setOpertName(loginUser.getUsername());
            }

            if (e != null)
            {
                opertLog.setOpertStatus(BusinessStatus.FAIL.ordinal()+"");
                opertLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            opertLog.setMethodName(className + "." + methodName + "()");
            // 设置请求方式
            opertLog.setRequestType(ServletUtils.getRequest().getMethod());
            // 处理设置注解上的参数
            getControllerMethodDescription(joinPoint, controllerLog, opertLog);
            // 保存数据库
            AsyncManager.me().execute(AsyncFactory.recordOperate(opertLog));
        }
        catch (Exception exp)
        {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param oplog 日志
     * @param opertLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, Oplog oplog, SysOperatelog opertLog) throws Exception
    {
        // 设置action动作
        opertLog.setOpertType(oplog.businessType().ordinal()+"");
        // 设置标题
        opertLog.setOplogTitle(oplog.title());
        // 设置操作人类别
        opertLog.setOpertType(oplog.operatorType().ordinal()+"");
        // 是否需要保存request，参数和值
        if (oplog.isSaveRequestData())
        {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(joinPoint, opertLog);
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param opertLog 操作日志
     * @throws Exception 异常
     */
    private void setRequestValue(JoinPoint joinPoint, SysOperatelog opertLog) throws Exception
    {
        String requestMethod = opertLog.getMethodName();
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod))
        {
            String params = argsArrayToString(joinPoint.getArgs());
            opertLog.setOpertParams(StringUtils.substring(params, 0, 2000));
        }
        else
        {
            Map<?, ?> paramsMap = (Map<?, ?>) ServletUtils.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            opertLog.setOpertParams(StringUtils.substring(paramsMap.toString(), 0, 2000));
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private Oplog getAnnotationLog(JoinPoint joinPoint) throws Exception
    {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null)
        {
            return method.getAnnotation(Oplog.class);
        }
        return null;
    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray)
    {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0)
        {
            for (int i = 0; i < paramsArray.length; i++)
            {
                if (!isFilterObject(paramsArray[i]))
                {
                    Object jsonObj = JSON.toJSON(paramsArray[i]);
                    params += jsonObj.toString() + " ";
                }
            }
        }
        return params.trim();
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    public boolean isFilterObject(final Object o)
    {
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse;
    }
}