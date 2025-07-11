package com.zxd.pojo;

import java.io.Serializable;

/**
 * (Room)实体类
 *
 * @author makejava
 * @since 2025-06-29 20:46:14
 */
public class Room implements Serializable {
    private static final long serialVersionUID = -77236563941960531L;
    /**
     * 房间号
     */
    private Integer rmNumber;
    
    private String name;
    
    private String image;
    
    private String commentContent;
    /**
     * 评分
     */
    private String pf;
    /**
     * 评价
     */
    private String pj;
    /**
     * 环境
     */
    private String hj;
    /**
     * 卫生
     */
    private String ws;
    /**
     * 服务
     */
    private String fw;
    /**
     * 设施
     */
    private String ss;

    private Integer roomtypeId;
    
    private Integer tagId;
    
    private String notes;


    public Integer getRmNumber() {
        return rmNumber;
    }

    public void setRmNumber(Integer rmNumber) {
        this.rmNumber = rmNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public String getPj() {
        return pj;
    }

    public void setPj(String pj) {
        this.pj = pj;
    }

    public String getHj() {
        return hj;
    }

    public void setHj(String hj) {
        this.hj = hj;
    }

    public String getWs() {
        return ws;
    }

    public void setWs(String ws) {
        this.ws = ws;
    }

    public String getFw() {
        return fw;
    }

    public void setFw(String fw) {
        this.fw = fw;
    }

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    public Integer getRoomtypeId() {
        return roomtypeId;
    }

    public void setRoomtypeId(Integer roomtypeId) {
        this.roomtypeId = roomtypeId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    private String rmtype; // 或类似的属性名
    private String tagContent; //

    // 必须有对应的 getter 方法
    public String getRmtype() {
        return rmtype;
    }

    // setter 方法
    public void setRmtype(String rmtype) {
        this.rmtype = rmtype;
    }
    public String gettagContent() {
        return tagContent;
    }

    // setter 方法
    public void settagContente(String tagContent) {
        this.tagContent = tagContent;
    }
}

