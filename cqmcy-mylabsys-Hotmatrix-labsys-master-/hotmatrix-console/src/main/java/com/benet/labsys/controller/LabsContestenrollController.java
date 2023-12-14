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
import com.benet.labsys.domain.LabsContestenroll;
import com.benet.labsys.service.ILabsContestenrollService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 竞赛报名信息Controller
 *
 * @author yoxking
 * @date 2022-11-19 19:36:34
 */
@Api(value = "labsys/contestenroll", tags = "竞赛报名信息控制器")
@RestController
@RequestMapping("/labsys/contestenroll")
public class LabsContestenrollController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsContestenrollService labsContestenrollService;
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
     * 查询竞赛报名信息列表
     */
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        String condition = "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("contestName")&&StringUtils.isNotEmpty(maps.get("contestName").toString())) {
            condition += "And contest_no like '%" + maps.get("contestName")+"%'";
        }

        if(maps.containsKey("userNo")&&StringUtils.isNotEmpty(maps.get("userNo").toString())) {
            condition += "And user_no like '%" + maps.get("userNo")+"%'";
        }

        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsContestenrollService.getCountByCondition(loginUser.getAppCode(),condition);
        List<LabsContestenroll> list = labsContestenrollService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增竞赛报名信息
     */
    @Oplog(title = "竞赛报名信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsContestenroll labsContestenroll) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsContestenroll.setEnrollNo(UuidUtils.shortUUID());
        labsContestenroll.setCreateBy(loginUser.getUserNo());
        labsContestenroll.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsContestenrollService.AddNewRecord(loginUser.getAppCode(),labsContestenroll));
    }

    /**
     * 编辑竞赛报名信息
     */
    @Oplog(title = "竞赛报名信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsContestenroll labsContestenroll) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsContestenroll.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsContestenrollService.UpdateRecord(loginUser.getAppCode(),labsContestenroll));
    }

    /**
     * 保存竞赛报名信息
     */
    @Oplog(title = "竞赛报名信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsContestenroll labsContestenroll) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsContestenrollService.getRecordByNo(loginUser.getAppCode(),labsContestenroll.getEnrollNo()))) {
            labsContestenroll.setEnrollNo(UuidUtils.shortUUID());
            labsContestenroll.setCreateBy(loginUser.getUserNo());
            labsContestenroll.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsContestenrollService.AddNewRecord(loginUser.getAppCode(),labsContestenroll));
        } else {
            labsContestenroll.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsContestenrollService.UpdateRecord(loginUser.getAppCode(),labsContestenroll));
        }
    }

    /**
     * 删除竞赛报名信息
     */
    @Oplog(title = "竞赛报名信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsContestenrollService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(labsContestenrollService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取竞赛报名信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsContestenroll info= labsContestenrollService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出竞赛报名信息列表
     */
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
        int count = labsContestenrollService.getCountByCondition(loginUser.getAppCode(),condition);

        List<LabsContestenroll> list = labsContestenrollService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<LabsContestenroll> util = new ExcelUtils<LabsContestenroll>(LabsContestenroll.class);
        return util.exportExcel(list, "LabsContestenroll");
    }

}
