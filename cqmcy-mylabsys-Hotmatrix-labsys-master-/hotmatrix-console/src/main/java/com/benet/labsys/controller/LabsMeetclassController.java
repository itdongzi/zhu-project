package com.benet.labsys.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.labsys.domain.LabsMeetclass;
import com.benet.labsys.service.ILabsMeetclassService;
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
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 会议室类型Controller
 * 
 * @author yoxking
 * @date 2023-02-24 09:46:53
 */
@Api(value = "labsys/meetclass", tags = "会议室类型控制器")
@RestController
@RequestMapping("/labsys/meetclass")
public class LabsMeetclassController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsMeetclassService labsMeetclassService;
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
     * 查询实验室类型列表
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
        int count = labsMeetclassService.getCountByCondition(loginUser.getAppCode(),condition);
        List<LabsMeetclass> list = labsMeetclassService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    @GetMapping(value = "/tree")
    public AjaxResult tree() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<ItemObjectVo> list = buildItemTree(loginUser.getAppCode(), "0");
        return AjaxResult.success(list);
    }
    private List<ItemObjectVo> buildItemTree(String appCode, String parentNo) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<LabsMeetclass> infoList = labsMeetclassService.getRecordsByClassNo(appCode, parentNo);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (LabsMeetclass info : infoList) {
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
     * 新增实验室类型
     */
    @Oplog(title = "实验室类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsMeetclass labsMeetclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsMeetclass.setClassNo(UuidUtils.shortUUID());
        labsMeetclass.setCreateBy(loginUser.getUserNo());
        labsMeetclass.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsMeetclassService.AddNewRecord(loginUser.getAppCode(),labsMeetclass));
    }

    /**
     * 编辑实验室类型
     */
    @Oplog(title = "实验室类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsMeetclass labsMeetclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsMeetclass.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsMeetclassService.UpdateRecord(loginUser.getAppCode(),labsMeetclass));
    }

    /**
     * 保存实验室类型
     */
    @Oplog(title = "实验室类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsMeetclass labsMeetclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsMeetclassService.getRecordByNo(loginUser.getAppCode(),labsMeetclass.getClassNo()))) {
            labsMeetclass.setClassNo(UuidUtils.shortUUID());
            labsMeetclass.setCreateBy(loginUser.getUserNo());
            labsMeetclass.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsMeetclassService.AddNewRecord(loginUser.getAppCode(),labsMeetclass));
        } else {
            labsMeetclass.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsMeetclassService.UpdateRecord(loginUser.getAppCode(),labsMeetclass));
        }
    }

    /**
     * 删除实验室类型
     */
    @Oplog(title = "实验室类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsMeetclassService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(labsMeetclassService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取实验室类型详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsMeetclass info= labsMeetclassService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出实验室类型列表
     */
    @Oplog(title = "实验室类型", businessType = BusinessType.EXPORT)
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
        int count = labsMeetclassService.getCountByCondition(loginUser.getAppCode(),condition);

        List<LabsMeetclass> list = labsMeetclassService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<LabsMeetclass> util = new ExcelUtils<LabsMeetclass>(LabsMeetclass.class);
        return util.exportExcel(list, "LabsMeetclass");
    }

}