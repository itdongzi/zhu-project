package com.benet.labsys.controller;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.labsys.vmodel.ScheduleEventVO;
import com.benet.labsys.domain.LabsSectionsinfo;
import com.benet.labsys.domain.LabsSemesterinfo;
import com.benet.labsys.service.ILabsSectionsinfoService;
import com.benet.labsys.service.ILabsSemesterinfoService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
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
import com.benet.labsys.domain.LabsRoomschedule;
import com.benet.labsys.service.ILabsRoomscheduleService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 实验室排课Controller
 *
 * @author yoxking
 * @date 2022-08-05
 */
@Api(value = "labsys/roomschedule", tags = "实验室排课控制器")
@RestController
@RequestMapping("/labsys/roomschedule")
@Slf4j
public class LabsRoomscheduleController extends BaseController {
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsRoomscheduleService labsRoomscheduleService;

    @Autowired
    private ILabsSectionsinfoService labsSectionsinfoService;

    @Autowired
    private ILabsSemesterinfoService labsSemesterinfoService;

    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('labsys:roomschedule:index')")
    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 查询实验室排课列表
     */
    @PreAuthorize("@ps.hasPermit('labsys:roomschedule:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest) {
        //String condition= pagerRequest.getCondition();
        String condition = "";
        Map maps = pRequest.getDataParams();
        if (maps.containsKey("name")) {
            condition += " name=" + maps.get("name");
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsRoomscheduleService.getCountByCondition(loginUser.getAppCode(), condition);
        List<LabsRoomschedule> list = labsRoomscheduleService.getRecordsByPaging(loginUser.getAppCode(), pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增实验室排课
     */
    @PreAuthorize("@ps.hasPermit('labsys:roomschedule:addnew')")
    @Oplog(title = "实验室排课", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsRoomschedule labsRoomschedule) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsRoomschedule.setSchdNo(UuidUtils.shortUUID());
        labsRoomschedule.setCreateBy(loginUser.getUserNo());
        labsRoomschedule.setUpdateBy(loginUser.getUserNo());
        //todo 判断是否添加了重复的课程
        String roomNo = labsRoomschedule.getRoomNo();//实验室编号
        String semeNo = labsRoomschedule.getSemeNo();//学期编号
        String schdValue = labsRoomschedule.getSchdValue();//学期编号
        String[] kkzcArray = labsRoomschedule.getKkzc().split(",");
        String condition = "";
        for (String kkzc : kkzcArray) {
            condition = "room_no='" + roomNo + "' and " + "seme_no='" + semeNo + "' and " + "schd_value='" + schdValue + "' and " + "KKZC like '%" + kkzc + "%'";
            log.info("查询条件 - {}", condition);
            int countByCondition = labsRoomscheduleService.getCountByCondition(loginUser.getAppCode(), condition);
            log.info("查询结果 - {}", countByCondition);
            if (countByCondition == 1) {
                return AjaxResult.error("该时间段已存在课程，请核实后在操作！");
            }
        }

        return toAjax(labsRoomscheduleService.AddNewRecord(loginUser.getAppCode(), labsRoomschedule));
    }

    /**
     * 编辑实验室排课
     */
    @PreAuthorize("@ps.hasPermit('labsys:roomschedule:update')")
    @Oplog(title = "实验室排课", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsRoomschedule labsRoomschedule) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsRoomschedule.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsRoomscheduleService.UpdateRecord(loginUser.getAppCode(), labsRoomschedule));
    }

