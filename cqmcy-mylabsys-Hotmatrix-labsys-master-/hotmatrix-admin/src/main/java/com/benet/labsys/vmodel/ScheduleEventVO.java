package com.benet.labsys.vmodel;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author 这颗甜
 * @Date 2022/8/30 17:49
 * @description: 课表事件vo
 */
@Data
@Accessors(chain = true)
public class ScheduleEventVO {
    private String id;
    private String title;
    private String start;
    private String end;
    private String weekNumber;
}
