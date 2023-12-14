/*
 * Chsi
 * Created on 2023-02-13
 */
package com.benet.labsys.domain;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangwd <a href="mailto:mail_zwd@163.com.cn">zhangwd</a>
 * @version $Id$
 */
public class CourseSchedule {
    private Map<String, Object> courseSchedule = new HashMap<>();

    public Map<String, Object> getSchedule() {
        return courseSchedule;
    }

    public void setSchedule(int time, int day, Object obj) {
        courseSchedule.put(String.format("%d-%d", time, day), obj);
    }
}



