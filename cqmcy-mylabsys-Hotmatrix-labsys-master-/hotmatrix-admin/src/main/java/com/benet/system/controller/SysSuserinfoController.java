package com.benet.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.utils.file.FileUploadUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.framework.utils.SecurityUtils;
import com.benet.system.service.ISysDepartmentService;
import com.benet.system.service.ISysPostinfoService;
import com.benet.system.vmodel.ItemObjectVo;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.benet.system.domain.SysSuserinfo;
import com.benet.system.service.ISysSuserinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 系统用户信息Controller
 *
 * @author yoxking
 * @date 2020-04-20
 */
@Api(value = "system/suserinfo", tags = "系统用户信息控制器")
@RestController
@RequestMapping("/system/suserinfo")
public class SysSuserinfoController extends BaseController {
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysPostinfoService postinfoService;
    @Autowired
    private ISysDepartmentService departmentService;
    @Autowired
    private ISysSuserinfoService sysSuserinfoService;

    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:suserinfo:index')")
    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 查询系统用户信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:suserinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("userCnname")&&StringUtils.isNotEmpty(maps.get("userCnname").toString())) {
            condition += "And user_cnname like '%" + maps.get("userCnname")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysSuserinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<SysSuserinfo> list = sysSuserinfoService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        if(list!=null&&list.size()>0){
            for (SysSuserinfo info:list){
                info.setDeptNo(departmentService.getRecordNameByNo(loginUser.getAppCode(),info.getDeptNo()));
                info.setPostNo(postinfoService.getRecordNameByNo(loginUser.getAppCode(),info.getPostNo()));
            }
        }
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('system:suserinfo:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysSuserinfoService.getCountByCondition(loginUser.getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getAppCode());
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<SysSuserinfo> infoList = sysSuserinfoService.getAllRecords(appCode);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (SysSuserinfo info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getUserNo());
                item.setKey(info.getUserNo());
                item.setTitle(info.getUserCnname());
                item.setValue(info.getUserNo());
                item.setChildren(null);

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增系统用户信息
     */
    @PreAuthorize("@ps.hasPermit('system:suserinfo:addnew')")
    @Oplog(title = "系统用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysSuserinfo sysSuserinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysSuserinfo.setUserNo(UuidUtils.shortUUID());
        sysSuserinfo.setCreateBy(loginUser.getUserNo());
        sysSuserinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(sysSuserinfoService.AddNewRecord(loginUser.getAppCode(),sysSuserinfo));
    }

    /**
     * 编辑系统用户信息
     */
    @PreAuthorize("@ps.hasPermit('system:suserinfo:update')")
    @Oplog(title = "系统用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody SysSuserinfo sysSuserinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysSuserinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(sysSuserinfoService.UpdateRecord(loginUser.getAppCode(),sysSuserinfo));
    }

    /**
     * 保存系统用户信息
     */
    @PreAuthorize("@ps.hasPermit('system:suserinfo:save')")
    @Oplog(title = "系统用户信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysSuserinfo sysSuserinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysSuserinfoService.getRecordByNo(loginUser.getAppCode(),sysSuserinfo.getUserNo()))) {
            sysSuserinfo.setUserNo(UuidUtils.shortUUID());
            sysSuserinfo.setCreateBy(loginUser.getUserNo());
            sysSuserinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysSuserinfoService.AddNewRecord(loginUser.getAppCode(),sysSuserinfo));
        } else {
            sysSuserinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysSuserinfoService.UpdateRecord(loginUser.getAppCode(),sysSuserinfo));
        }
    }

    /**
     * 删除系统用户信息
     */
    @PreAuthorize("@ps.hasPermit('system:suserinfo:delete')")
    @Oplog(title = "系统用户信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(sysSuserinfoService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取系统用户信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:suserinfo:detail')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(sysSuserinfoService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取系统用户信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:suserinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysSuserinfo info=sysSuserinfoService.getRecordByNo(loginUser.getAppCode(),id);
        if(info!=null){
            info.setDeptNo(departmentService.getRecordNameByNo(loginUser.getAppCode(),info.getDeptNo()));
            info.setPostNo(postinfoService.getRecordNameByNo(loginUser.getAppCode(),info.getPostNo()));
        }
        return AjaxResult.success(info);
    }

    /**
     * 导出系统用户信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:suserinfo:export')")
    @Oplog(title = "系统用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("userCnname")&&StringUtils.isNotEmpty(maps.get("userCnname").toString())) {
            condition += "And user_cnname like '%" + maps.get("userCnname")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysSuserinfoService.getCountByCondition(loginUser.getAppCode(),condition);

        List<SysSuserinfo> list = sysSuserinfoService.getRecordsByPaging(loginUser.getAppCode(),1, count, condition, "id", "Asc");
        ExcelUtils<SysSuserinfo> util = new ExcelUtils<SysSuserinfo>(SysSuserinfo.class);
        return util.exportExcel(list, "SysSuserinfo");
    }

    /**
     * 获取系统用户信息详细信息
     */
    //@PreAuthorize("@ps.hasPermit('system:suserinfo:profile')")
    @GetMapping(value = "/profile")
    public AjaxResult profile() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysSuserinfo userInfo = sysSuserinfoService.getRecordByNo(loginUser.getAppCode(),loginUser.getUserNo());
        return AjaxResult.success(userInfo);
    }

    /**
     * 修改用户密码
     */
    @PreAuthorize("@ps.hasPermit('system:suserinfo:update')")
    @Oplog(title = "修改用户密码", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/password")
    public AjaxResult password(String oldPswd, String newPswd) {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String userNo = loginUser.getUserNo();
        String password = loginUser.getPassword();
        if (!SecurityUtils.matchesPassword(oldPswd, password)) {
            return AjaxResult.error("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPswd, password)) {
            return AjaxResult.error("新密码不能与旧密码相同");
        }

        if (sysSuserinfoService.resetUserPassword(userNo,SecurityUtils.encryptPassword(newPswd)) > 0) {
            // 更新缓存用户密码
            loginUser.setPassword(SecurityUtils.encryptPassword(newPswd));
            tokenService.setLoginUser(loginUser);
            return AjaxResult.success();
        }
        return AjaxResult.error("修改密码异常，请联系管理员");
    }

    /**
     * 头像上传
     */
    @Oplog(title = "更新用户头像", businessType = BusinessType.UPDATE)
    @PostMapping("/uploadAvatar")
    public AjaxResult uploadAvatar(@RequestParam("avatarfile") MultipartFile avatarfile) throws IOException
    {
        if (!avatarfile.isEmpty())
        {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            SysSuserinfo userInfo = sysSuserinfoService.getRecordByNo(loginUser.getAppCode(),loginUser.getUserNo());
            if(userInfo!=null) {
                String avatar = FileUploadUtils.upload(GlobalConfig.getAvatarPath(), avatarfile);
                userInfo.setAvatar(avatar);
                if (sysSuserinfoService.UpdateRecord(loginUser.getAppCode(),userInfo)>0) {
                    AjaxResult ajax = AjaxResult.success();
                    ajax.put("imgUrl", avatar);
                    // 更新缓存用户头像
                    //loginUser.setAvatar(avatar);
                    tokenService.setLoginUser(loginUser);
                    return ajax;
                }
            }
        }
        return AjaxResult.error("上传图片异常，请联系管理员");
    }

    /**
     * 校验用户名是否重复
     */
    @GetMapping(value = "checkLoginName/{name}")
    public AjaxResult checkLoginName(@PathVariable("name") String name) {
        return AjaxResult.success(sysSuserinfoService.checkLoginNameUnique(name));
    }

}
