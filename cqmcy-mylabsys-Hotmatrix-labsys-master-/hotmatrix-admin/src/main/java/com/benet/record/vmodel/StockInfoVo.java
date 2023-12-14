package com.benet.record.vmodel;


import java.time.LocalDateTime;

public class StockInfoVo {

    private String stockNo;
    private String storeNo;
    private String ruserNo;
    private String equipNo;
    private String equipType;
    private int equipNum;
    private int stockNum;
    private String produceNumber;
    private LocalDateTime produceDate;
    private LocalDateTime expireDate;
    private String checkState;
    private String comments;

    public String getStockNo() {
        return stockNo;
    }

    public void setStockNo(String stockNo) {
        this.stockNo = stockNo;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getRuserNo() {
        return ruserNo;
    }

    public void setRuserNo(String ruserNo) {
        this.ruserNo = ruserNo;
    }

    public String getEquipNo() {
        return equipNo;
    }

    public void setEquipNo(String equipNo) {
        this.equipNo = equipNo;
    }

    public String getEquipType() {
        return equipType;
    }

    public void setEquipType(String equipType) {
        this.equipType = equipType;
    }

    public int getEquipNum() {
        return equipNum;
    }

    public void setEquipNum(int equipNum) {
        this.equipNum = equipNum;
    }

    public int getStockNum() {
        return stockNum;
    }

    public void setStockNum(int stockNum) {
        this.stockNum = stockNum;
    }

    public String getProduceNumber() {
        return produceNumber;
    }

    public void setProduceNumber(String produceNumber) {
        this.produceNumber = produceNumber;
    }

    public LocalDateTime getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(LocalDateTime produceDate) {
        this.produceDate = produceDate;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public String getCheckState() {
        return checkState;
    }

    public void setCheckState(String checkState) {
        this.checkState = checkState;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
