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
import com.benet.labsys.domain.LabsResourceclass;
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
import com.benet.labsys.domain.LabsDeviceclass;
import com.benet.labsys.service.ILabsDeviceclassService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 实验室设备类型Controller
 * 
 * @author yoxking
 * @date 2022-08-05
 */
@Api(value = "labsys/deviceclass", tags = "实验室设备类型控制器")
@RestController
@RequestMapping("/labsys/deviceclass")
public class LabsDeviceclassController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsDeviceclassService labsDeviceclassService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('labsys:deviceclass:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询实验室设备类型列表
     */
    @PreAuthorize("@ps.hasPermit('labsys:deviceclass:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("className")) {
            condition += " class_name like " + "'%" + maps.get("className") + "%'";
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsDeviceclassService.getCountByCondition(loginUser.getAppCode(),condition);
        List<LabsDeviceclass> list = labsDeviceclassService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        for (LabsDeviceclass labsDeviceclass : list) {
            if (!labsDeviceclass.getParentNo().equals("0")){
                String recordNameByNo = labsDeviceclassService.getRecordNameByNo(loginUser.getAppCode(), labsDeviceclass.getParentNo());
                labsDeviceclass.setParentNo(recordNameByNo);
            }else {
                labsDeviceclass.setParentNo("顶级");
            }
        }
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('labsys:deviceclass:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsDeviceclassService.getCountByCondition(loginUser.getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getAppCode(),"0");
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode, String parentNo) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<LabsDeviceclass> infoList = labsDeviceclassService.getRecordsByClassNo(appCode,parentNo);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (LabsDeviceclass info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getClassNo());
                item.setKey(info.getClassNo());
                item.setTitle(info.getClassName());
                item.setValue(info.getClassNo());
                item.setChildren(buildItemTree(appCode,info.getClassNo()));

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增实验室设备类型
     */
    @PreAuthorize("@ps.hasPermit('labsys:deviceclass:addnew')")
    @Oplog(title = "实验室设备类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsDeviceclass labsDeviceclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsDeviceclass.setClassNo(UuidUtils.shortUUID());
        labsDeviceclass.setCreateBy(loginUser.getUserNo());
        labsDeviceclass.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsDeviceclassService.AddNewRecord(loginUser.getAppCode(),labsDeviceclass));
    }

    /**
     * 编辑实验室设备类型
     */
    @PreAuthorize("@ps.hasPermit('labsys:deviceclass:update')")
    @Oplog(title = "实验室设备类型", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody LabsDeviceclass labsDeviceclass) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsDeviceclass.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsDeviceclassService.UpdateRecord(loginUser.getAppCode(),labsDeviceclass));
        }

    /**
     * 保存实验室设备类型
     */
    @PreAuthorize("@ps.hasPermit('labsys:deviceclass:save')")
    @Oplog(title = "实验室设备类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsDeviceclass labsDeviceclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsDeviceclassService.getRecordByNo(loginUser.getAppCode(),labsDeviceclass.getClassNo()))) {
            labsDeviceclass.setClassNo(UuidUtils.shortUUID());
            labsDeviceclass.setCreateBy(loginUser.getUserNo());
            labsDeviceclass.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsDeviceclassService.AddNewRecord(loginUser.getAppCode(),labsDeviceclass));
        } else {
            labsDeviceclass.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsDeviceclassService.UpdateRecord(loginUser.getAppCode(),labsDeviceclass));
        }
    }

    /**
     * 删除实验室设备类型
     */
    @PreAuthorize("@ps.hasPermit('labsys:deviceclass:delete')")
    @Oplog(title = "实验室设备类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsDeviceclassService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取实验室设备类型详细信息
     */
    @PreAuthorize("@ps.hasPermit('labsys:deviceclass:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsDeviceclass recordByNo = labsDeviceclassService.getRecordByNo(loginUser.getAppCode(), id);
        if (!recordByNo.getParentNo().equals("0")){
            String recordNameByNo = labsDeviceclassService.getRecordNameByNo(loginUser.getAppCode(), recordByNo.getParentNo());
            recordByNo.setParentNo(recordNameByNo);
        }else {
            recordByNo.setParentNo("顶级");
        }
        return AjaxResult.success(recordByNo);
    }

    /**
     * 导出实验室设备类型列表
     */
    @PreAuthorize("@ps.hasPermit('labsys:deviceclass:export')")
    @Oplog(title = "实验室设备类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("name")) {
            condition += " name=" + maps.get("name");
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsDeviceclassService.getCountByCondition(loginUser.getAppCode(),condition);

        List<LabsDeviceclass> list = labsDeviceclassService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<LabsDeviceclass> util = new ExcelUtils<LabsDeviceclass>(LabsDeviceclass.class);
        return util.exportExcel(list, "LabsDeviceclass");
    }

    /**
     * 设备类型列表
     */
    @GetMapping("/allDeviceclass")
    public AjaxResult allDeviceclass() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        //todo 获取所有实验室的信息
        List<LabsDeviceclass> allRecords = labsDeviceclassService.getAllRecords(loginUser.getAppCode());
        return AjaxResult.success(allRecords);
    }

}
