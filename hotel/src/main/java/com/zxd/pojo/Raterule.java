package com.zxd.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (Raterule)实体类
 *
 * @author makejava
 * @since 2025-06-29 20:46:06
 */
public class Raterule implements Serializable {
    private static final long serialVersionUID = -79416027171175102L;
    
    private Integer rateruleId;
    /**
     * 规则生效日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateStart;
    /**
     * 规则失效日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateEnd;
    /**
     * 操作一类房间
     */
    private Integer rmtypeId;
    /**
     * 操作一个房间
     */
    private Integer rmNumber;
    /**
     * 适用于周几
     */
    private boolean  mon;
    
    private boolean  tue;
    
    private boolean  wed;
    
    private boolean  thu;
    
    private boolean  fri;
    
    private boolean  sat;
    
    private boolean  sun;
    /**
     * 折扣类型名称
     */
    private String name;
    /**
     * 计算公式
     */
    private String formula;
    /**
     * 折扣值
     */
    private Double discountValue;
    /**
     * 条件值（用于满减等）
     */
    private Double conditionValue;
    /**
     * 入住前两天/前一天可以取消 cin_time-canceltime xx前可取消
     */
    private Integer canceltime;
    
    private String notes;


    public Integer getRateruleId() {
        return rateruleId;
    }

    public void setRateruleId(Integer rateruleId) {
        this.rateruleId = rateruleId;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Integer getRmtypeId() {
        return rmtypeId;
    }

    public void setRmtypeId(Integer rmtypeId) {
        this.rmtypeId = rmtypeId;
    }

    public Integer getRmNumber() {
        return rmNumber;
    }

    public void setRmNumber(Integer rmNumber) {
        this.rmNumber = rmNumber;
    }

    public boolean  getMon() {
        return mon;
    }

    public void setMon(boolean  mon) {
        this.mon = mon;
    }

    public boolean  getTue() {
        return tue;
    }

    public void setTue(boolean  tue) {
        this.tue = tue;
    }

    public boolean  getWed() {
        return wed;
    }

    public void setWed(boolean  wed) {
        this.wed = wed;
    }

    public boolean  getThu() {
        return thu;
    }

    public void setThu(boolean  thu) {
        this.thu = thu;
    }

    public boolean  getFri() {
        return fri;
    }

    public void setFri(boolean  fri) {
        this.fri = fri;
    }

    public boolean  getSat() {
        return sat;
    }

    public void setSat(boolean  sat) {
        this.sat = sat;
    }

    public boolean  getSun() {
        return sun;
    }

    public void setSun(boolean  sun) {
        this.sun = sun;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    public Double getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(Double conditionValue) {
        this.conditionValue = conditionValue;
    }

    public Integer getCanceltime() {
        return canceltime;
    }

    public void setCanceltime(Integer canceltime) {
        this.canceltime = canceltime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    private String rmtype; // 或类似的属性名

    // 必须有对应的 getter 方法
    public String getRmtype() {
        return rmtype;
    }

    // setter 方法
    public void setRmtype(String rmtype) {
        this.rmtype = rmtype;
    }

}

