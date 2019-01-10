package com.msy.mapper;


import java.util.List;


import com.msy.model.Category;

public interface CategoryMapper {
    

	/**
     *删除分类
     *@param categoryId
	 *@return
     */
    int deleteByPrimaryKey(String categoryId);

	/**
     *插入分类
     *@param categoryId
	 *@return
     */
    int insert(Category record);

	/**
     *查询分类列表
     *@param 
	 *@return
     */
    List<Category> selectall();
    /**
     *查询分类列表
     *@param 
	 *@return
     */
    List<Category> selectforcategoryid(String categoryid);
   
    /**
     *查询分类
     *@param categoryId
	 *@return
     */
    Category selectByPrimaryKey(String categoryId);

   
    /**
     *修改分类
     *@param categoryId
	 *@return
     */
    int updateByPrimaryKey(Category record);
}