package com.zxd.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (VwRoomstatusdetails)实体类
 *
 * @author makejava
 * @since 2025-06-29 20:46:17
 */
public class VwRoomstatusdetails implements Serializable {
    private static final long serialVersionUID = 700236236434649741L;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ddate;
    /**
     * 房间号
     */
    private Integer rmNumber;
    
    private String rmName;
    
    private String rmType;
    
    private String rmStatus;
    
    private String image;


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

    public String getRmName() {
        return rmName;
    }

    public void setRmName(String rmName) {
        this.rmName = rmName;
    }

    public String getRmType() {
        return rmType;
    }

    public void setRmType(String rmType) {
        this.rmType = rmType;
    }

    public String getRmStatus() {
        return rmStatus;
    }

    public void setRmStatus(String rmStatus) {
        this.rmStatus = rmStatus;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}

