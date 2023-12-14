package com.benet.web.vmodel;

public class CodeImgInfo {

    private String uuid;
    private String img;

    public CodeImgInfo(String uuid, String img) {
        this.uuid = uuid;
        this.img = img;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
