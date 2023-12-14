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
import com.benet.labsys.domain.LabsRoomclass;
import com.benet.labsys.service.ILabsRoomclassService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 实验室类型Controller
 *
 * @author yoxking
 * @date 2022-08-05
 */
@Api(value = "labsys/roomclass", tags = "实验室类型控制器")
@RestController
@RequestMapping("/labsys/roomclass")
public class LabsRoomclassController extends BaseController {
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsRoomclassService labsRoomclassService;

    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('labsys:roomclass:index')")
    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 查询实验室类型列表
     */
    @PreAuthorize("@ps.hasPermit('labsys:roomclass:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest) {
        //String condition= pagerRequest.getCondition();
        String condition = "";
        Map maps = pRequest.getDataParams();
        if (maps.containsKey("className")) {
            condition += " class_name like " + "'%" + maps.get("className") + "%'";
        }

        System.out.println("condition - " + condition);

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsRoomclassService.getCountByCondition(loginUser.getAppCode(), condition);
        List<LabsRoomclass> list = labsRoomclassService.getRecordsByPaging(loginUser.getAppCode(), pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        for (LabsRoomclass labsRoomclass : list) {
            if (!labsRoomclass.getParentNo().equals("0")) {
                String recordNameByNo = labsRoomclassService.getRecordNameByNo(loginUser.getAppCode(), labsRoomclass.getParentNo());
                labsRoomclass.setParentNo(recordNameByNo);
            } else {

                labsRoomclass.setParentNo("顶级");
            }
        }
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('labsys:roomclass:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsRoomclassService.getCountByCondition(loginUser.getAppCode(), "");
        List<ItemObjectVo> list = buildItemTree(loginUser.getAppCode(), "0");
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode, String parentNo) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<LabsRoomclass> infoList = labsRoomclassService.getRecordsByClassNo(appCode, parentNo);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (LabsRoomclass info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getClassNo());
                item.setKey(info.getClassNo());
                item.setTitle(info.getClassName());
                item.setValue(info.getClassNo());
                item.setChildren(buildItemTree(appCode, info.getClassNo()));

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增实验室类型
     */
    @PreAuthorize("@ps.hasPermit('labsys:roomclass:addnew')")
    @Oplog(title = "实验室类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsRoomclass labsRoomclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsRoomclass.setClassNo(UuidUtils.shortUUID());
        labsRoomclass.setCreateBy(loginUser.getUserNo());
        labsRoomclass.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsRoomclassService.AddNewRecord(loginUser.getAppCode(), labsRoomclass));
    }

    /**
     * 编辑实验室类型
     */
    @PreAuthorize("@ps.hasPermit('labsys:roomclass:update')")
    @Oplog(title = "实验室类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsRoomclass labsRoomclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsRoomclass.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsRoomclassService.UpdateRecord(loginUser.getAppCode(), labsRoomclass));
    }

    /**
     * 保存实验室类型
     */
    @PreAuthorize("@ps.hasPermit('labsys:roomclass:save')")
    @Oplog(title = "实验室类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsRoomclass labsRoomclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsRoomclassService.getRecordByNo(loginUser.getAppCode(), labsRoomclass.getClassNo()))) {
            labsRoomclass.setClassNo(UuidUtils.shortUUID());
            labsRoomclass.setCreateBy(loginUser.getUserNo());
            labsRoomclass.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsRoomclassService.AddNewRecord(loginUser.getAppCode(), labsRoomclass));
        } else {
            labsRoomclass.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsRoomclassService.UpdateRecord(loginUser.getAppCode(), labsRoomclass));
        }
    }

    /**
     * 删除实验室类型
     */
    @PreAuthorize("@ps.hasPermit('labsys:roomclass:delete')")
    @Oplog(title = "实验室类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsRoomclassService.SoftDeleteByNos(loginUser.getAppCode(), ids));
    }

    /**
     * 获取实验室类型详细信息
     */
    @PreAuthorize("@ps.hasPermit('labsys:roomclass:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsRoomclass labsRoomclass = labsRoomclassService.getRecordByNo(loginUser.getAppCode(), id);
        if (!labsRoomclass.getParentNo().equals("0")) {
            String recordNameByNo = labsRoomclassService.getRecordNameByNo(loginUser.getAppCode(), labsRoomclass.getParentNo());
            labsRoomclass.setParentNo(recordNameByNo);
        } else {

            labsRoomclass.setParentNo("顶级");
        }
        return AjaxResult.success(labsRoomclass);
    }

    /**
     * 导出实验室类型列表
     */
    @PreAuthorize("@ps.hasPermit('labsys:roomclass:export')")
    @Oplog(title = "实验室类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest) {
        //String condition= pagerRequest.getCondition();
        String condition = "";
        Map maps = pRequest.getDataParams();
        if (maps.containsKey("name")) {
            condition += " name=" + maps.get("name");
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsRoomclassService.getCountByCondition(loginUser.getAppCode(), condition);

        List<LabsRoomclass> list = labsRoomclassService.getRecordsByPaging(loginUser.getAppCode(), 1, count, condition, "id", "Asc");
        ExcelUtils<LabsRoomclass> util = new ExcelUtils<LabsRoomclass>(LabsRoomclass.class);
        return util.exportExcel(list, "LabsRoomclass");
    }

}
