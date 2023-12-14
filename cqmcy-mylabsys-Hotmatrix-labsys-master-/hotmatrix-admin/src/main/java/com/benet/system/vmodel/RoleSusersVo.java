package com.benet.system.vmodel;


public class RoleSusersVo {

    private String key;
    private String title;
    private String desc;
    private boolean chosen;

    public RoleSusersVo() {
    }

    public RoleSusersVo(String key, String title, String desc, boolean chosen) {
        this.key = key;
        this.title = title;
        this.desc = desc;
        this.chosen = chosen;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isChosen() {
        return chosen;
    }

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }
}
