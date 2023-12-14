package com.benet.system.controller;

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
import com.benet.system.domain.SysDepartment;
import com.benet.system.service.ISysDepartmentService;
import com.benet.system.vmodel.ItemObjectVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 部门信息Controller
 *
 * @author yoxking
 * @date 2020-03-29
 */
@Api(value = "system/department", tags = "部门信息控制器")
@RestController
@RequestMapping("/system/department")
public class SysDepartmentController extends BaseController {

    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysDepartmentService sysDepartmentService;

    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:department:index')")
    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 查询部门信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:department:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("deptName")&&StringUtils.isNotEmpty(maps.get("deptName").toString())) {
            condition += "And dept_name like '%" + maps.get("deptName")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysDepartmentService.getCountByCondition(loginUser.getAppCode(),condition);
        List<SysDepartment> list = sysDepartmentService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        if(list!=null&&list.size()>0){
            for(SysDepartment info:list){
                info.setParentNo(sysDepartmentService.getRecordNameByNo(loginUser.getAppCode(),info.getParentNo()));
            }
        }
        return getDataTable(list, count);
    }

    /**
     * 查询部门信息树形列表
     */
    @PreAuthorize("@ps.hasPermit('system:department:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysDepartmentService.getCountByCondition(loginUser.getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getAppCode(),"0");
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode, String parentNo) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<SysDepartment> infoList = sysDepartmentService.getRecordsByClassNo(appCode,parentNo);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (SysDepartment info : infoList) {

                item = new ItemObjectVo();
                item.setId(info.getDeptNo());
                item.setKey(info.getDeptNo());
                item.setTitle(info.getDeptName());
                item.setValue(info.getDeptNo());
                item.setChildren(buildItemTree(appCode,info.getDeptNo()));

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增部门信息
     */
    @PreAuthorize("@ps.hasPermit('system:department:addnew')")
    @Oplog(title = "部门信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysDepartment sysDepartment) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysDepartment.setDeptNo(UuidUtils.shortUUID());
        sysDepartment.setCreateBy(loginUser.getUserNo());
        sysDepartment.setUpdateBy(loginUser.getUserNo());
        return toAjax(sysDepartmentService.AddNewRecord(loginUser.getAppCode(),sysDepartment));
    }

    /**
     * 编辑部门信息
     */
    @PreAuthorize("@ps.hasPermit('system:department:update')")
    @Oplog(title = "部门信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody SysDepartment sysDepartment) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysDepartment.setUpdateBy(loginUser.getUserNo());
        return toAjax(sysDepartmentService.UpdateRecord(loginUser.getAppCode(),sysDepartment));
    }

    /**
     * 保存部门信息
     */
    @PreAuthorize("@ps.hasPermit('system:department:save')")
    @Oplog(title = "部门信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysDepartment sysDepartment) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysDepartmentService.getRecordByNo(loginUser.getAppCode(),sysDepartment.getDeptNo()))) {
            sysDepartment.setDeptNo(UuidUtils.shortUUID());
            sysDepartment.setCreateBy(loginUser.getUserNo());
            sysDepartment.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysDepartmentService.AddNewRecord(loginUser.getAppCode(),sysDepartment));
        } else {
            sysDepartment.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysDepartmentService.UpdateRecord(loginUser.getAppCode(),sysDepartment));
        }
    }

    /**
     * 删除部门信息
     */
    @PreAuthorize("@ps.hasPermit('system:department:delete')")
    @Oplog(title = "部门信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(sysDepartmentService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取部门信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:department:detail')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(sysDepartmentService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取部门信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:department:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysDepartment info=sysDepartmentService.getRecordByNo(loginUser.getAppCode(),id);
        if(info!=null){
            info.setParentNo(sysDepartmentService.getRecordNameByNo(loginUser.getAppCode(),info.getParentNo()));
        }
        return AjaxResult.success(info);
    }

    /**
     * 导出部门信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:department:export')")
    @Oplog(title = "部门信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("deptName")&&StringUtils.isNotEmpty(maps.get("deptName").toString())) {
            condition += "And dept_name like '%" + maps.get("deptName")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysDepartmentService.getCountByCondition(loginUser.getAppCode(),condition);

        List<SysDepartment> list = sysDepartmentService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<SysDepartment> util = new ExcelUtils<SysDepartment>(SysDepartment.class);
        return util.exportExcel(list, "department");
    }

}
