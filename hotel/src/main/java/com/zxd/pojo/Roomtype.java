package com.zxd.pojo;

import java.io.Serializable;

/**
 * (Roomtype)实体类
 *
 * @author makejava
 * @since 2025-06-29 20:46:15
 */
public class Roomtype implements Serializable {
    private static final long serialVersionUID = -28167958932312123L;
    
    private Integer rmtypeId;
    
    private String name;
    /**
     * 房间类型的价格
     */
    private Integer rate;
    /**
     * 备注
     */
    private String notes;


    public Integer getRmtypeId() {
        return rmtypeId;
    }

    public void setRmtypeId(Integer rmtypeId) {
        this.rmtypeId = rmtypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}

