package com.github.itdachen.admin.sdk.vo;

import java.io.Serializable;

/**
 * 查询返回给前端的类型
 *
 * @author 王大宸
 * @date 2024-12-19 9:53
 */
public class AdmAreaModel implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private String id;

    /**
     * 名称
     */
    private String title;

    /**
     * 上级ID
     */
    private String parentId;


    /**
     * 数据级别：省市区县乡镇街道
     */
    private String level;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
