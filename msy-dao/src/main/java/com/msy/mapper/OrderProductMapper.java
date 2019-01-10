package com.msy.mapper;

import com.msy.model.OrderProduct;

public interface OrderProductMapper {
   
    /**
     *删除订单商品信息
     *@param movieId
	 *@return
     */
    int deleteByPrimaryKey(String orderProductId);

    /**
     *添加订单商品信息
     *@param movieId
	 *@return
     */
    int insert(OrderProduct orderProduct);

    /**
     *查询订单商品信息
     *@param movieId
	 *@return
     */
    OrderProduct selectByPrimaryKey(String orderProductId);

    /**
     *修改订单商品信息
     *@param movieId
	 *@return
     */
    int updateByPrimaryKey(OrderProduct orderProduct);
}