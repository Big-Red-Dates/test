package com.msy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msy.mapper.OrderProductMapper;
import com.msy.model.OrderProduct;
import com.msy.service.OrderProductService;

@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService {

	@Autowired
    private OrderProductMapper orderProductMapper;
	
	@Override
	public void add(OrderProduct orderprodu) throws Exception {
		// TODO Auto-generated method stub
		this.orderProductMapper.insert(orderprodu);
	}

	@Override
	public OrderProduct getorderproduById(String id) throws Exception {
		// TODO Auto-generated method stub
		return this.orderProductMapper.selectByPrimaryKey(id);
	}

	@Override
	public void delOrderProductById(String id) throws Exception {
		// TODO Auto-generated method stub
		this.orderProductMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateOrderProductById(OrderProduct orderprodu) throws Exception {
		// TODO Auto-generated method stub
		this.orderProductMapper.updateByPrimaryKey(orderprodu);
	}

}
