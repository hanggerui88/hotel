package com.zxd.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (Rmstatusrule)实体类
 *
 * @author makejava
 * @since 2025-06-29 20:46:12
 */
public class Rmstatusrule implements Serializable {
    private static final long serialVersionUID = -74624065918673161L;
    
    private Integer rmstatusruleId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateStart;
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
     * 状态：0-默认,1-售出,2-维修,3-纠纷停售,4-打扫,5-空房,6-取消预订
     */
    private Integer statusNumber;
    
    private String notes;
    private String name;
    private String note;

    public Integer getRmstatusruleId() {
        return rmstatusruleId;
    }

    public void setRmstatusruleId(Integer rmstatusruleId) {
        this.rmstatusruleId = rmstatusruleId;
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

    public Integer getStatusNumber() {
        return statusNumber;
    }

    public void setStatusNumber(Integer statusNumber) {
        this.statusNumber = statusNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

