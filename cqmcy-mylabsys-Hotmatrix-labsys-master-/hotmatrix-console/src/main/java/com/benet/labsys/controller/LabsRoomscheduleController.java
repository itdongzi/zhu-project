package com.benet.labsys.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.benet.common.core.pager.PageRequest;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.labsys.domain.CourseSchedule;
import com.benet.labsys.domain.LabsAreainfo;
import com.benet.labsys.domain.LabsRoominfo;
import com.benet.labsys.domain.LabsSectionsinfo;
import com.benet.labsys.domain.LabsSemesterinfo;
import com.benet.labsys.service.impl.LabsAreainfoServiceImpl;
import com.benet.labsys.service.impl.LabsRoominfoServiceImpl;
import com.benet.labsys.service.impl.LabsSemesterinfoServiceImpl;
import com.benet.system.constant.ClassNumber;
import com.benet.system.constant.Weekday;
import io.swagger.annotations.Api;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.benet.labsys.domain.LabsRoomschedule;
import com.benet.labsys.service.ILabsRoomscheduleService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 实验室排课Controller
 *
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Api(value = "labsys/roomschedule", tags = "实验室排课控制器")
@RestController
@RequestMapping("/labsys/roomschedule")
public class LabsRoomscheduleController extends BaseController {
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsRoomscheduleService labsRoomscheduleService;
    @Autowired
    private LabsRoominfoServiceImpl labsRoominfoService;
    @Autowired
    private LabsAreainfoServiceImpl labsAreainfoService;
    @Autowired
    private LabsSemesterinfoServiceImpl labsSemesterinfoService;