    /**
     * 保存实验室排课
     */
    @PreAuthorize("@ps.hasPermit('labsys:roomschedule:save')")
    @Oplog(title = "实验室排课", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsRoomschedule labsRoomschedule) {
        log.info("保存实验室排课");
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsRoomscheduleService.getRecordByNo(loginUser.getAppCode(), labsRoomschedule.getSchdNo()))) {
            labsRoomschedule.setSchdNo(UuidUtils.shortUUID());
            labsRoomschedule.setCreateBy(loginUser.getUserNo());
            labsRoomschedule.setUpdateBy(loginUser.getUserNo());
            //todo 判断是否添加了重复的课程
            String roomNo = labsRoomschedule.getRoomNo();//实验室编号
            String semeNo = labsRoomschedule.getSemeNo();//学期编号
            String schdValue = labsRoomschedule.getSchdValue();//学期编号
            String[] kkzcArray = labsRoomschedule.getKkzc().split(",");
            String condition = "";
            for (String kkzc : kkzcArray) {
                condition = "room_no='" + roomNo + "' and " + "seme_no='" + semeNo + "' and " + "schd_value='" + schdValue + "' and " + "KKZC like '%" + kkzc + "%'";
                log.info("查询条件 - {}", condition);
                int countByCondition = labsRoomscheduleService.getCountByCondition(loginUser.getAppCode(), condition);
                log.info("查询结果 - {}", countByCondition);
                if (countByCondition == 1) {
                    return AjaxResult.error("该时间段已存在课程，请核实后在操作！");
                }
            }
            return toAjax(labsRoomscheduleService.AddNewRecord(loginUser.getAppCode(), labsRoomschedule));
        } else {
            labsRoomschedule.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsRoomscheduleService.UpdateRecord(loginUser.getAppCode(), labsRoomschedule));
        }
    }

    /**
     * 删除实验室排课
     */
    @PreAuthorize("@ps.hasPermit('labsys:roomschedule:delete')")
    @Oplog(title = "实验室排课", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsRoomscheduleService.SoftDeleteByNos(loginUser.getAppCode(), ids));
    }

    /**
     * 获取实验室排课详细信息
     */
    @PreAuthorize("@ps.hasPermit('labsys:roomschedule:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsRoomschedule labsRoomschedule = labsRoomscheduleService.getRecordByNo(loginUser.getAppCode(), id);
        return AjaxResult.success(labsRoomschedule);
    }

    /**
     * 导出实验室排课列表
     */
    @PreAuthorize("@ps.hasPermit('labsys:roomschedule:export')")
    @Oplog(title = "实验室排课", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest) {
        //String condition= pagerRequest.getCondition();
        String condition = "";
        Map maps = pRequest.getDataParams();
        if (maps.containsKey("name")) {
            condition += " name=" + maps.get("name");
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsRoomscheduleService.getCountByCondition(loginUser.getAppCode(), condition);

        List<LabsRoomschedule> list = labsRoomscheduleService.getRecordsByPaging(loginUser.getAppCode(), 1, count, condition, "id", "Asc");
        ExcelUtils<LabsRoomschedule> util = new ExcelUtils<LabsRoomschedule>(LabsRoomschedule.class);
        return util.exportExcel(list, "LabsRoomschedule");
    }

    /**
     * 渲染课表的数据
     *
     * @param roomNo
     * @param semeNo
     * @return
     */
    @GetMapping("roomscheduleList")
    public AjaxResult roomscheduleList(String roomNo, String semeNo) {
        if (StringUtils.isEmpty(roomNo)) {
            return AjaxResult.error("实验室不能为空");
        }
        if (StringUtils.isEmpty(semeNo)) {
            return AjaxResult.error("学年学期不能为空");
        }

        String condition = " room_no=" + roomNo + " and seme_no=" + semeNo;

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsSemesterinfo labsSemesterinfo = labsSemesterinfoService.getRecordByNo(loginUser.getAppCode(), semeNo);

        LocalDateTime startDate = labsSemesterinfo.getStartDate();//学期开始时间
        long l = startDate.toInstant(ZoneOffset.of("+8")).toEpochMilli() / 1000;
        log.info("学期开始时间:{}", l);

        log.info("学期开始时间是周几,{}", startDate.getDayOfWeek());
        String dayOfWeek = "";
        if (startDate.getDayOfWeek().toString().equals("MONDAY")) {
            dayOfWeek = "1";
        } else if (startDate.getDayOfWeek().toString().equals("TUESDAY")) {
            dayOfWeek = "2";
        } else if (startDate.getDayOfWeek().toString().equals("WEDNESDAY")) {
            dayOfWeek = "3";
        } else if (startDate.getDayOfWeek().toString().equals("THURSDAY")) {
            dayOfWeek = "4";
        } else if (startDate.getDayOfWeek().toString().equals("FRIDAY")) {
            dayOfWeek = "5";
        } else if (startDate.getDayOfWeek().toString().equals("SATURDAY")) {
            dayOfWeek = "6";
        } else {
            dayOfWeek = "7";
        }
        log.info("学期开始时间是周几,{}", dayOfWeek);
        List<LabsRoomschedule> labsRoomscheduleListByCondition = null;
        if (labsRoomscheduleListByCondition.size() > 0) {
            List<ScheduleEventVO> list = new ArrayList<>();
            for (LabsRoomschedule labsRoomschedule : labsRoomscheduleListByCondition) {
                String[] kkzcArray = labsRoomschedule.getKkzc().split(",");
                String[] schdValueArray = labsRoomschedule.getSchdValue().split("\\+");
                LabsSectionsinfo labsSectionsinfo = labsSectionsinfoService.getRecordByNo(loginUser.getAppCode(), schdValueArray[1]);
                for (String kkzc : kkzcArray) {
                    log.info("第几周,{}", kkzc);
                    log.info("周几,{}", schdValueArray[0]);
                    log.info("上课时间:{}", labsSectionsinfo.getStartTime());
                    log.info("下课时间:{}", labsSectionsinfo.getEnditTime());
                    long timestamp = 0;
                    //一周
                    if (Integer.valueOf(kkzc) == 1) {
                        log.info("一周");
                        //计算第一周相差的时间
                        long i = (Integer.valueOf(schdValueArray[0]) - Integer.valueOf(dayOfWeek)) * 24 * 3600;
                        timestamp = l + i;
                    }
                    if (Integer.valueOf(kkzc) == 2) {
                        log.info("两周");
                        //第一周的时间
                        long i1 = (7 - Integer.valueOf(dayOfWeek)) * 24 * 3600;
                        //第二周的时间
                        long i2 = Integer.valueOf(schdValueArray[0]) * 24 * 3600;
                        timestamp = l + i1 + i2;
                    } else {
                        //第一周时间
                        long i1 = (7 - Integer.valueOf(dayOfWeek)) * 24 * 3600;
                        //中间时间
                        long i2 = (Integer.valueOf(kkzc) - 2) * 7 * 24 * 3600;
                        //最后一周一时间
                        long i3 = Integer.valueOf(schdValueArray[0]) * 24 * 3600;
                        timestamp = l + i1 + i2 + i3;
                    }
                    String format = LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.ofHours(8)).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));//2020-01-10 07:53:40
                    log.info("转换后的上课时间:{}", format + " " + labsSectionsinfo.getStartTime());
                    log.info("转换后的下课时间:{}", format + " " + labsSectionsinfo.getEnditTime());
                    ScheduleEventVO scheduleEventVO = new ScheduleEventVO();
                    scheduleEventVO
                            .setId(labsRoomschedule.getSchdNo())
                            .setTitle(labsRoomschedule.getKcmc())
                            .setStart(format + " " + labsSectionsinfo.getStartTime())
                            .setEnd(format + " " + labsSectionsinfo.getEnditTime())
                            .setWeekNumber(kkzc)
                    ;
                    list.add(scheduleEventVO);
                }
            }
            return AjaxResult.success(list);
        }
        return AjaxResult.success(null);
    }

    @GetMapping("delRoomSchedule")
    public AjaxResult delRoomSchedule(String schdNo, String weekNumber) {
        System.out.println("schdNo " + schdNo);
        System.out.println("weekNumber " + weekNumber);
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        //如果weekNumber不为空则更新，为空就删除整条数据
        if (!StringUtils.isEmpty(weekNumber)) {
            return AjaxResult.success(labsRoomscheduleService.SoftDeleteByNo(loginUser.getAppCode(), schdNo));
        }
        LabsRoomschedule labsRoomschedule = labsRoomscheduleService.getRecordByNo(loginUser.getAppCode(), schdNo);
        String[] kkzcArray = labsRoomschedule.getKkzc().split("\\+");
        String newKkzc = "";
        for (String kkzc : kkzcArray) {
            if (!kkzc.equals(weekNumber)) {
                newKkzc = newKkzc + kkzc + ",";
            }
        }
        String s = StringUtils.removeSuffix(newKkzc, ",");
        labsRoomschedule.setKkzc(s);
        return AjaxResult.success(labsRoomscheduleService.UpdateRecord(loginUser.getAppCode(), labsRoomschedule));
    }

}
