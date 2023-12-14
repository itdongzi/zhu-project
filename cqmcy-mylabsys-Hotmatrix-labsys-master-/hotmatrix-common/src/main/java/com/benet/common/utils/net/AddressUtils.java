package com.benet.common.utils.net;

import com.alibaba.fastjson.JSONObject;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.constant.GenConstants;
import com.benet.common.constant.PubConstants;
import com.benet.common.utils.http.HttpUtils;
import com.benet.common.utils.string.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 获取地址类
 * 
 * @author yoxking
 */
public class AddressUtils
{
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    // IP地址查询
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    public static String getRealAddressByIP(String ip)
    {
        String address = "unknow";

        // 内网不查询
        if (IpnetUtils.internalIp(ip))
        {
            return "内网IP";
        }
        if (GlobalConfig.isAddressEnabled())
        {
            try
            {
                String rspStr = HttpUtils.sendGet(IP_URL, "ip=" + ip + "&json=true");
                if (StringUtils.isEmpty(rspStr))
                {
                    log.error("获取地理位置异常 {}", ip);
                    return PubConstants.UNKNOWN;
                }
                JSONObject obj = JSONObject.parseObject(rspStr);
                String region = obj.getString("pro");
                String city = obj.getString("city");
                return String.format("%s %s", region, city);
            }
            catch (Exception e)
            {
                log.error("获取地理位置异常 {}", ip);
            }
        }
        return address;
    }
}
