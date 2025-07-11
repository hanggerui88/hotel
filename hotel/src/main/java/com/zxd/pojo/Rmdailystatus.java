package com.zxd.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (Rmdailystatus)实体类
 *
 * @author makejava
 * @since 2025-06-29 20:46:12
 */
public class Rmdailystatus implements Serializable {
    private static final long serialVersionUID = -46665246954211005L;
    
    private Integer rmdailystatusId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ddate;
    
    private Integer rmNumber;
    /**
     * 状态：0-默认,1-售出,2-维修,3-纠纷停售,4-打扫,5-空房,6-取消预订
     */
    private Integer statusNumber;


    public Integer getRmdailystatusId() {
        return rmdailystatusId;
    }

    public void setRmdailystatusId(Integer rmdailystatusId) {
        this.rmdailystatusId = rmdailystatusId;
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

    public Integer getStatusNumber() {
        return statusNumber;
    }

    public void setStatusNumber(Integer statusNumber) {
        this.statusNumber = statusNumber;
    }

}

