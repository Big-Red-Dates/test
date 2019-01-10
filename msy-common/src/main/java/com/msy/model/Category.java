package com.msy.model;

import java.io.Serializable;

public class Category implements Serializable {
    /**
	 * 分类实体类
	 */
	private static final long serialVersionUID = 4589292792046730130L;

	/**
     *分类id
     */
    private String categoryId;

    /**
     *分类名称
     */
    private String categoryName;

   
    public String getCategoryId() {
        return categoryId;
    }

    
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

   
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }
}