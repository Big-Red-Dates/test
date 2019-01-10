package com.msy.service;

import java.util.List;

import com.msy.model.Category;



public interface CateGoryService {
	/**
	 * 获取影片信息列表
	 * @param 
	 * @return
	 * @throws Exception
	 */
	List<Category> getCategoryList() throws Exception ;
	
	/**
	 * 根据id获取影片信息列表
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<Category> getCategoryListBycateid(String categoryid) throws Exception;
}
