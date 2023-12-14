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
import com.benet.labsys.domain.LabsDeviceclass;
import com.benet.labsys.domain.LabsRoominfo;
import com.benet.system.vmodel.ItemObjectVo;
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
import com.benet.labsys.domain.LabsAreainfo;
import com.benet.labsys.service.ILabsAreainfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 实验楼区域Controller
 *
 * @author yoxking
 * @date 2022-08-05
 */
@Api(value = "labsys/areainfo", tags = "实验楼区域控制器")
@RestController
@RequestMapping("/labsys/areainfo")
public class LabsAreainfoController extends BaseController {
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsAreainfoService labsAreainfoService;

    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('labsys:areainfo:index')")
    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 查询实验楼区域列表
     */
    @PreAuthorize("@ps.hasPermit('labsys:areainfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest) {
        //String condition= pagerRequest.getCondition();
        String condition = "";
        Map maps = pRequest.getDataParams();
        if (maps.containsKey("areaName")) {
            condition += " area_name like " + "'%" + maps.get("areaName") + "%'";
        }
        if (maps.containsKey("size")) {
            condition += " size = " +maps.get("size");
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsAreainfoService.getCountByCondition(loginUser.getAppCode(), condition);
        List<LabsAreainfo> list = labsAreainfoService.getRecordsByPaging(loginUser.getAppCode(), pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        for (LabsAreainfo labsAreainfo : list) {
            if (!labsAreainfo.getParentNo().equals("0")) {
                String recordNameByNo = labsAreainfoService.getRecordNameByNo(loginUser.getAppCode(), labsAreainfo.getParentNo());
                labsAreainfo.setParentNo(recordNameByNo);
            } else {
                labsAreainfo.setParentNo("顶级");
            }
        }
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('labsys:areainfo:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsAreainfoService.getCountByCondition(loginUser.getAppCode(), "");
        List<ItemObjectVo> list = buildItemTree(loginUser.getAppCode(), "0");
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode, String parentNo) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<LabsAreainfo> infoList = labsAreainfoService.getRecordsByClassNo(appCode, parentNo);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (LabsAreainfo info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getAreaNo());
                item.setKey(info.getAreaNo());
                item.setTitle(info.getAreaName());
                item.setValue(info.getAreaNo());
                item.setChildren(buildItemTree(appCode, info.getAreaNo()));

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增实验楼区域
     */
    @PreAuthorize("@ps.hasPermit('labsys:areainfo:addnew')")
    @Oplog(title = "实验楼区域", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsAreainfo labsAreainfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsAreainfo.setAreaNo(UuidUtils.shortUUID());
        labsAreainfo.setCreateBy(loginUser.getUserNo());
        labsAreainfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsAreainfoService.AddNewRecord(loginUser.getAppCode(), labsAreainfo));
    }

    /**
     * 编辑实验楼区域
     */
    @PreAuthorize("@ps.hasPermit('labsys:areainfo:update')")
    @Oplog(title = "实验楼区域", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsAreainfo labsAreainfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsAreainfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsAreainfoService.UpdateRecord(loginUser.getAppCode(), labsAreainfo));
    }

    /**
     * 保存实验楼区域
     */
    @PreAuthorize("@ps.hasPermit('labsys:areainfo:save')")
    @Oplog(title = "实验楼区域", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsAreainfo labsAreainfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsAreainfoService.getRecordByNo(loginUser.getAppCode(), labsAreainfo.getAreaNo()))) {
            labsAreainfo.setAreaNo(UuidUtils.shortUUID());
            labsAreainfo.setCreateBy(loginUser.getUserNo());
            labsAreainfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsAreainfoService.AddNewRecord(loginUser.getAppCode(), labsAreainfo));
        } else {
            labsAreainfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsAreainfoService.UpdateRecord(loginUser.getAppCode(), labsAreainfo));
        }
    }

    /**
     * 删除实验楼区域
     */
    @PreAuthorize("@ps.hasPermit('labsys:areainfo:delete')")
    @Oplog(title = "实验楼区域", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsAreainfoService.SoftDeleteByNos(loginUser.getAppCode(), ids));
    }

    /**
     * 获取实验楼区域详细信息
     */
    @PreAuthorize("@ps.hasPermit('labsys:areainfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsAreainfo recordByNo = labsAreainfoService.getRecordByNo(loginUser.getAppCode(), id);
        if (!recordByNo.getParentNo().equals("0")) {
            String recordNameByNo = labsAreainfoService.getRecordNameByNo(loginUser.getAppCode(), recordByNo.getParentNo());
            recordByNo.setParentNo(recordNameByNo);
        } else {
            recordByNo.setParentNo("顶级");
        }
        return AjaxResult.success(recordByNo);
    }

    /**
     * 导出实验楼区域列表
     */
    @PreAuthorize("@ps.hasPermit('labsys:areainfo:export')")
    @Oplog(title = "实验楼区域", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest) {
        //String condition= pagerRequest.getCondition();
        String condition = "";
        Map maps = pRequest.getDataParams();
        if (maps.containsKey("name")) {
            condition += " name=" + maps.get("name");
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsAreainfoService.getCountByCondition(loginUser.getAppCode(), condition);

        List<LabsAreainfo> list = labsAreainfoService.getRecordsByPaging(loginUser.getAppCode(), 1, count, condition, "id", "Asc");
        ExcelUtils<LabsAreainfo> util = new ExcelUtils<LabsAreainfo>(LabsAreainfo.class);
        return util.exportExcel(list, "LabsAreainfo");
    }

    /**
     * 楼栋列表
     */
    @GetMapping("/allAreainfo")
    public AjaxResult allAreainfo() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        //todo 获取所有实验室的信息
        List<LabsAreainfo> allRecords = labsAreainfoService.getAllRecords(loginUser.getAppCode());
        return AjaxResult.success(allRecords);
    }

}
