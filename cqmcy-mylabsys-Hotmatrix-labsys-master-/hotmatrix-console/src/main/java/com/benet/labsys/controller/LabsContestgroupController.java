package com.benet.labsys.controller;

import java.util.List;
import java.util.Map;

import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import io.swagger.annotations.Api;
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
import com.benet.labsys.domain.LabsContestgroup;
import com.benet.labsys.service.ILabsContestgroupService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 竞赛小组信息Controller
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:34
 */
@Api(value = "labsys/contestgroup", tags = "竞赛小组信息控制器")
@RestController
@RequestMapping("/labsys/contestgroup")
public class LabsContestgroupController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsContestgroupService labsContestgroupService;
    /**
     * 首页
     */
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询竞赛小组信息列表
     */
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        String condition = "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("roomName")&&StringUtils.isNotEmpty(maps.get("roomName").toString())) {
            condition += "And room_name like '%" + maps.get("roomName")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsContestgroupService.getCountByCondition(loginUser.getAppCode(),condition);
        List<LabsContestgroup> list = labsContestgroupService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增竞赛小组信息
     */
    @Oplog(title = "竞赛小组信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsContestgroup labsContestgroup) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsContestgroup.setGroupNo(UuidUtils.shortUUID());
        labsContestgroup.setCreateBy(loginUser.getUserNo());
        labsContestgroup.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsContestgroupService.AddNewRecord(loginUser.getAppCode(),labsContestgroup));
    }

    /**
     * 编辑竞赛小组信息
     */
    @Oplog(title = "竞赛小组信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsContestgroup labsContestgroup) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsContestgroup.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsContestgroupService.UpdateRecord(loginUser.getAppCode(),labsContestgroup));
    }

    /**
     * 保存竞赛小组信息
     */
    @Oplog(title = "竞赛小组信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsContestgroup labsContestgroup) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsContestgroupService.getRecordByNo(loginUser.getAppCode(),labsContestgroup.getGroupNo()))) {
            labsContestgroup.setGroupNo(UuidUtils.shortUUID());
            labsContestgroup.setCreateBy(loginUser.getUserNo());
            labsContestgroup.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsContestgroupService.AddNewRecord(loginUser.getAppCode(),labsContestgroup));
        } else {
            labsContestgroup.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsContestgroupService.UpdateRecord(loginUser.getAppCode(),labsContestgroup));
        }
    }

    /**
     * 删除竞赛小组信息
     */
    @Oplog(title = "竞赛小组信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsContestgroupService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(labsContestgroupService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取竞赛小组信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsContestgroup info= labsContestgroupService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出竞赛小组信息列表
     */
    @Oplog(title = "竞赛小组信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        String condition = "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("roomName")&&StringUtils.isNotEmpty(maps.get("roomName").toString())) {
            condition += "And room_name like '%" + maps.get("roomName")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsContestgroupService.getCountByCondition(loginUser.getAppCode(),condition);

        List<LabsContestgroup> list = labsContestgroupService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<LabsContestgroup> util = new ExcelUtils<LabsContestgroup>(LabsContestgroup.class);
        return util.exportExcel(list, "LabsContestgroup");
    }

}
