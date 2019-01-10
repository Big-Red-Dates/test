package com.msy.mapper;

import com.msy.model.Product;

public interface ProductMapper {

	/**
	 * 删除商品
	 * @param productId
	 * @return
	 */
    int deleteByPrimaryKey(String productId);

    /**
     * 新增商品
     * @param record
     * @return
     */
    int insert(Product record);

    /**
     * 查询商品
     * @param productId
     * @return
     */
    Product selectByPrimaryKey(String productId);
    
    /**
     * 从缓存中查询商品
     * @param productId
     * @return
     */
    Product selectByPrimaryKeyOnEhCache(String productId);
}
