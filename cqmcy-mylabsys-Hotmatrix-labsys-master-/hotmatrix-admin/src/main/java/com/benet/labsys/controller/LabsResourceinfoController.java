package com.benet.labsys.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.benet.common.configure.ServerConfig;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.labsys.domain.LabsResourcefiles;
import com.benet.labsys.service.ILabsResourceclassService;
import com.benet.labsys.service.ILabsResourcefilesService;
import com.benet.labsys.vmodel.FileInfoVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
import com.benet.labsys.domain.LabsResourceinfo;
import com.benet.labsys.service.ILabsResourceinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;


/**
 * 教学资源信息Controller
 *
 * @author yoxking
 * @date 2022-08-05
 */
@Api(value = "labsys/resourceinfo", tags = "教学资源信息控制器")
@RestController
@RequestMapping("/labsys/resourceinfo")
@Slf4j
public class LabsResourceinfoController extends BaseController {
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsResourceinfoService labsResourceinfoService;

    @Autowired
    private ILabsResourceclassService labsResourceclassService;

    @Autowired
    private ILabsResourcefilesService labsResourcefilesService;

    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('labsys:resourceinfo:index')")
    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 查询教学资源信息列表
     */
    @PreAuthorize("@ps.hasPermit('labsys:resourceinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest) {
        //String condition= pagerRequest.getCondition();
        String condition = "";
        Map maps = pRequest.getDataParams();
        if (maps.containsKey("rescName")) {
            condition += " resc_name like " + "'%" + maps.get("rescName") + "%'";
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsResourceinfoService.getCountByCondition(loginUser.getAppCode(), condition);
        List<LabsResourceinfo> list = labsResourceinfoService.getRecordsByPaging(loginUser.getAppCode(), pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        for (LabsResourceinfo labsResourceinfo : list) {
            // todo 设置资源类型名称
            String recordNameByNo = labsResourceclassService.getRecordNameByNo(loginUser.getAppCode(), labsResourceinfo.getClassNo());
            labsResourceinfo.setClassNo(recordNameByNo);
        }
        return getDataTable(list, count);
    }

    /**
     * 新增教学资源信息
     */
    @PreAuthorize("@ps.hasPermit('labsys:resourceinfo:addnew')")
    @Oplog(title = "教学资源信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsResourceinfo labsResourceinfo) {
        log.info("资源信息 - {}", labsResourceinfo);
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String rescNo = UuidUtils.shortUUID();
        labsResourceinfo.setRescNo(rescNo);
        labsResourceinfo.setCreateBy(loginUser.getUserNo());
        labsResourceinfo.setUpdateBy(loginUser.getUserNo());
        /*
        if (labsResourceinfo.getFileList().size() > 0) {
            for (FileInfoVO fileInfoVO : labsResourceinfo.getFileList()) {
                log.info("文件信息 - {}", fileInfoVO);
                String[] split = fileInfoVO.getName().split("\\.");
                log.info("数组信息 - {}", split);
                LabsResourcefiles labsResourcefiles = new LabsResourcefiles();
                labsResourcefiles
                        .setFileNo(UuidUtils.shortUUID())
                        .setRescNo(rescNo)
                        .setFileName(fileInfoVO.getName())
                        .setFileUrl(fileInfoVO.getFileAddress())
                        .setFileSize(Integer.valueOf(fileInfoVO.getSize()))
                        .setFileExt(split[split.length - 1])
                        .setCheckState("1");
                labsResourcefiles.setCreateBy(loginUser.getUserNo());
                labsResourcefiles.setUpdateBy(loginUser.getUserNo());
                labsResourcefilesService.AddNewRecord(loginUser.getAppCode(), labsResourcefiles);
            }
        }
         */
        return toAjax(labsResourceinfoService.AddNewRecord(loginUser.getAppCode(), labsResourceinfo));
    }

    /**
     * 编辑教学资源信息
     */
    @PreAuthorize("@ps.hasPermit('labsys:resourceinfo:update')")
    @Oplog(title = "教学资源信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsResourceinfo labsResourceinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsResourceinfo.setUpdateBy(loginUser.getUserNo());

        /*
        if (labsResourceinfo.getFileList().size() > 0) {
            for (FileInfoVO fileInfoVO : labsResourceinfo.getFileList()) {
                String[] split = fileInfoVO.getName().split("\\.");
                LabsResourcefiles labsResourcefiles = new LabsResourcefiles();
                labsResourcefiles
                        .setFileNo(UuidUtils.shortUUID())
                        .setRescNo(labsResourceinfo.getRescNo())
                        .setFileName(fileInfoVO.getName())
                        .setFileUrl(fileInfoVO.getFileAddress())
                        .setFileSize(Integer.valueOf(fileInfoVO.getSize()))
                        .setFileExt(split[split.length - 1])
                        .setCheckState("1");
                labsResourcefiles.setCreateBy(loginUser.getUserNo());
                labsResourcefiles.setUpdateBy(loginUser.getUserNo());
                labsResourcefilesService.AddNewRecord(loginUser.getAppCode(), labsResourcefiles);
            }
        }
         */

        return toAjax(labsResourceinfoService.UpdateRecord(loginUser.getAppCode(), labsResourceinfo));
    }

    /**
     * 保存教学资源信息
     */
    @PreAuthorize("@ps.hasPermit('labsys:resourceinfo:save')")
    @Oplog(title = "教学资源信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsResourceinfo labsResourceinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsResourceinfoService.getRecordByNo(loginUser.getAppCode(), labsResourceinfo.getRescNo()))) {
            labsResourceinfo.setRescNo(UuidUtils.shortUUID());
            labsResourceinfo.setCreateBy(loginUser.getUserNo());
            labsResourceinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsResourceinfoService.AddNewRecord(loginUser.getAppCode(), labsResourceinfo));
        } else {
            labsResourceinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsResourceinfoService.UpdateRecord(loginUser.getAppCode(), labsResourceinfo));
        }
    }

    /**
     * 删除教学资源信息
     */
    @PreAuthorize("@ps.hasPermit('labsys:resourceinfo:delete')")
    @Oplog(title = "教学资源信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsResourceinfoService.SoftDeleteByNos(loginUser.getAppCode(), ids));
    }

    /**
     * 获取教学资源信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('labsys:resourceinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsResourceinfo labsResourceinfo = labsResourceinfoService.getRecordByNo(loginUser.getAppCode(), id);
        // todo 设置资源类型名称
        String recordNameByNo = labsResourceclassService.getRecordNameByNo(loginUser.getAppCode(), labsResourceinfo.getClassNo());
        labsResourceinfo.setClassNo(recordNameByNo);
        /*
        //查询资源对应的文件
        List<LabsResourcefiles> labsResourcefiles = labsResourcefilesService.getRecordsByClassNo(loginUser.getAppCode(), labsResourceinfo.getRescNo());
        if (labsResourcefiles.size() > 0) {
            List<FileInfoVO> fileList = new ArrayList<>();
            for (LabsResourcefiles labsResourcefile : labsResourcefiles) {
                FileInfoVO fileInfoVO = new FileInfoVO();
                fileInfoVO
                        .setStatus("done")
                        .setSize(String.valueOf(labsResourcefile.getFileSize()))
                        .setUrl(serverConfig.getFileUrlPrefix() + labsResourcefile.getFileUrl())
                        .setName(labsResourcefile.getFileName())
                        .setUid(labsResourcefile.getFileNo())
                        .setFileNo(labsResourcefile.getFileNo())
                        .setRescNo(labsResourceinfo.getRescNo())
                        .setType(labsResourcefile.getFileExt())
                        .setFileAddress(labsResourcefile.getFileUrl())
                ;
                fileList.add(fileInfoVO);
            }
            labsResourceinfo.setFileList(fileList);
        }
         */
        return AjaxResult.success(labsResourceinfo);
    }

    /**
     * 导出教学资源信息列表
     */
    @PreAuthorize("@ps.hasPermit('labsys:resourceinfo:export')")
    @Oplog(title = "教学资源信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest) {
        //String condition= pagerRequest.getCondition();
        String condition = "";
        Map maps = pRequest.getDataParams();
        if (maps.containsKey("name")) {
            condition += " name=" + maps.get("name");
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsResourceinfoService.getCountByCondition(loginUser.getAppCode(), condition);

        List<LabsResourceinfo> list = labsResourceinfoService.getRecordsByPaging(loginUser.getAppCode(), 1, count, condition, "id", "Asc");
        ExcelUtils<LabsResourceinfo> util = new ExcelUtils<LabsResourceinfo>(LabsResourceinfo.class);
        return util.exportExcel(list, "LabsResourceinfo");
    }


}
