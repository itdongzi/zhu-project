package com.benet.system.vmodel;

import lombok.Data;

import java.lang.reflect.Field;

@Data
public class ScheduleImportVo {
    private String orderNo;
    private String roomNo;
    private String semeNo;
    private String scheduleType;
    private String scheduleValue;
    private String useValue;
    private String xnxqxx;
    private String kkzc;
    private String dszbz;
    private String sectName;
    private String dwmc;
    private String jzgh;
    private String xm;
    private String kcdm;
    private String kcmc;
    private String kbhbxxbh;
    private String hbmc;
    private String bjdm;
    private String bjmc;

    public boolean validateAllPropertiesNotNull() {
        //通过反射获取所有属性
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                //检查每个属性是否为null
                if (field.get(this) == null) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
