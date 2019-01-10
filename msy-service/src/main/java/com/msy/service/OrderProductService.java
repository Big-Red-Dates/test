package com.msy.service;

import com.msy.model.OrderProduct;
/**
 * 订单商品服务
 * @author add by jung.chen
 *
 */
public interface OrderProductService {

	/**
	 * 添加订单商品接口
	 * @param movie
	 */
	void add(OrderProduct orderprodu) throws Exception ;
	
	/**
	 * 获取订单商品信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	OrderProduct getorderproduById(String id) throws Exception ;
	
	/**
	 * 根据id删除订单商品
	 * @param id
	 * @throws Exception
	 */
	void delOrderProductById(String id)throws Exception;
	
	/**
	 * 根据id修改订单商品
	 * @param id
	 * @throws Exception
	 */
	void updateOrderProductById(OrderProduct orderprodu)throws Exception;
}
