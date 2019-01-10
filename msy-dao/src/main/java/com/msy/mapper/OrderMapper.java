package com.msy.mapper;

import java.util.List;

import com.msy.model.Order;
import com.msy.model.OrderVo;


public interface OrderMapper {
    
	/**
     *删除订单
     *@param movieId
	 *@return
     */
	int deleteByPrimaryKey(String orderId);

    /**
   	 * 
     * 新增订单
     * @param movie
     * @return
     */
    int insert(Order order);
    
    /** 
     * 查询订单
     * @param movieId
     * @return
     */
    Order selectByPrimaryKey(String order);

    /** 
     * 查询订单
     * @param movieId
     * @return
     */
    OrderVo selectOrderVoByPrimaryKey(String order);
    
    
    /**
     * 修改订单
     * @param movie
     * @return
     */
    int updateByPrimaryKey(Order order);
    
    /**
     * 查询用户订单
     * @param movie
     * @return
     */
    List<Order> getOrderByUserId(String userId);


    /**
     * 查询总理记录数
     * @param pagerEntity
     * @return
     */
	Integer count(Order order);

	/**
	 * 查询订单数量
	 * @param pagerEntity
	 * @return
	 */
	List<Order> search(Order order);

	
	/** 
     * 根据订单Id和支付状态查询订单
     * @param movieId
     * @return
     */
    Order selectByOrderIdAndpaystatus(String orderId);


	/**
	 * 根据OrderId条件查询一条数据
	 */
	List<Order> setOrderByIdList(Order order);

	/**
	 * 保存
	 */
	int setupdateOrderUpdate(Order order);

	/**
	 * 删除
	 */
	int setdelOrderDelete(Order order);

	/**
	 * 验证
	 * 
	 * @param order
	 * @return
	 */
	List<Order> setOrderByIdRemote(Order order);

}