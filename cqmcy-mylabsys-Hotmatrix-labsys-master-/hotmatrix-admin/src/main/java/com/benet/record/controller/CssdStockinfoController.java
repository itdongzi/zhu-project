package com.benet.record.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.date.DateTimeUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.record.domain.CssdStockflows;
import com.benet.record.domain.CssdStockitems;
import com.benet.record.service.ICssdStockflowsService;
import com.benet.record.service.ICssdStockitemsService;
import com.benet.record.vmodel.StockInfoVo;
import com.benet.system.vmodel.ItemObjectVo;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.benet.record.domain.CssdStockinfo;
import com.benet.record.service.ICssdStockinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 库存信息Controller
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Api(value = "record/stockinfo", tags = "库存信息控制器")
@RestController
@RequestMapping("/record/stockinfo")
public class CssdStockinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdStockinfoService cssdStockinfoService;
    @Autowired
    private ICssdStockflowsService cssdStockflowsService;

    @Autowired
    private ICssdStockitemsService cssdStockitemsService;

    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('record:stockinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询库存信息列表
     */
    @PreAuthorize("@ps.hasPermit('record:stockinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if (maps.containsKey("dataType")&&StringUtils.isNotEmpty(maps.get("dataType").toString())) {
            condition += "And data_type = '" + maps.get("dataType") + "' ";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdStockinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<CssdStockinfo> list = cssdStockinfoService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('record:stockinfo:list')")
    @GetMapping(value = "/tree}")
    public TableDataInfo tree(@RequestParam("equipType") String equipType)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

        String condition=" equip_type='"+equipType+"'";
        int count = cssdStockinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<ItemObjectVo> list = buildItemTree(loginUser.getAppCode(),equipType);
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode,String equipType) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        String condition=" equip_type='"+equipType+"'";
        List<CssdStockinfo> infoList = cssdStockinfoService.getRecordsByPaging(appCode,1,100,condition,"id","asc");

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CssdStockinfo info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getStockNo());
                item.setKey(info.getStockNo());
                item.setTitle(info.getEquipNo()+" "+info.getProduceNumber()+" "+info.getEquipNum()+" "+info.getStoreNo());
                item.setValue(info.getStockNo());
                item.setChildren(null);

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增库存信息
     */
    @PreAuthorize("@ps.hasPermit('record:stockinfo:addnew')")
    @Oplog(title = "库存信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CssdStockinfo cssdStockinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdStockinfo.setStockNo(UuidUtils.shortUUID());
        cssdStockinfo.setCreateBy(loginUser.getUserNo());
        cssdStockinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(cssdStockinfoService.AddNewRecord(loginUser.getAppCode(),cssdStockinfo));
    }

    /**
     * 编辑库存信息
     */
    @PreAuthorize("@ps.hasPermit('record:stockinfo:update')")
    @Oplog(title = "库存信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CssdStockinfo cssdStockinfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdStockinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(cssdStockinfoService.UpdateRecord(loginUser.getAppCode(),cssdStockinfo));
        }

    /**
     * 保存库存信息
     */
    @PreAuthorize("@ps.hasPermit('record:stockinfo:save')")
    @Oplog(title = "库存信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CssdStockinfo cssdStockinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cssdStockinfoService.getRecordByNo(loginUser.getAppCode(),cssdStockinfo.getStockNo()))) {
            cssdStockinfo.setStockNo(UuidUtils.shortUUID());
            cssdStockinfo.setCreateBy(loginUser.getUserNo());
            cssdStockinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(cssdStockinfoService.AddNewRecord(loginUser.getAppCode(),cssdStockinfo));
        } else {
            cssdStockinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(cssdStockinfoService.UpdateRecord(loginUser.getAppCode(),cssdStockinfo));
        }
    }

    /**
     * 删除库存信息
     */
    @PreAuthorize("@ps.hasPermit('record:stockinfo:delete')")
    @Oplog(title = "库存信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cssdStockinfoService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取库存信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('record:stockinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdStockinfoService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取库存信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('record:stockinfo:detail')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

        CssdStockinfo info=cssdStockinfoService.getRecordByNo(loginUser.getAppCode(),id);
        StockInfoVo infoVo=new StockInfoVo();
        infoVo.setStockNo(info.getStockNo());
        infoVo.setStoreNo(info.getStoreNo());
        infoVo.setRuserNo("");
        infoVo.setEquipNo(info.getEquipNo());
        infoVo.setEquipNum(1);
        infoVo.setStockNum(info.getEquipNum());
        infoVo.setProduceNumber(info.getProduceNumber());
        infoVo.setProduceDate(info.getProduceDate());
        infoVo.setExpireDate(info.getExpireDate());
        infoVo.setCheckState(info.getCheckState());
        infoVo.setComments(info.getComments());

        return AjaxResult.success(infoVo);
    }

    /**
     * 导出库存信息列表
     */
    @PreAuthorize("@ps.hasPermit('record:stockinfo:export')")
    @Oplog(title = "库存信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("equipType")) {
            condition += " equip_type=" + maps.get("equipType");
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdStockinfoService.getCountByCondition(loginUser.getAppCode(),condition);

        List<CssdStockinfo> list = cssdStockinfoService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<CssdStockinfo> util = new ExcelUtils<CssdStockinfo>(CssdStockinfo.class);
        return util.exportExcel(list, "CssdStockinfo");
    }

    /**
     * 入库单据审核操作详细信息
     */
    @PreAuthorize("@ps.hasPermit('record:stockinfo:inbound')")
    @Oplog(title = "入库信息", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/inbound")
    public AjaxResult inbound(@RequestBody String sflowNo)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        CssdStockflows stockflows=cssdStockflowsService.getRecordByNo(loginUser.getAppCode(),sflowNo);

        List<CssdStockitems> stockitemsList=cssdStockitemsService.getRecordsByClassNo(loginUser.getAppCode(),stockflows.getSflowNo());

        CssdStockinfo stockinfo=null;
        List<CssdStockinfo> stockinfoList=null;
        String sCondition="";

        if(stockitemsList!=null&&stockitemsList.size()>0){
            for (CssdStockitems stockitem:stockitemsList) {

                sCondition="check_state='1' And store_no='"+stockflows.getStoreNo()+"' And equip_no='"+stockitem.getEquipNo()+"' And data_type='"+stockitem.getDataType()+"'";
                stockinfoList=cssdStockinfoService.getRecordsByPaging(loginUser.getAppCode(),1,10,sCondition,"id","desc");
                if(stockinfoList!=null&&stockinfoList.size()>0){
                    stockinfo=stockinfoList.get(0);
                    stockinfo.setEquipNum(stockinfo.getEquipNum()+stockitem.getEquipNum());
                    stockinfo.setUpdateBy(loginUser.getUserNo());

                    cssdStockinfoService.UpdateRecord(loginUser.getAppCode(),stockinfo);
                }else{
                    stockinfo=new CssdStockinfo();
                    stockinfo.setStockNo(UuidUtils.shortUUID());
                    stockinfo.setStoreNo(stockflows.getStoreNo());
                    stockinfo.setEquipNo(stockitem.getEquipNo());
                    stockinfo.setDataType(stockitem.getDataType());
                    stockinfo.setEquipNum(stockitem.getEquipNum());
                    stockinfo.setProduceNumber(stockitem.getProduceNumber());
                    stockinfo.setProduceDate(DateTimeUtils.parse(stockitem.getProduceDate()));
                    stockinfo.setExpireDate(DateTimeUtils.parse(stockitem.getExpireDate()));
                    stockinfo.setCheckState("1");
                    stockinfo.setBranchNo("");
                    stockinfo.setCreateBy(loginUser.getUserNo());
                    stockinfo.setUpdateBy(loginUser.getUserNo());
                    stockinfo.setComments(stockitem.getComments());

                    cssdStockinfoService.AddNewRecord(loginUser.getAppCode(),stockinfo);
                }
            }

            stockflows.setCheckState("2"); //状态，1未审核、2已审核、3已转采购|销售
            stockflows.setUpdateBy(loginUser.getUserNo());

            cssdStockflowsService.UpdateRecord(loginUser.getAppCode(),stockflows);
        }

        return AjaxResult.success("操作成功");
    }

    /**
     * 出库单据审核操作详细信息
     */
    @PreAuthorize("@ps.hasPermit('record:stockinfo:outbound')")
    @Oplog(title = "出库信息", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/outbound")
    public AjaxResult outbound(@RequestBody String sflowNo)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        CssdStockflows stockflows=cssdStockflowsService.getRecordByNo(loginUser.getAppCode(),sflowNo);

        List<CssdStockitems> stockitemsList=cssdStockitemsService.getRecordsByClassNo(loginUser.getAppCode(),stockflows.getSflowNo());

        CssdStockinfo stockinfo=null;
        List<CssdStockinfo> stockinfoList=null;
        String sCondition="";

        if(stockitemsList!=null&&stockitemsList.size()>0){
            for (CssdStockitems stockitem:stockitemsList) {

                sCondition="check_state='1' And store_no='"+stockflows.getStoreNo()+"' And equip_no='"+stockitem.getEquipNo()+"' And data_type='"+stockitem.getDataType()+"'";
                stockinfoList=cssdStockinfoService.getRecordsByPaging(loginUser.getAppCode(),1,10,"","id","desc");
                if(stockinfoList!=null&&stockinfoList.size()>0){
                    stockinfo=stockinfoList.get(0);
                    stockinfo.setEquipNum(stockinfo.getEquipNum()-stockitem.getEquipNum());
                    stockinfo.setUpdateBy(loginUser.getUserNo());

                    cssdStockinfoService.UpdateRecord(loginUser.getAppCode(),stockinfo);
                }
            }

            stockflows.setCheckState("2"); //状态，1未审核、2已审核、3已转采购|销售
            stockflows.setUpdateBy(loginUser.getUserNo());

            cssdStockflowsService.UpdateRecord(loginUser.getAppCode(),stockflows);
        }

        return AjaxResult.success("操作成功");
    }

}
