package com.benet.web.controller;

import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.core.pager.PagerRequest;
import com.benet.common.core.pager.TableResponse;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.labsys.domain.LabsResourceinfo;
import com.benet.labsys.service.ILabsResourceinfoService;
import com.benet.system.domain.SysContentinfo;
import com.benet.system.service.ISysContentinfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 门户数据
 *
 * @author yoxking
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("portal")
public class PortalDataController extends BaseController {

    private String appCode="6000001018937812";

    @Autowired
    private final ISysContentinfoService contentinfoService;
    @Autowired
    private final ILabsResourceinfoService resourceinfoService;

    /**
     * 通知公告
     *
     * @return 结果
     */
    @PostMapping("/notice/list")
    public AjaxResult notice(@RequestBody PagerRequest pagerRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition = "";

        int count = contentinfoService.getCountByCondition(appCode,condition);
        List<SysContentinfo> list = contentinfoService.getRecordsByPaging(appCode,pagerRequest.getCurrent(), pagerRequest.getPageSize(), condition, "id", "Asc");

        TableResponse tableResponse=new TableResponse(list,count);
        return AjaxResult.success(tableResponse);
    }

    @GetMapping(value = "/notice/detail/{id}")
    public AjaxResult ndetail(@PathVariable("id") String id) {

        return AjaxResult.success(contentinfoService.getRecordByNo(appCode,id));
    }


    /**
     * 共享资源
     *
     * @return 结果
     */
    @PostMapping("/resource/list")
    public AjaxResult resource(@RequestBody PagerRequest pagerRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition = "";

        int count = resourceinfoService.getCountByCondition(appCode,condition);
        List<LabsResourceinfo> list = resourceinfoService.getRecordsByPaging(appCode,pagerRequest.getCurrent(), pagerRequest.getPageSize(), condition, "id", "Asc");

        TableResponse tableResponse=new TableResponse(list,count);
        return AjaxResult.success(tableResponse);
    }

    @GetMapping(value = "/resource/detail/{id}")
    public AjaxResult rdetail(@PathVariable("id") String id) {

        return AjaxResult.success(resourceinfoService.getRecordByNo(appCode,id));
    }
}