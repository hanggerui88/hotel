package com.zxd.pojo;

import java.io.Serializable;

/**
 * (Tag)实体类
 *
 * @author makejava
 * @since 2025-06-29 20:46:16
 */
public class Tag implements Serializable {
    private static final long serialVersionUID = -19232777281783771L;
    
    private Integer tagId;
    
    private String content;


    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

