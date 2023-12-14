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
import com.benet.labsys.domain.LabsContestclass;
import com.benet.labsys.service.ILabsContestclassService;
import com.benet.labsys.service.ILabsContestclassService;
import com.benet.system.vmodel.ItemObjectVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 竞赛类型Controller
 *
 * @author yoxking
 * @date 2022-08-05
 */
@Api(value = "labsys/contentclass", tags = "竞赛类型控制器")
@RestController
@RequestMapping("/labsys/contentclass")
public class LabsContentclassController extends BaseController {
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsContestclassService labsContestclassService;

    /**
     * 查询竞赛类型列表
     */
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest) {
        //String condition= pagerRequest.getCondition();
        String condition = "";
        Map maps = pRequest.getDataParams();
        if (maps.containsKey("className")) {
            condition += " class_name like " + "'%" + maps.get("className") + "%'";
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsContestclassService.getCountByCondition(loginUser.getAppCode(), condition);
        List<LabsContestclass> list = labsContestclassService.getRecordsByPaging(loginUser.getAppCode(), pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        for (LabsContestclass labsContestclass : list) {
            if (!labsContestclass.getParentNo().equals("0")) {
                String recordNameByNo = labsContestclassService.getRecordNameByNo(loginUser.getAppCode(), labsContestclass.getParentNo());
                labsContestclass.setParentNo(recordNameByNo);
            } else {
                labsContestclass.setParentNo("顶级");
            }
        }
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @GetMapping(value = "/tree")
    public AjaxResult tree() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<ItemObjectVo> list = buildItemTree(loginUser.getAppCode(), "0");
        return AjaxResult.success(list);
    }

    private List<ItemObjectVo> buildItemTree(String appCode, String parentNo) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<LabsContestclass> infoList = labsContestclassService.getRecordsByClassNo(appCode, parentNo);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (LabsContestclass info : infoList) {
                item = new ItemObjectVo();
                item.setLabel(info.getClassName());
                item.setValue(info.getClassNo());
                item.setChildren(buildItemTree(appCode, info.getClassNo()));

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增竞赛类型
     */
    @Oplog(title = "竞赛类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsContestclass labsContestclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsContestclass.setClassNo(UuidUtils.shortUUID());
        labsContestclass.setCreateBy(loginUser.getUserNo());
        labsContestclass.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsContestclassService.AddNewRecord(loginUser.getAppCode(), labsContestclass));
    }

    /**
     * 编辑竞赛类型
     */
    @Oplog(title = "竞赛类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsContestclass labsContestclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsContestclass.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsContestclassService.UpdateRecord(loginUser.getAppCode(), labsContestclass));
    }

    /**
     * 保存竞赛类型
     */
    @Oplog(title = "竞赛类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsContestclass labsContestclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsContestclassService.getRecordByNo(loginUser.getAppCode(), labsContestclass.getClassNo()))) {
            labsContestclass.setClassNo(UuidUtils.shortUUID());
            labsContestclass.setCreateBy(loginUser.getUserNo());
            labsContestclass.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsContestclassService.AddNewRecord(loginUser.getAppCode(), labsContestclass));
        } else {
            labsContestclass.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsContestclassService.UpdateRecord(loginUser.getAppCode(), labsContestclass));
        }
    }

    /**
     * 删除竞赛类型
     */
    @Oplog(title = "竞赛类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsContestclassService.SoftDeleteByNos(loginUser.getAppCode(), ids));
    }

    /**
     * 获取竞赛类型详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsContestclass recordByNo = labsContestclassService.getRecordByNo(loginUser.getAppCode(), id);
        if (!recordByNo.getParentNo().equals("0")) {
            String recordNameByNo = labsContestclassService.getRecordNameByNo(loginUser.getAppCode(), recordByNo.getParentNo());
            recordByNo.setParentNo(recordNameByNo);
        } else {
            recordByNo.setParentNo("顶级");
        }
        return AjaxResult.success(recordByNo);
    }

    /**
     * 导出竞赛类型列表
     */
    @Oplog(title = "竞赛类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest) {
        //String condition= pagerRequest.getCondition();
        String condition = "";
        Map maps = pRequest.getDataParams();
        if (maps.containsKey("name")) {
            condition += " name=" + maps.get("name");
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsContestclassService.getCountByCondition(loginUser.getAppCode(), condition);

        List<LabsContestclass> list = labsContestclassService.getRecordsByPaging(loginUser.getAppCode(), 1, count, condition, "id", "Asc");
        ExcelUtils<LabsContestclass> util = new ExcelUtils<LabsContestclass>(LabsContestclass.class);
        return util.exportExcel(list, "LabsContestclass");
    }


}
