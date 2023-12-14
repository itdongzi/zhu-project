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
import com.benet.record.domain.CssdEquipments;
import com.benet.record.service.ICssdEquipmentsService;
import com.benet.record.vmodel.StockItemVo;
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
import com.benet.record.domain.CssdStockitems;
import com.benet.record.service.ICssdStockitemsService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 出入库单明细Controller
 * 
 * @author yoxking
 * @date 2021-03-20
 */
@Api(value = "record/stockitems", tags = "出入库单明细控制器")
@RestController
@RequestMapping("/record/stockitems")
public class CssdStockitemsController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdEquipmentsService cssdEquipmentsService;

    @Autowired
    private ICssdStockitemsService cssdStockitemsService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('record:stockitems:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询出入库单明细列表
     */
    @PreAuthorize("@ps.hasPermit('record:stockitems:list')")
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
        int count = cssdStockitemsService.getCountByCondition(loginUser.getAppCode(),condition);
        List<CssdStockitems> list = cssdStockitemsService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增出入库单明细
     */
    @PreAuthorize("@ps.hasPermit('record:stockitems:addnew')")
    @Oplog(title = "出入库单明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody List<StockItemVo> stockitemsList) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

        CssdStockitems cssdStockitem=null;

        if(stockitemsList!=null&&stockitemsList.size()>0){
            for (StockItemVo stockitem:stockitemsList){
                cssdStockitem=new CssdStockitems();
                cssdStockitem.setSitemNo(UuidUtils.shortUUID());
                cssdStockitem.setSflowNo(stockitem.getSflowNo());
                cssdStockitem.setEquipNo(stockitem.getEquipNo());
                cssdStockitem.setDataType("1");
                cssdStockitem.setEquipNum(stockitem.getEquipNum());
                cssdStockitem.setUnitPrice(stockitem.getUnitPrice());
                cssdStockitem.setTaxPrice(stockitem.getTaxPrice());
                cssdStockitem.setAllPrice(stockitem.getAllPrice());
                cssdStockitem.setProduceNumber(stockitem.getProduceNumber());
                cssdStockitem.setProduceDate(DateTimeUtils.formatNow("yyyy-MM-dd HH:mm:ss"));
                cssdStockitem.setExpireDate(DateTimeUtils.formatNow("yyyy-MM-dd HH:mm:ss"));
                cssdStockitem.setOtherField1("");
                cssdStockitem.setOtherField2("");
                cssdStockitem.setOtherField3("");
                cssdStockitem.setOtherField4("");
                cssdStockitem.setOtherField5("");
                cssdStockitem.setBranchNo("");
                cssdStockitem.setCreateBy(loginUser.getUserNo());
                cssdStockitem.setUpdateBy(loginUser.getUserNo());
                cssdStockitem.setComments(stockitem.getComments());

                cssdStockitemsService.AddNewRecord(loginUser.getAppCode(),cssdStockitem);
            }
        }

        return success();
    }

    /**
     * 编辑出入库单明细
     */
    @PreAuthorize("@ps.hasPermit('record:stockitems:update')")
    @Oplog(title = "出入库单明细", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody List<StockItemVo> stockitemsList) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

        CssdStockitems cssdStockitem = null;

        if (stockitemsList != null && stockitemsList.size() > 0) {
            //删除原来的明细列表
            cssdStockitemsService.HardDeleteByCondition(loginUser.getAppCode(), "sflow_no='"+stockitemsList.get(0).getSflowNo()+"'");
            for (StockItemVo stockitem : stockitemsList) {
                cssdStockitem=new CssdStockitems();
                cssdStockitem.setSitemNo(UuidUtils.shortUUID());
                cssdStockitem.setSflowNo(stockitem.getSflowNo());
                cssdStockitem.setEquipNo(stockitem.getEquipNo());
                cssdStockitem.setDataType("1");
                cssdStockitem.setEquipNum(stockitem.getEquipNum());
                cssdStockitem.setUnitPrice(stockitem.getUnitPrice());
                cssdStockitem.setTaxPrice(stockitem.getTaxPrice());
                cssdStockitem.setAllPrice(stockitem.getAllPrice());
                cssdStockitem.setProduceNumber(stockitem.getProduceNumber());
                cssdStockitem.setProduceDate(DateTimeUtils.formatNow("yyyy-MM-dd HH:mm:ss"));
                cssdStockitem.setExpireDate(DateTimeUtils.formatNow("yyyy-MM-dd HH:mm:ss"));
                cssdStockitem.setOtherField1("");
                cssdStockitem.setOtherField2("");
                cssdStockitem.setOtherField3("");
                cssdStockitem.setOtherField4("");
                cssdStockitem.setOtherField5("");
                cssdStockitem.setBranchNo("");
                cssdStockitem.setCreateBy(loginUser.getUserNo());
                cssdStockitem.setUpdateBy(loginUser.getUserNo());
                cssdStockitem.setComments(stockitem.getComments());

                cssdStockitemsService.AddNewRecord(loginUser.getAppCode(), cssdStockitem);
            }
        }

        return success();
    }

    /**
     * 保存出入库单明细
     */
    @PreAuthorize("@ps.hasPermit('record:stockitems:save')")
    @Oplog(title = "出入库单明细", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CssdStockitems cssdStockitems) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cssdStockitemsService.getRecordByNo(loginUser.getAppCode(),cssdStockitems.getSitemNo()))) {
            cssdStockitems.setSitemNo(UuidUtils.shortUUID());
            cssdStockitems.setCreateBy(loginUser.getUserNo());
            cssdStockitems.setUpdateBy(loginUser.getUserNo());
            return toAjax(cssdStockitemsService.AddNewRecord(loginUser.getAppCode(),cssdStockitems));
        } else {
            cssdStockitems.setUpdateBy(loginUser.getUserNo());
            return toAjax(cssdStockitemsService.UpdateRecord(loginUser.getAppCode(),cssdStockitems));
        }
    }

    /**
     * 删除出入库单明细
     */
    @PreAuthorize("@ps.hasPermit('record:stockitems:delete')")
    @Oplog(title = "出入库单明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cssdStockitemsService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取出入库单明细详细信息
     */
    @PreAuthorize("@ps.hasPermit('record:stockitems:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdStockitemsService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 导出出入库单明细列表
     */
    @PreAuthorize("@ps.hasPermit('record:stockitems:export')")
    @Oplog(title = "出入库单明细", businessType = BusinessType.EXPORT)
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
        int count = cssdStockitemsService.getCountByCondition(loginUser.getAppCode(),condition);

        List<CssdStockitems> list = cssdStockitemsService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<CssdStockitems> util = new ExcelUtils<CssdStockitems>(CssdStockitems.class);
        return util.exportExcel(list, "CssdStockitems");
    }

    /**
     * 按出入库单号查询订单明细列表
     */
    @PreAuthorize("@ps.hasPermit('record:stockitems:list')")
    @GetMapping(value = "/listStockitemsBysflowNo/{id}")
    public TableDataInfo listStockitemsBysflowNo(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String appCode=loginUser.getAppCode();

        List<StockItemVo> itemList=new ArrayList<>();
        StockItemVo item=null;
        CssdEquipments equip=null;
        List<CssdStockitems> infoList=cssdStockitemsService.getRecordsByClassNo(appCode,id);

        if(infoList!=null&&infoList.size()>0){
            for(CssdStockitems info:infoList){
                equip=cssdEquipmentsService.getRecordByNo(appCode,info.getEquipNo());
                if(equip!=null){
                    item=new StockItemVo();
                    item.setSflowNo(info.getSflowNo());
                    item.setEquipNo(info.getEquipNo());
                    item.setEquipName(equip.getEquipCname());
                    item.setEquipSpec(equip.getEquipSpec());
                    item.setEquipModel(equip.getEquipModel());
                    item.setUnitName(equip.getUnitNo());
                    item.setProduceNumber(info.getProduceNumber());
                    item.setEquipNum(info.getEquipNum());
                    item.setStoreNum(1);
                    item.setUnitPrice(info.getUnitPrice());
                    item.setTaxPrice(0);
                    item.setAllPrice(info.getAllPrice());
                    item.setComments(info.getComments());

                    itemList.add(item);
                }
            }
        }
        return getDataTable(itemList, itemList.size());
    }
}
