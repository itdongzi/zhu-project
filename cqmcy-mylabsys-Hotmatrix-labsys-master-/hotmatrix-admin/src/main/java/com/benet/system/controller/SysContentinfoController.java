package com.benet.system.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.utils.file.FileUploadUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.system.service.ISysConteeclassService;


import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.benet.system.domain.SysContentinfo;
import com.benet.system.service.ISysContentinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

import javax.servlet.http.HttpServletRequest;

import static com.benet.common.utils.file.MimeTypeUtils.getExtension;

/**
 * 内容信息Controller
 *
 * @author yoxking
 * @date 2020-04-20
 */
@Api(value = "system/contentinfo", tags = "内容信息控制器")
@RestController
@RequestMapping("/system/contentinfo")
@Slf4j
public class SysContentinfoController extends BaseController {
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysContentinfoService sysContentinfoService;

    @Autowired
    private ISysConteeclassService sysConteeclassService;

    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:contentinfo:index')")
    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 查询内容信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:contentinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest) {
        //String condition= pagerRequest.getCondition();
        String condition = "";
        Map maps = pRequest.getDataParams();
        if (maps.containsKey("classNo")&&StringUtils.isNotEmpty(maps.get("classNo").toString())&&!maps.get("classNo").toString().equals("0")) {
            condition += "And class_no = '" + maps.get("classNo")+ "'";
        }
        if (maps.containsKey("title")&&StringUtils.isNotEmpty(maps.get("title").toString())) {
            condition += "And title like " + "'%" + maps.get("title") + "%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysContentinfoService.getCountByCondition(loginUser.getAppCode(), condition);
        List<SysContentinfo> list = sysContentinfoService.getRecordsByPaging(loginUser.getAppCode(), pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        for (SysContentinfo sysContentinfo : list) {
            sysContentinfo.setClassNo(sysConteeclassService.getClassNameByClassNo(loginUser.getAppCode(), sysContentinfo.getClassNo()));
        }
        return getDataTable(list, count);
    }

    /**
     * 新增内容信息
     */
    @PreAuthorize("@ps.hasPermit('system:contentinfo:addnew')")
    @Oplog(title = "内容信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysContentinfo sysContentinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysContentinfo.setContzNo(UuidUtils.shortUUID());
        sysContentinfo.setCreateBy(loginUser.getUserNo());
        sysContentinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(sysContentinfoService.AddNewRecord(loginUser.getAppCode(), sysContentinfo));
    }

    /**
     * 编辑内容信息
     */
    @PreAuthorize("@ps.hasPermit('system:contentinfo:update')")
    @Oplog(title = "内容信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody SysContentinfo sysContentinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysContentinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(sysContentinfoService.UpdateRecord(loginUser.getAppCode(), sysContentinfo));
    }

    /**
     * 保存内容信息
     */
    @PreAuthorize("@ps.hasPermit('system:contentinfo:save')")
    @Oplog(title = "内容信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysContentinfo sysContentinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysContentinfoService.getRecordByNo(loginUser.getAppCode(), sysContentinfo.getContzNo()))) {
            sysContentinfo.setContzNo(UuidUtils.shortUUID());
            sysContentinfo.setCreateBy(loginUser.getUserNo());
            sysContentinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysContentinfoService.AddNewRecord(loginUser.getAppCode(), sysContentinfo));
        } else {
            sysContentinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysContentinfoService.UpdateRecord(loginUser.getAppCode(), sysContentinfo));
        }
    }

    /**
     * 删除内容信息
     */
    @PreAuthorize("@ps.hasPermit('system:contentinfo:delete')")
    @Oplog(title = "内容信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(sysContentinfoService.SoftDeleteByNos(loginUser.getAppCode(), ids));
    }

    /**
     * 获取内容信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:contentinfo:detail')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(sysContentinfoService.getRecordByNo(loginUser.getAppCode(), id));
    }

    /**
     * 获取内容信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:contentinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysContentinfo info = sysContentinfoService.getRecordByNo(loginUser.getAppCode(), id);
        if(info!=null) {
            info.setComments(sysConteeclassService.getClassNameByClassNo(loginUser.getAppCode(), info.getClassNo()));
        }
        return AjaxResult.success(info);
    }

    /**
     * 导出内容信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:contentinfo:export')")
    @Oplog(title = "内容信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest) {
        //String condition= pagerRequest.getCondition();
        String condition = "";
        Map maps = pRequest.getDataParams();
        if (maps.containsKey("classNo")&&StringUtils.isNotEmpty(maps.get("classNo").toString())&&!maps.get("classNo").toString().equals("0")) {
            condition += "And class_no = '" + maps.get("classNo")+ "'";
        }
        if (maps.containsKey("title")&&StringUtils.isNotEmpty(maps.get("title").toString())) {
            condition += "And title like " + "'%" + maps.get("title") + "%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysContentinfoService.getCountByCondition(loginUser.getAppCode(), condition);

        List<SysContentinfo> list = sysContentinfoService.getRecordsByPaging(loginUser.getAppCode(), 1, count, condition, "id", "Asc");
        ExcelUtils<SysContentinfo> util = new ExcelUtils<SysContentinfo>(SysContentinfo.class);
        return util.exportExcel(list, "SysContentinfo");
    }

}
