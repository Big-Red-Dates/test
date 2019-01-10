package com.msy.service;

import com.msy.model.Order;
import com.msy.model.OrderVo;
import com.msy.model.Result;
import com.msy.utils.Pager;
/**
 * 订单服务
 * @author add by jung.chen
 *
 */
public interface OrderService {
	
	/**
	 * 添加订单接口
	 * @param movie
	 */
	Result add(String entityId,String roomId,String out_trade_no) throws Exception ;
	
	/**
	 * 添加订单接口
	 * @param movie
	 */
	void add(Order order) throws Exception ;
	
	/**
	 * 获取订单信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Order getOrderById(String id) throws Exception ;
	


	  /** 
     * 查询订单
     * @param movieId
     * @return
     */
    OrderVo getOrderVoById(String order);
    

	/**
	 * 根据id删除订单
	 * @param id
	 * @throws Exception
	 */
	void delOrderById(String id)throws Exception;
	
	/**
	 * 根据id修改订单
	 * @param id
	 * @throws Exception
	 */
	void updateOrderById(Order order)throws Exception;

	
	/**
     * 查询用户订单
	 * @param pager 
     * @param userId
     * @return
     */
	Result getOrderByUserId(Pager<Order> pager, Order order)  throws Exception;

	/**
	 * 获取订单信息
	 * @param id and pay_status
	 * @return
	 * @throws Exception
	 */
	Order getOrderByIdAndStatus(String orderId) throws Exception ;




	/**
	 * 查询
	 */
	Result getOrderByIdList(Pager<Order> pager, Order order) throws Exception;

	/**
	 * 保存
	 * @return 
	 */
	int updateOrderUpdate(Pager<Order> pager, Order order) throws Exception;

	/**
	 * 删除
	 * @return 
	 */
	int delOrderDelete(Pager<Order> pager, Order order) throws Exception;
	
	/**
	 * 新增
	 * @return 
	 */
	int addOrderAdd(Pager<Order> pager, Order order) throws Exception;

	/**
	 * 验证
	 * 
	 * @param pager
	 * @param order
	 * @return
	 */
	Result getOrderByIdRemote(Pager<Order> pager, Order order);


}
