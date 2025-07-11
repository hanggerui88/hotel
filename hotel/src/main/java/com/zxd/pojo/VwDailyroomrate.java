package com.zxd.pojo;


import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;

/**
 * (VwDailyroomrate)实体类
 *
 * @author makejava
 * @since 2025-06-29 20:46:17
 */
public class VwDailyroomrate implements Serializable {
    private static final long serialVersionUID = 221758766794592406L;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ddate;
    
    private Integer rmNumber;
    
    private String roomName;
    
    private String roomType;
    /**
     * 折扣类型名称
     */
    private String rateRuleName;
    /**
     * 计算公式
     */
    private String discountType;
    /**
     * 折扣值
     */
    private Double discountValue;
    /**
     * 房间类型的价格
     */
    private Integer basePrice;
    
    private Double finalPrice;


    public Date getDdate() {
        return ddate;
    }

    public void setDdate(Date ddate) {
        this.ddate = ddate;
    }

    public Integer getRmNumber() {
        return rmNumber;
    }

    public void setRmNumber(Integer rmNumber) {
        this.rmNumber = rmNumber;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRateRuleName() {
        return rateRuleName;
    }

    public void setRateRuleName(String rateRuleName) {
        this.rateRuleName = rateRuleName;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    public Integer getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Integer basePrice) {
        this.basePrice = basePrice;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

}

