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
import com.benet.labsys.domain.LabsContestsignin;
import com.benet.labsys.service.ILabsContestsigninService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 竞赛打卡信息Controller
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:34
 */
@Api(value = "labsys/contestsignin", tags = "竞赛打卡信息控制器")
@RestController
@RequestMapping("/labsys/contestsignin")
public class LabsContestsigninController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsContestsigninService labsContestsigninService;
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
     * 查询竞赛打卡信息列表
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
        int count = labsContestsigninService.getCountByCondition(loginUser.getAppCode(),condition);
        List<LabsContestsignin> list = labsContestsigninService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增竞赛打卡信息
     */
    @Oplog(title = "竞赛打卡信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsContestsignin labsContestsignin) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsContestsignin.setSignNo(UuidUtils.shortUUID());
        labsContestsignin.setCreateBy(loginUser.getUserNo());
        labsContestsignin.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsContestsigninService.AddNewRecord(loginUser.getAppCode(),labsContestsignin));
    }

    /**
     * 编辑竞赛打卡信息
     */
    @Oplog(title = "竞赛打卡信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsContestsignin labsContestsignin) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsContestsignin.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsContestsigninService.UpdateRecord(loginUser.getAppCode(),labsContestsignin));
    }

    /**
     * 保存竞赛打卡信息
     */
    @Oplog(title = "竞赛打卡信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsContestsignin labsContestsignin) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsContestsigninService.getRecordByNo(loginUser.getAppCode(),labsContestsignin.getSignNo()))) {
            labsContestsignin.setSignNo(UuidUtils.shortUUID());
            labsContestsignin.setCreateBy(loginUser.getUserNo());
            labsContestsignin.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsContestsigninService.AddNewRecord(loginUser.getAppCode(),labsContestsignin));
        } else {
            labsContestsignin.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsContestsigninService.UpdateRecord(loginUser.getAppCode(),labsContestsignin));
        }
    }

    /**
     * 删除竞赛打卡信息
     */
    @Oplog(title = "竞赛打卡信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsContestsigninService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(labsContestsigninService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取竞赛打卡信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsContestsignin info= labsContestsigninService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出竞赛打卡信息列表
     */
    @Oplog(title = "竞赛打卡信息", businessType = BusinessType.EXPORT)
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
        int count = labsContestsigninService.getCountByCondition(loginUser.getAppCode(),condition);

        List<LabsContestsignin> list = labsContestsigninService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<LabsContestsignin> util = new ExcelUtils<LabsContestsignin>(LabsContestsignin.class);
        return util.exportExcel(list, "LabsContestsignin");
    }

}
