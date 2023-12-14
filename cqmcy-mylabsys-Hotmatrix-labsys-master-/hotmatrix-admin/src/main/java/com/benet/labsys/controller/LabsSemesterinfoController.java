package com.benet.labsys.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.labsys.domain.LabsRoominfo;
import com.benet.labsys.service.ILabsSectionsinfoService;
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
import com.benet.labsys.domain.LabsSemesterinfo;
import com.benet.labsys.service.ILabsSemesterinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 学期学年信息Controller
 *
 * @author yoxking
 * @date 2022-08-05
 */
@Api(value = "labsys/semesterinfo", tags = "学期学年信息控制器")
@RestController
@RequestMapping("/labsys/semesterinfo")
@Slf4j
public class LabsSemesterinfoController extends BaseController {
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsSemesterinfoService labsSemesterinfoService;

    @Autowired
    private ILabsSectionsinfoService labsSectionsinfoService;

    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('labsys:semesterinfo:index')")
    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 查询学期学年信息列表
     */
    @PreAuthorize("@ps.hasPermit('labsys:semesterinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest) {
        //String condition= pagerRequest.getCondition();
        String condition = "";
        Map maps = pRequest.getDataParams();
        if (maps.containsKey("semeName")) {
            condition += " seme_name like " + "'%" + maps.get("semeName") + "%'";

        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsSemesterinfoService.getCountByCondition(loginUser.getAppCode(), condition);
        List<LabsSemesterinfo> list = labsSemesterinfoService.getRecordsByPaging(loginUser.getAppCode(), pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增学期学年信息
     */
    @PreAuthorize("@ps.hasPermit('labsys:semesterinfo:addnew')")
    @Oplog(title = "学期学年信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsSemesterinfo labsSemesterinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsSemesterinfo.setSemeNo(UuidUtils.shortUUID());
        labsSemesterinfo.setCreateBy(loginUser.getUserNo());
        labsSemesterinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsSemesterinfoService.AddNewRecord(loginUser.getAppCode(), labsSemesterinfo));
    }

    /**
     * 编辑学期学年信息
     */
    @PreAuthorize("@ps.hasPermit('labsys:semesterinfo:update')")
    @Oplog(title = "学期学年信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsSemesterinfo labsSemesterinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsSemesterinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsSemesterinfoService.UpdateRecord(loginUser.getAppCode(), labsSemesterinfo));
    }

    /**
     * 保存学期学年信息
     */
    @PreAuthorize("@ps.hasPermit('labsys:semesterinfo:save')")
    @Oplog(title = "学期学年信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsSemesterinfo labsSemesterinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsSemesterinfoService.getRecordByNo(loginUser.getAppCode(), labsSemesterinfo.getSemeNo()))) {
            labsSemesterinfo.setSemeNo(UuidUtils.shortUUID());
            labsSemesterinfo.setCreateBy(loginUser.getUserNo());
            labsSemesterinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsSemesterinfoService.AddNewRecord(loginUser.getAppCode(), labsSemesterinfo));
        } else {
            labsSemesterinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsSemesterinfoService.UpdateRecord(loginUser.getAppCode(), labsSemesterinfo));
        }
    }

    /**
     * 删除学期学年信息
     */
    @PreAuthorize("@ps.hasPermit('labsys:semesterinfo:delete')")
    @Oplog(title = "学期学年信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<String> list = new ArrayList();
        for (String id : ids) {
            //todo 查询一下其他数据有没有绑定该数据
            int countByCondition = labsSectionsinfoService.getCountByCondition(loginUser.getAppCode(), "seme_no=" + id);
            if (countByCondition == 0){
                list.add(id);
            }
        }
        if (list.size() > 0){
            String[] newIds = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                newIds[i] = list.get(i);
            }
            log.info("需要删除的数据,{}",newIds);
            labsSemesterinfoService.SoftDeleteByNos(loginUser.getAppCode(), newIds);
        }
        if (list.size() < ids.length){
            return AjaxResult.success("选中的部分数据无法删除");
        }
        return AjaxResult.success();
    }

    /**
     * 获取学期学年信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('labsys:semesterinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(labsSemesterinfoService.getRecordByNo(loginUser.getAppCode(), id));
    }

    /**
     * 导出学期学年信息列表
     */
    @PreAuthorize("@ps.hasPermit('labsys:semesterinfo:export')")
    @Oplog(title = "学期学年信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest) {
        //String condition= pagerRequest.getCondition();
        String condition = "";
        Map maps = pRequest.getDataParams();
        if (maps.containsKey("name")) {
            condition += " name=" + maps.get("name");
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsSemesterinfoService.getCountByCondition(loginUser.getAppCode(), condition);

        List<LabsSemesterinfo> list = labsSemesterinfoService.getRecordsByPaging(loginUser.getAppCode(), 1, count, condition, "id", "Asc");
        ExcelUtils<LabsSemesterinfo> util = new ExcelUtils<LabsSemesterinfo>(LabsSemesterinfo.class);
        return util.exportExcel(list, "LabsSemesterinfo");
    }

    /**
     * 学期学年信息列表
     */
    @GetMapping("/allSemesterinfo")
    public AjaxResult allSemesterinfo(){
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        //todo 获取所有学期学年信息的信息
        System.out.println("loginUser - " + loginUser.getAppCode());
        List<LabsSemesterinfo> allRecords = labsSemesterinfoService.getAllRecords(loginUser.getAppCode());
        return AjaxResult.success(allRecords);
    }

}
