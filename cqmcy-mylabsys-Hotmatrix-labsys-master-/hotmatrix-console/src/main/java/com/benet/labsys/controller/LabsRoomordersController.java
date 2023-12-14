package com.benet.labsys.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.system.constant.OrderCheckStatus;
import com.benet.system.vmodel.RoomOrderValueVo;
import lombok.RequiredArgsConstructor;
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
import com.benet.labsys.domain.LabsRoomorders;
import com.benet.labsys.service.ILabsRoomordersService;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 实验室预约Controller
 * 
 * @author yoxking
 * @date 2022-08-05
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/labsys/roomorders")
public class LabsRoomordersController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsRoomordersService labsRoomordersService;
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
     * 查询实验室预约列表
     */
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsRoomordersService.getCountByCondition(loginUser.getAppCode(),pRequest.getCondition());
        List<LabsRoomorders> list = labsRoomordersService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增实验室预约
     */
    @PostMapping
    public AjaxResult insert(@RequestBody JSONObject json) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        RoomOrderValueVo roomOrderValueVo = new RoomOrderValueVo();
        roomOrderValueVo.setZc(json.getString("kkzc"));
        roomOrderValueVo.setXq(json.getString("week"));
        roomOrderValueVo.setJc(json.getString("jc"));
        roomOrderValueVo.setJsxm(json.getString("jsxm"));
        roomOrderValueVo.setKcmc(json.getString("kcmc"));
        roomOrderValueVo.setBjxx(json.getString("bjxx"));

        LabsRoomorders labsRoomorders = new LabsRoomorders();

        labsRoomorders.setOrderNo(UuidUtils.shortUUID());
        labsRoomorders.setRoomNo(json.getString("roomNo"));
        labsRoomorders.setSemeNo(json.getString("semeNo"));
        labsRoomorders.setUserNo(loginUser.getUserNo());
        String orderValueStr = JSON.toJSONString(roomOrderValueVo);
        labsRoomorders.setOrderValue(orderValueStr);
        labsRoomorders.setOrderDtime(LocalDateTime.parse(json.getString("orderDtime"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        labsRoomorders.setCheckState(OrderCheckStatus.UNCHECK.getCode());
        labsRoomorders.setComments(json.getString("comments"));
        labsRoomorders.setCreateBy(loginUser.getUserNo());
        labsRoomorders.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsRoomordersService.AddNewRecord(loginUser.getAppCode(),labsRoomorders));
    }

    /**
     * 编辑实验室预约
     */
    @PutMapping
        public AjaxResult update(@RequestBody LabsRoomorders labsRoomorders) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsRoomorders.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsRoomordersService.UpdateRecord(loginUser.getAppCode(),labsRoomorders));
        }

    /**
     * 保存实验室预约
     */
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsRoomorders labsRoomorders) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsRoomordersService.getRecordByNo(loginUser.getAppCode(),labsRoomorders.getOrderNo()))) {
            labsRoomorders.setOrderNo(UuidUtils.shortUUID());
            labsRoomorders.setCreateBy(loginUser.getUserNo());
            labsRoomorders.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsRoomordersService.AddNewRecord(loginUser.getAppCode(),labsRoomorders));
        } else {
            labsRoomorders.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsRoomordersService.UpdateRecord(loginUser.getAppCode(),labsRoomorders));
        }
    }

    /**
     * 删除实验室预约
     */
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsRoomordersService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取实验室预约详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(labsRoomordersService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 导出实验室预约列表
     */
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsRoomordersService.getCountByCondition(loginUser.getAppCode(),pRequest.getCondition());

        List<LabsRoomorders> list = labsRoomordersService.getRecordsByPaging(loginUser.getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<LabsRoomorders> util = new ExcelUtils<LabsRoomorders>(LabsRoomorders.class);
        return util.exportExcel(list, "LabsRoomorders");
    }

}
