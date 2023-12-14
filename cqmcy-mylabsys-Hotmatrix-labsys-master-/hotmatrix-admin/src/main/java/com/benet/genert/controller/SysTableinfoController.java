package com.benet.genert.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.core.text.ConvertHelper;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.genert.domain.SysTabcolumn;
import com.benet.genert.domain.SysTableinfo;
import com.benet.genert.service.ISysTabcolumnService;
import com.benet.genert.service.ISysTableinfoService;
import io.swagger.annotations.Api;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 代码生成 操作处理
 * 
 * @author yoxking
 */
@Api(value = "genert/tableinfo", tags = "代码生成控制器")
@RestController
@RequestMapping("/genert/tableinfo")
public class SysTableinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysTableinfoService tableInfoService;

    @Autowired
    private ISysTabcolumnService tabColumnService;

    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:tableinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询代码生成列表
     */
    @PreAuthorize("@ps.hasPermit('system:tableinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("name")) {
            condition += " name=" + maps.get("name");
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = tableInfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<SysTableinfo> list = tableInfoService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询数据库列表
     */
    @GetMapping("/dbTableList")
    public AjaxResult dbTableList()
    {
        List<SysTableinfo> list = tableInfoService.getDbTableList("");
        String result="";
        if(list!=null&&list.size()>0){
            for(SysTableinfo table :list){
                result+=table.getTableName()+",";
            }
            result=result.substring(0, result.length()-1);
        }
        return AjaxResult.success(result);
    }

    /**
     * 查询数据表字段列表
     */
    @GetMapping(value = "/column/{tableNo}")
    public TableDataInfo columnList(@PathVariable String tableNo)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        TableDataInfo dataInfo = new TableDataInfo();
        List<SysTabcolumn> list = tabColumnService.getRecordsByClassNo(loginUser.getAppCode(),tableNo);
        dataInfo.setRows(list);
        dataInfo.setTotal(list.size());
        return dataInfo;
    }

    /**
     * 导入表结构（保存）
     */
    @Oplog(title = "代码生成", businessType = BusinessType.IMPORT)
    @PostMapping("/importTable")
    public AjaxResult importTable(@RequestBody String tables)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String[] tableNames = ConvertHelper.toStrArray(tables);
        // 查询表信息
        List<SysTableinfo> tableList = tableInfoService.getDbTableListByNames(tableNames);
        tableInfoService.importTableInfo(loginUser.getAppCode(),tableList);
        return AjaxResult.success();
    }

    /**
     * 保存代码生成业务
     */
    @PreAuthorize("@ps.hasPermit('system:tableinfo:save')")
    @Oplog(title = "代码生成", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysTableinfo sysTableInfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(tableInfoService.getRecordByNo(loginUser.getAppCode(),sysTableInfo.getTableNo()))) {
            sysTableInfo.setTableNo(UuidUtils.shortUUID());
            sysTableInfo.setCreateBy(loginUser.getUserNo());
            sysTableInfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(tableInfoService.AddNewRecord(loginUser.getAppCode(),sysTableInfo));
        } else {
            sysTableInfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(tableInfoService.UpdateRecord(loginUser.getAppCode(),sysTableInfo));
        }
    }

    /**
     * 删除表格信息
     */
    @PreAuthorize("@ps.hasPermit('system:tableinfo:delete')")
    @Oplog(title = "代码生成", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(tableInfoService.HardDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 代码生成信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:tableinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(tableInfoService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 导出表格信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:tableinfo:export')")
    @Oplog(title = "代码生成", businessType = BusinessType.EXPORT)
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
        int count = tableInfoService.getCountByCondition(loginUser.getAppCode(),condition);

        List<SysTableinfo> list = tableInfoService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<SysTableinfo> util = new ExcelUtils<SysTableinfo>(SysTableinfo.class);
        return util.exportExcel(list, "SysTableinfo");
    }

    /**
     * 预览代码
     */
    @GetMapping("/preview/{tableNo}")
    public AjaxResult preview(@PathVariable("tableNo") String tableNo) throws IOException
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        Map<String, String> dataMap = tableInfoService.previewCode(loginUser.getAppCode(),tableNo);
        return AjaxResult.success(dataMap);
    }

    /**
     * 生成代码
     */
    @Oplog(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/genCode/{tableName}")
    public void genCode(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        byte[] data = tableInfoService.generateCode(loginUser.getAppCode(),tableName);
        genCode(response, data);
    }

    /**
     * 批量生成代码
     */
    @Oplog(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/batchGenCode")
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String[] tableNames = ConvertHelper.toStrArray(tables);
        byte[] data = tableInfoService.generateCode(loginUser.getAppCode(),tableNames);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException
    {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"hotmatrix.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}