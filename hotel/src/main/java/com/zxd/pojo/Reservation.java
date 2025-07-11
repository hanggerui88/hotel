package com.zxd.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * (Reservation)实体类
 *
 * @author makejava
 * @since 2025-06-29 20:46:11
 */
public class Reservation implements Serializable {
    private static final long serialVersionUID = 356625066949888769L;
    
    private Integer reId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date cinDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date coutDate;
    
    private Integer adultNum;
    
    private Integer childNum;
    /**
     * 一次订一个类
     */
    private Integer rmtypeId;
    
    private Integer rmNum;
    
    private String cinName;
    
    private String cinPhone;
    /**
     * 订单创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    /**
     * 预订状态：1-有效，0-取消
     */
    private Integer reStatus;
    /**
     * 负责这个预定的员工id
     */
    private Integer staffId;
    /**
     * 用户备注
     */
    private String note;


    public Integer getReId() {
        return reId;
    }

    public void setReId(Integer reId) {
        this.reId = reId;
    }

    public Date getCinDate() {
        return cinDate;
    }

    public void setCinDate(Date cinDate) {
        this.cinDate = cinDate;
    }

    public Date getCoutDate() {
        return coutDate;
    }

    public void setCoutDate(Date coutDate) {
        this.coutDate = coutDate;
    }

    public Integer getAdultNum() {
        return adultNum;
    }

    public void setAdultNum(Integer adultNum) {
        this.adultNum = adultNum;
    }

    public Integer getChildNum() {
        return childNum;
    }

    public void setChildNum(Integer childNum) {
        this.childNum = childNum;
    }

    public Integer getRmtypeId() {
        return rmtypeId;
    }

    public void setRmtypeId(Integer rmtypeId) {
        this.rmtypeId = rmtypeId;
    }

    public Integer getRmNum() {
        return rmNum;
    }

    public void setRmNum(Integer rmNum) {
        this.rmNum = rmNum;
    }

    public String getCinName() {
        return cinName;
    }

    public void setCinName(String cinName) {
        this.cinName = cinName;
    }

    public String getCinPhone() {
        return cinPhone;
    }

    public void setCinPhone(String cinPhone) {
        this.cinPhone = cinPhone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getReStatus() {
        return reStatus;
    }

    public void setReStatus(Integer reStatus) {
        this.reStatus = reStatus;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    private BigDecimal rate;         // 房间价格（来自SQL的rt.rate）
    private String staffName;        // 员工姓名（来自SQL的s.username）
    private Integer canceltime;
    private String name;
    private  String rtname;
    // Getter 和 Setter
    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public void setCanceltime(Integer canceltime) {
        this.canceltime = canceltime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRtname() {
        return rtname;
    }

    public void setRtname(String rtname) {
        this.rtname = rtname;
    }

    public Integer getCanceltime() {
        return canceltime;
    }
}