    /**
     * 首页
     */
    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @PostMapping("/upload")
    public AjaxResult uploadFile(@RequestBody @RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        try {
            String name = file.getOriginalFilename();
            if (StringUtils.isBlank(name)) {
                return AjaxResult.error("文件解析失败");
            }
            String fileExtension = name.substring(name.lastIndexOf("."));
            if (!fileExtension.equals(".xls") && !fileExtension.equals(".xlsx")) {
                return AjaxResult.error("请上传扩展名为xls/xlsx的文件");
            }

            String msg = this.importData(file.getInputStream(), name, request);
            if (msg.contains("错误")) {
                return AjaxResult.error(msg);
            }
            return AjaxResult.success(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("导入课表失败！");
        }
    }

    public String importData(InputStream inputStream, String fileName, HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<LabsRoomschedule> list = parseToList(inputStream, fileName);
        if (list.isEmpty()) {
            return "解析出0条数据，请检查上传的文件。";
        }
        int count = 0;
        for (LabsRoomschedule labsRoomschedule : list) {
            labsRoomschedule.setSchdNo(UuidUtils.shortUUID());
            labsRoomschedule.setCreateBy(loginUser.getUserNo());
            labsRoomschedule.setUpdateBy(loginUser.getUserNo());
            int i = labsRoomscheduleService.AddNewRecord(loginUser.getAppCode(), labsRoomschedule);
            if (i == 1) {
                count++;
            }
        }
        return "导入excel数据成功，共导入" + count + "条数据";
    }

    private List<LabsRoomschedule> parseToList(InputStream inputStream, String fileName) {
        List<LabsRoomschedule> list = new ArrayList<>();
        Workbook workbook = null;
        try {
            if (fileName.endsWith(".xls")) {
                workbook = new HSSFWorkbook(inputStream);
            } else if (fileName.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            }
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == 0) {
                    continue;
                }
                Iterator<Cell> cellIterator = row.cellIterator();
                LabsRoomschedule labsRoomschedule = new LabsRoomschedule();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    cell.setCellType(CellType.STRING);
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex) {
                        case 0:
                            labsRoomschedule.setOrderNo(cell.getStringCellValue());
                            break;
                        case 1:
                            labsRoomschedule.setRoomNo(cell.getStringCellValue());
                            break;
                        case 2:
                            labsRoomschedule.setSemeNo(cell.getStringCellValue());
                            break;
                        case 3:
                            labsRoomschedule.setSchdType(cell.getStringCellValue());
                            break;
                        case 4:
                            labsRoomschedule.setSchdValue(cell.getStringCellValue());
                            break;
                        case 5:
                            labsRoomschedule.setUsesValue(cell.getStringCellValue());
                            break;
                        case 6:
                            labsRoomschedule.setXnxqbh(cell.getStringCellValue());
                            break;
                        case 7:
                            labsRoomschedule.setKkzc(cell.getStringCellValue());
                            break;
                        case 8:
                            labsRoomschedule.setDszbz(cell.getStringCellValue());
                            break;
                        case 9:
                            labsRoomschedule.setJxjcbh(cell.getStringCellValue());
                            break;
                        case 10:
                            labsRoomschedule.setDwmc(cell.getStringCellValue());
                            break;
                        case 11:
                            labsRoomschedule.setJzgh(cell.getStringCellValue());
                            break;
                        case 12:
                            labsRoomschedule.setXm(cell.getStringCellValue());
                            break;
                        case 13:
                            labsRoomschedule.setKcdm(cell.getStringCellValue());
                            break;
                        case 14:
                            labsRoomschedule.setKcmc(cell.getStringCellValue());
                            break;
                        case 15:
                            labsRoomschedule.setKkhbxxbh(cell.getStringCellValue());
                            break;
                        case 16:
                            labsRoomschedule.setHbmc(cell.getStringCellValue());
                            break;
                        case 17:
                            labsRoomschedule.setBjdm(cell.getStringCellValue());
                            break;
                        case 18:
                            labsRoomschedule.setBjmc(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                list.add(labsRoomschedule);
            }
            workbook.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @GetMapping(value = "/getxnxqbhlist")
    public AjaxResult getXnxqbhList() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<LabsSemesterinfo> labsSemesterinfoList = labsSemesterinfoService.getAllRecords(loginUser.getAppCode());
        List<Map<String, String>> xnxqbhList = new ArrayList<>();
        for (LabsSemesterinfo labsSemesterinfo : labsSemesterinfoList) {
            List<LabsSemesterinfo> itemList = labsSemesterinfoList.stream()
                    .filter(seme -> seme.getSemeName() == labsSemesterinfo.getSemeName())
                    .collect(Collectors.toList());
            String xnxq = labsSemesterinfo.getSemeName().substring(0, 9);
            for (LabsSemesterinfo semesterinfo : itemList) {
                Map<String, String> map = new HashMap<>();
                map.put("semeNo", labsSemesterinfo.getSemeNo());
                map.put("xnxqbh", xnxq + "-" + semesterinfo.getSemeType());
                xnxqbhList.add(map);
            }
        }
        return AjaxResult.success(xnxqbhList);
    }

    @PostMapping(value = "/getkkzclist")
    public AjaxResult getKkzcList(@RequestBody PageRequest pRequest) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<String> list = null;

        Map maps = pRequest.getDataParams();
        if (maps == null || maps.isEmpty()) {
            return AjaxResult.success(list);
        }

        if (!maps.containsKey("semeNo") || StringUtils.isEmpty(maps.get("semeNo").toString())) {
            return AjaxResult.success(list);
        }

        LabsSemesterinfo labsSemesterinfo = labsSemesterinfoService.getRecordByNo(loginUser.getAppCode(), maps.get("semeNo").toString());

        if (null != labsSemesterinfo) {
            int startWeek = labsSemesterinfo.getStartWeek();
            int totalWeek = labsSemesterinfo.getSemeWeeks();
            int[] array = new int[totalWeek];
            for (int i = 0; i < array.length; i++) {
                array[i] = startWeek + i;
            }
            return AjaxResult.success(array);
        } else {
            return AjaxResult.success(null);
        }
    }

