package com.benet.labsys.controller;

import java.util.List;
import java.util.Map;

import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.labsys.service.ILabsSemesterinfoService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.benet.labsys.domain.LabsSectionsinfo;
import com.benet.labsys.service.ILabsSectionsinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 教学节次时间Controller
 *
 * @author yoxking
 * @date 2022-08-15 18:06:10
 */
@Api(value = "labsys/sectionsinfo", tags = "教学节次时间控制器")
@RestController
@RequestMapping("/labsys/sectionsinfo")
@Slf4j
public class LabsSectionsinfoController extends BaseController {
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsSectionsinfoService labsSectionsinfoService;

    @Autowired
    private ILabsSemesterinfoService labsSemesterinfoService;

    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('labsys:sectionsinfo:index')")
    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 查询教学节次时间列表
     */
    @PreAuthorize("@ps.hasPermit('labsys:sectionsinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest) {
        //String condition= pagerRequest.getCondition();
        String condition = "";
        Map maps = pRequest.getDataParams();
        if (maps.containsKey("sectName")) {
            condition += " sect_name like " + "'%" + maps.get("sectName") + "%'";
        }
        if (maps.containsKey("semeNo")) {
            condition += "and seme_no like " + "'%" + maps.get("semeNo") + "%'";
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsSectionsinfoService.getCountByCondition(loginUser.getAppCode(), condition);
        List<LabsSectionsinfo> list = labsSectionsinfoService.getRecordsByPaging(loginUser.getAppCode(), pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        for (LabsSectionsinfo labsSectionsinfo : list) {
            String recordNameByNo = labsSemesterinfoService.getRecordNameByNo(loginUser.getAppCode(), labsSectionsinfo.getSemeNo());
            labsSectionsinfo.setSemeNo(recordNameByNo);
        }
        return getDataTable(list, count);
    }

    /**
     * 新增教学节次时间
     */
    @PreAuthorize("@ps.hasPermit('labsys:sectionsinfo:addnew')")
    @Oplog(title = "教学节次时间", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsSectionsinfo labsSectionsinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsSectionsinfo.setSectNo(UuidUtils.shortUUID());
        labsSectionsinfo.setCreateBy(loginUser.getUserNo());
        labsSectionsinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsSectionsinfoService.AddNewRecord(loginUser.getAppCode(), labsSectionsinfo));
    }

    /**
     * 编辑教学节次时间
     */
    @PreAuthorize("@ps.hasPermit('labsys:sectionsinfo:update')")
    @Oplog(title = "教学节次时间", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsSectionsinfo labsSectionsinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsSectionsinfo.setUpdateBy(loginUser.getUserNo());
        log.info("教学信息 - {}", labsSectionsinfo);
        return toAjax(labsSectionsinfoService.UpdateRecord(loginUser.getAppCode(), labsSectionsinfo));
    }

    /**
     * 保存教学节次时间
     */
    @PreAuthorize("@ps.hasPermit('labsys:sectionsinfo:save')")
    @Oplog(title = "教学节次时间", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsSectionsinfo labsSectionsinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsSectionsinfoService.getRecordByNo(loginUser.getAppCode(), labsSectionsinfo.getSectNo()))) {
            labsSectionsinfo.setSectNo(UuidUtils.shortUUID());
            labsSectionsinfo.setCreateBy(loginUser.getUserNo());
            labsSectionsinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsSectionsinfoService.AddNewRecord(loginUser.getAppCode(), labsSectionsinfo));
        } else {
            labsSectionsinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsSectionsinfoService.UpdateRecord(loginUser.getAppCode(), labsSectionsinfo));
        }
    }

    /**
     * 删除教学节次时间
     */
    @PreAuthorize("@ps.hasPermit('labsys:sectionsinfo:delete')")
    @Oplog(title = "教学节次时间", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsSectionsinfoService.SoftDeleteByNos(loginUser.getAppCode(), ids));
    }

    /**
     * 获取教学节次时间详细信息
     */
    @PreAuthorize("@ps.hasPermit('labsys:sectionsinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsSectionsinfo labsSectionsinfo = labsSectionsinfoService.getRecordByNo(loginUser.getAppCode(), id);
        String recordNameByNo = labsSemesterinfoService.getRecordNameByNo(loginUser.getAppCode(), labsSectionsinfo.getSemeNo());
        labsSectionsinfo.setSemeNo(recordNameByNo);
        return AjaxResult.success(labsSectionsinfo);
    }

    /**
     * 导出教学节次时间列表
     */
    @PreAuthorize("@ps.hasPermit('labsys:sectionsinfo:export')")
    @Oplog(title = "教学节次时间", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest) {
        //String condition= pagerRequest.getCondition();
        String condition = "";
        Map maps = pRequest.getDataParams();
        if (maps.containsKey("name")) {
            condition += " name=" + maps.get("name");
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsSectionsinfoService.getCountByCondition(loginUser.getAppCode(), condition);

        List<LabsSectionsinfo> list = labsSectionsinfoService.getRecordsByPaging(loginUser.getAppCode(), 1, count, condition, "id", "Asc");
        ExcelUtils<LabsSectionsinfo> util = new ExcelUtils<LabsSectionsinfo>(LabsSectionsinfo.class);
        return util.exportExcel(list, "LabsSectionsinfo");
    }

    //todo 通过semeNo查询课程信息
    @GetMapping("getSectionsinfoListByCondition")
    public AjaxResult getSectionsinfoListByCondition(String condition) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<LabsSectionsinfo> labsSectionsinfoListByCondition = null;
        log.info("查询条件,{},{}", condition,labsSectionsinfoListByCondition);
        return AjaxResult.success(labsSectionsinfoListByCondition);
    }

}
