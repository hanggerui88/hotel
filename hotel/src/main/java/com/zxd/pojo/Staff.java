package com.zxd.pojo;

import java.io.Serializable;

/**
 * (Staff)实体类
 *
 * @author makejava
 * @since 2025-06-29 20:46:15
 */
public class Staff implements Serializable {
    private static final long serialVersionUID = 772770039099137234L;
    
    private Integer staffId;
    
    private String password;
    
    private String username;
    
    private String department;
    
    private String email;
    /**
     * 权限
     */
    private String qx;
    /**
     * 备注
     */
    private String notes;


    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQx() {
        return qx;
    }

    public void setQx(String qx) {
        this.qx = qx;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}

