package com.zxd.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (Rmtypedailyrate)实体类
 *
 * @author makejava
 * @since 2025-06-29 20:46:13
 */
public class Rmtypedailyrate implements Serializable {
    private static final long serialVersionUID = 501029178484011387L;
    
    private Integer rmtypedailyrateId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ddate;
    
    private Integer rmNumber;
    
    private Integer rateruleId;


    public Integer getRmtypedailyrateId() {
        return rmtypedailyrateId;
    }

    public void setRmtypedailyrateId(Integer rmtypedailyrateId) {
        this.rmtypedailyrateId = rmtypedailyrateId;
    }

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

    public Integer getRateruleId() {
        return rateruleId;
    }

    public void setRateruleId(Integer rateruleId) {
        this.rateruleId = rateruleId;
    }

}

