package com.benet.labsys.controller;

import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.labsys.domain.LabsContestpaper;
import com.benet.labsys.domain.LabsContestresource;
import com.benet.labsys.service.ILabsContestpaperService;
import com.benet.labsys.service.ILabsContestresourceService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 竞赛资源信息Controller
 *
 * @author yoxking
 * @date 2022-11-19 19:36:34
 */
@Api(value = "labsys/contestresource", tags = "竞赛资源信息控制器")
@RestController
@RequestMapping("/labsys/contestresource")
public class LabsContestresourceController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsContestresourceService labsContestsourceService;
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
     * 查询竞赛资源信息列表
     */
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        String condition = "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("rescTitle")&& StringUtils.isNotEmpty(maps.get("rescTitle").toString())) {
            condition += "And resc_title like '%" + maps.get("rescTitle")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsContestsourceService.getCountByCondition(loginUser.getAppCode(),condition);
        List<LabsContestresource> list = labsContestsourceService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增竞赛资源信息
     */
    @PostMapping
    public AjaxResult insert(@RequestBody LabsContestresource labsContestresource) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsContestresource.setRescNo(UuidUtils.shortUUID());
        labsContestresource.setCreateBy(loginUser.getUserNo());
        labsContestresource.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsContestsourceService.AddNewRecord(loginUser.getAppCode(),labsContestresource));
    }

    /**
     * 编辑竞赛资源信息
     */
    @Oplog(title = "竞赛资源信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsContestresource labsContestresource) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsContestresource.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsContestsourceService.UpdateRecord(loginUser.getAppCode(),labsContestresource));
    }

    /**
     * 保存竞赛资源信息
     */
    @Oplog(title = "竞赛资源信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsContestresource labsContestresource) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsContestsourceService.getRecordByNo(loginUser.getAppCode(),labsContestresource.getRescNo()))) {
            labsContestresource.setRescNo(UuidUtils.shortUUID());
            labsContestresource.setCreateBy(loginUser.getUserNo());
            labsContestresource.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsContestsourceService.AddNewRecord(loginUser.getAppCode(),labsContestresource));
        } else {
            labsContestresource.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsContestsourceService.UpdateRecord(loginUser.getAppCode(),labsContestresource));
        }
    }

    /**
     * 删除竞赛资源信息
     */
    @Oplog(title = "竞赛资源信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsContestsourceService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(labsContestsourceService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取竞赛资源信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsContestresource info= labsContestsourceService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出竞赛资源信息列表
     */
    @Oplog(title = "竞赛资源信息", businessType = BusinessType.EXPORT)
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
        int count = labsContestsourceService.getCountByCondition(loginUser.getAppCode(),condition);

        List<LabsContestresource> list = labsContestsourceService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<LabsContestresource> util = new ExcelUtils<LabsContestresource>(LabsContestresource.class);
        return util.exportExcel(list, "LabsContestpaper");
    }

}
