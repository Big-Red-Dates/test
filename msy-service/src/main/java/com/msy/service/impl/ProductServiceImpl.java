package com.msy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msy.annotation.DataSource;
import com.msy.mapper.ProductMapper;
import com.msy.model.Product;
import com.msy.service.ProductService;

@Service
@Transactional
@DataSource("ds_master")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public void add(Product pro) throws Exception {
		this.productMapper.insert(pro);
	}

	@Override
	public Product getProductById(String productId) throws Exception {
		/**
		 * 
		 */
		return this.productMapper.selectByPrimaryKey(productId);
	}

	
}