    /**
     * 查询实验室排课列表
     */
    @PostMapping(value = "/list")
    public AjaxResult list(@RequestBody PageRequest pRequest) {
        String condition = "";
        Map maps = pRequest.getDataParams();

        if (!maps.containsKey("roomNo") || StringUtils.isEmpty(maps.get("roomNo").toString())) {
            return AjaxResult.error("请选择实训室名称");
        } else {
            condition += " And room_no = '" + maps.get("roomNo").toString() + "'";
        }

        if (!maps.containsKey("semeNo") || StringUtils.isEmpty(maps.get("semeNo").toString())) {
            return AjaxResult.error("请选择学年学期");
        } else {
            condition += " And seme_no = '" + maps.get("semeNo").toString() + "'";
        }

        if (!maps.containsKey("kkzc") || StringUtils.isEmpty(maps.get("kkzc").toString())) {
            return AjaxResult.error("请选择开课周次");
        } else {
            condition += " And kkzc like '%," + maps.get("kkzc").toString() + ",%'";
        }

        if (StringUtils.isNotEmpty(condition)) {
            condition = condition.substring(4);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

        List<LabsRoomschedule> list = labsRoomscheduleService.getRecordsByPaging(loginUser.getAppCode(), pRequest.getPageIndex(), Integer.MAX_VALUE, condition, "id", "Asc");

        CourseSchedule courseSchedule = new CourseSchedule();
        for (int day = 0; day < 8; day++) {
            for (int time = 0; time < 6; time++) {
                courseSchedule.setSchedule(time, day, null);
            }
        }
        for (LabsRoomschedule labsRoomschedule : list) {
            String schdValue = labsRoomschedule.getSchdValue();
            String week = schdValue.substring(0, schdValue.indexOf("+"));
            Weekday weekday = Weekday.fromDescription(week);
            int day = weekday.ordinal() + 1;
            String str1 = schdValue.substring(0, schdValue.indexOf("+"));
            String jc = schdValue.substring(str1.length() + 1);
            String[] jcArray = jc.split(",");
            for (String jcStr: jcArray) {
                ClassNumber classNumber = ClassNumber.fromDescription(jcStr);
                int time = classNumber.ordinal();
                Map<String, Object> map = new HashMap<>();
                map.put("id", labsRoomschedule.getId());
                LabsRoominfo labsRoominfo = labsRoominfoService.getRecordByNo(loginUser.getAppCode(), labsRoomschedule.getRoomNo());
                LabsAreainfo labsAreainfo = labsAreainfoService.getRecordByNo(loginUser.getAppCode(), labsRoominfo.getAreaNo());
                String[] timeStr = {"第1-2节", "第3-4节", "第5-6节", "第7-8节", "第9-10节", "第11-12节"};
                String sb = labsRoomschedule.getKcmc() +
                        labsRoomschedule.getKkzc() + "周" +
                        labsAreainfo.getAreaName() + "-" +
                        labsRoominfo.getRoomName() + "," +
                        "[" + timeStr[time] + "]" + "," +
                        labsRoomschedule.getHbmc() + "," +
                        "任课老师：" + labsRoomschedule.getXm();
                map.put("desc", sb);
                courseSchedule.setSchedule(time, day, map);
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("desc", "第1-2节");
        courseSchedule.setSchedule(0, 0, map);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("desc", "第3-4节");
        courseSchedule.setSchedule(1, 0, map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("desc", "第5-6节");
        courseSchedule.setSchedule(2, 0, map2);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("desc", "第7-8节");
        courseSchedule.setSchedule(3, 0, map3);
        Map<String, Object> map4 = new HashMap<>();
        map4.put("desc", "第9-10节");
        courseSchedule.setSchedule(4, 0, map4);
        Map<String, Object> map5 = new HashMap<>();
        map5.put("desc", "第11-12节");
        courseSchedule.setSchedule(5, 0, map5);

        Map<String, Object> schedule = courseSchedule.getSchedule();

        List<Map<String, Object>> scheduleList = new ArrayList<>();
        scheduleList.add(getTimeItem(schedule, "0-"));
        scheduleList.add(getTimeItem(schedule, "1-"));
        scheduleList.add(getTimeItem(schedule, "2-"));
        scheduleList.add(getTimeItem(schedule, "3-"));
        scheduleList.add(getTimeItem(schedule, "4-"));
        scheduleList.add(getTimeItem(schedule, "5-"));
        return AjaxResult.success(scheduleList);
    }

    private Map<String, Object> getTimeItem(Map<String, Object> schedule, String startStr) {
        Map<String, Object> newSchedule = new HashMap<>();
        for (Map.Entry<String, Object> entry : schedule.entrySet()) {
            if (entry.getKey().startsWith(startStr)) {
                if (entry.getKey().endsWith("-0")) {
                    newSchedule.put("jxjcbh", entry.getValue());
                } else if (entry.getKey().endsWith("-1")) {
                    newSchedule.put("Monday", entry.getValue());
                } else if (entry.getKey().endsWith("-2")) {
                    newSchedule.put("Tuesday", entry.getValue());
                } else if (entry.getKey().endsWith("-3")) {
                    newSchedule.put("Wednesday", entry.getValue());
                } else if (entry.getKey().endsWith("-4")) {
                    newSchedule.put("Thursday", entry.getValue());
                } else if (entry.getKey().endsWith("-5")) {
                    newSchedule.put("Friday", entry.getValue());
                } else if (entry.getKey().endsWith("-6")) {
                    newSchedule.put("Saturday", entry.getValue());
                } else if (entry.getKey().endsWith("-7")) {
                    newSchedule.put("Sunday", entry.getValue());
                }
            }
        }
        return newSchedule;
    }

    /**
     * 新增实验室排课
     */
    @Oplog(title = "实验室排课", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsRoomschedule labsRoomschedule) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsRoomschedule.setSchdNo(UuidUtils.shortUUID());
        labsRoomschedule.setCreateBy(loginUser.getUserNo());
        labsRoomschedule.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsRoomscheduleService.AddNewRecord(loginUser.getAppCode(), labsRoomschedule));
    }

    /**
     * 编辑实验室排课
     */
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
    @Oplog(title = "实验室排课", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsRoomschedule labsRoomschedule) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsRoomscheduleService.getRecordByNo(loginUser.getAppCode(), labsRoomschedule.getSchdNo()))) {
            labsRoomschedule.setSchdNo(UuidUtils.shortUUID());
            labsRoomschedule.setCreateBy(loginUser.getUserNo());
            labsRoomschedule.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsRoomscheduleService.AddNewRecord(loginUser.getAppCode(), labsRoomschedule));
        } else {
            labsRoomschedule.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsRoomscheduleService.UpdateRecord(loginUser.getAppCode(), labsRoomschedule));
        }
    }

    /**
     * 删除实验室排课
     */
    @Oplog(title = "实验室排课", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsRoomscheduleService.SoftDeleteByNos(loginUser.getAppCode(), ids));
    }

    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(labsRoomscheduleService.getRecordByNo(loginUser.getAppCode(), id));
    }

    /**
     * 获取实验室排课详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsRoomschedule info = labsRoomscheduleService.getRecordByNo(loginUser.getAppCode(), id);
        return AjaxResult.success(info);
    }

    /**
     * 导出实验室排课列表
     */
    @Oplog(title = "实验室排课", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest) {
        String condition = "";
        Map maps = pRequest.getDataParams();
        if (maps.containsKey("roomName") && StringUtils.isNotEmpty(maps.get("roomName").toString())) {
            condition += "And room_name like '%" + maps.get("roomName") + "%'";
        }
        if (StringUtils.isNotEmpty(condition)) {
            condition = condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsRoomscheduleService.getCountByCondition(loginUser.getAppCode(), condition);

        List<LabsRoomschedule> list = labsRoomscheduleService.getRecordsByPaging(loginUser.getAppCode(), 1, count, condition, "id", "Asc");
        ExcelUtils<LabsRoomschedule> util = new ExcelUtils<LabsRoomschedule>(LabsRoomschedule.class);
        return util.exportExcel(list, "LabsRoomschedule");
    }

}
