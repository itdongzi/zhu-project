package com.benet.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.utils.date.DateTimeUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.system.vmodel.MsgObjectVo;
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
import com.benet.system.domain.SysMessageinfo;
import com.benet.system.service.ISysMessageinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 消息信息Controller
 *
 * @author yoxking
 * @date 2020-04-20
 */
@Api(value = "system/messageinfo", tags = "消息信息控制器")
@RestController
@RequestMapping("/system/messageinfo")
public class SysMessageinfoController extends BaseController {
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysMessageinfoService sysMessageinfoService;

    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:messageinfo:index')")
    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 查询消息信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:messageinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest) {
        //String condition= pagerRequest.getCondition();
        String condition = "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("mtitle")&&StringUtils.isNotEmpty(maps.get("mtitle").toString())) {
            condition += "And mtitle like '%" + maps.get("mtitle")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysMessageinfoService.getCountByCondition(loginUser.getAppCode(), condition);
        List<SysMessageinfo> list = sysMessageinfoService.getRecordsByPaging(loginUser.getAppCode(), pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增消息信息
     */
    @PreAuthorize("@ps.hasPermit('system:messageinfo:addnew')")
    @Oplog(title = "消息信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysMessageinfo sysMessageinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysMessageinfo.setMssgNo(UuidUtils.shortUUID());
        sysMessageinfo.setCreateBy(loginUser.getUserNo());
        sysMessageinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(sysMessageinfoService.AddNewRecord(loginUser.getAppCode(), sysMessageinfo));
    }

    /**
     * 编辑消息信息
     */
    @PreAuthorize("@ps.hasPermit('system:messageinfo:update')")
    @Oplog(title = "消息信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody SysMessageinfo sysMessageinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysMessageinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(sysMessageinfoService.UpdateRecord(loginUser.getAppCode(), sysMessageinfo));
    }

    /**
     * 保存消息信息
     */
    @PreAuthorize("@ps.hasPermit('system:messageinfo:save')")
    @Oplog(title = "消息信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysMessageinfo sysMessageinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysMessageinfoService.getRecordByNo(loginUser.getAppCode(), sysMessageinfo.getMssgNo()))) {
            sysMessageinfo.setMssgNo(UuidUtils.shortUUID());
            sysMessageinfo.setCreateBy(loginUser.getUserNo());
            sysMessageinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysMessageinfoService.AddNewRecord(loginUser.getAppCode(), sysMessageinfo));
        } else {
            sysMessageinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysMessageinfoService.UpdateRecord(loginUser.getAppCode(), sysMessageinfo));
        }
    }

    /**
     * 删除消息信息
     */
    @PreAuthorize("@ps.hasPermit('system:messageinfo:delete')")
    @Oplog(title = "消息信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(sysMessageinfoService.SoftDeleteByNos(loginUser.getAppCode(), ids));
    }

    /**
     * 获取消息信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:messageinfo:detail')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(sysMessageinfoService.getRecordByNo(loginUser.getAppCode(), id));
    }

    /**
     * 获取消息信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:messageinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysMessageinfo info = sysMessageinfoService.getRecordByNo(loginUser.getAppCode(), id);
        if(info!=null){
            info.setReadState("1");
            info.setReceiveTime(DateTimeUtils.getNowDate());
            info.setUpdateBy(loginUser.getUserNo());
            sysMessageinfoService.UpdateRecord(loginUser.getAppCode(),info);

            info.setMssgType(info.getMssgType().equals("0")?"系统消息":"普通消息");
            info.setReadState(info.getReadState().equals("0")?"未读消息":"已读消息");
        }
        return AjaxResult.success(info);
    }

    /**
     * 导出消息信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:messageinfo:export')")
    @Oplog(title = "消息信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest) {
        //String condition= pagerRequest.getCondition();
        String condition = "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("mtitle")&&StringUtils.isNotEmpty(maps.get("mtitle").toString())) {
            condition += "And mtitle like '%" + maps.get("mtitle")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysMessageinfoService.getCountByCondition(loginUser.getAppCode(), condition);

        List<SysMessageinfo> list = sysMessageinfoService.getRecordsByPaging(loginUser.getAppCode(), 1, count, condition, "id", "Asc");
        ExcelUtils<SysMessageinfo> util = new ExcelUtils<SysMessageinfo>(SysMessageinfo.class);
        return util.exportExcel(list, "SysMessageinfo");
    }

    /**
     * 查询消息信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:messageinfo:list')")
    @GetMapping(value = "/getMessages")
    public TableDataInfo getMessages() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<SysMessageinfo> list = sysMessageinfoService.getRecordsByPaging(loginUser.getAppCode(), 1, 10, " read_state='0'", "id", "Desc");
        if (list != null && list.size() > 0) {
            List<MsgObjectVo> msgList = new ArrayList<>();
            MsgObjectVo msg = null;
            for (SysMessageinfo info : list) {
                msg = new MsgObjectVo();
                msg.setId(info.getMssgNo());
                msg.setTitle(info.getMtitle());
                msg.setDate(DateTimeUtils.formatTime(info.getSendTime(), "yyyy-MM-dd HH:mm:ss"));
                msg.setDesc(DateTimeUtils.formatTime(info.getSendTime(), "yyyy-MM-dd"));

                msgList.add(msg);
            }
            return getDataTable(msgList, msgList.size());
        }
        return getDataTable(null, 0);
    }

}
