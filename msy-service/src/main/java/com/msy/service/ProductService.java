package com.msy.service;

import com.msy.model.Product;

/**
 * 商品服务
 * @author add by yanz.wu
 *
 */
public interface ProductService {

	/**
	 * 添加商品接口
	 * @param pro
	 */
	void add(Product pro) throws Exception ;
	
	/**
	 * 获取商品信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Product getProductById(String id) throws Exception ;
}
