package com.msy.service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msy.constant.Constants;
import com.msy.mapper.EntityMapper;
import com.msy.mapper.OrderMapper;
import com.msy.mapper.OrderProductMapper;
import com.msy.model.EntityVo;
import com.msy.model.Order;
import com.msy.model.OrderProduct;
import com.msy.model.OrderVo;
import com.msy.model.Result;
import com.msy.service.OrderService;
import com.msy.utils.Pager;
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private EntityMapper entityMapper;
	
	@Autowired
	private OrderProductMapper orderProductMapper;
	
	@Override
	public void add(Order order) throws Exception {
			this.orderMapper.insert(order);
	}

	@Override
	public Order getOrderById(String id) throws Exception {
		return this.orderMapper.selectByPrimaryKey(id);
	}

	@Override
	public void delOrderById(String id) throws Exception {
		this.orderMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateOrderById(Order order) throws Exception {
		this.orderMapper.updateByPrimaryKey(order);
	}


	@Override
	public Result getOrderByUserId(Pager<Order> pager, Order order) throws Exception {
		Result rb = new Result();
		pager.setDataSize(orderMapper.count(order));
		if(pager.getCurrentPage() > 1) {
			int offset = pager.getPageSize() * (pager.getCurrentPage() - 1);
			order.setOffset(offset);
		}
		if(pager.getDataSize() < order.getOffset()){
			order.setOffset(0);
		}
		
		pager.setBeanList(orderMapper.search(order));
		rb.setResultData(pager);
		return rb;
	}


	@Override
	public Order getOrderByIdAndStatus(String orderId) throws Exception {
		// TODO Auto-generated method stub
		return this.orderMapper.selectByOrderIdAndpaystatus(orderId);
	}


	@Override
	public Result add(String entityId, String roomId,String out_trade_no) throws Exception {

		Result result = new Result();
		
		/**
		 * 查询产品信息
		 */
		try{
			Date date = new Date();
			EntityVo vo = this.entityMapper.selectEntityVo(entityId);
			
			OrderProduct op = new OrderProduct();
			
			op.setOrderProductId(UUID.randomUUID().toString().replaceAll("-", ""));
			op.setOrderId(out_trade_no);
			op.setProductId(vo.getProductId());
			op.setProductName(vo.getProductName());
			op.setProductPrice(vo.getProductPrice());
			op.setOpenTimeLength((byte) vo.getOpenTimeLength().intValue());
			this.orderProductMapper.insert(op);
			
			Order order = new Order();
			logger.debug("下订单成功  entityId==>"+entityId+", roomId==>"+roomId +" out_trade_no=>"+out_trade_no);
			order.setOrderId(out_trade_no);
			order.setPayPrice(vo.getProductPrice());
			order.setAllPrice(vo.getProductPrice());
			order.setPayType((byte)Constants.Order.PAY_STYLE_WECHAT);
			order.setPayStatus((byte)Constants.Order.PAY_STATUS_READY);
			order.setHouseId(roomId);
			order.setPayTime(date);
			order.setCreatedTime(date);
			order.setBusinessStatus((byte)Constants.Order.PAY_BUSINESS_STATUS_READY);
			this.orderMapper.insert(order);
			
			result.setResultData(vo);
			return result;
		}catch(Exception e){
			e.printStackTrace();
			result.setResultCode(Constants.RESULT_FAIL);
		}
		
		return result;
	}

	@Override
	public OrderVo getOrderVoById(String order) {
		return this.orderMapper.selectOrderVoByPrimaryKey(order);
	}
 
	/**
	 * 根据OrderId条件查询一条数据
	 */
	@Override
	public Result getOrderByIdList(Pager<Order> pager, Order order) throws Exception {
		// TODO 自动生成的方法存根
		Result rb = new Result();
		pager.setBeanList(orderMapper.setOrderByIdList(order));
		rb.setResultData(pager);
		return rb;
	}

	/**
	 * 保存
	 * @return 
	 */
	@Override
	public int updateOrderUpdate(Pager<Order> pager,Order order) throws Exception {
		// TODO 自动生成的方法存根
//		return this.orderMapper.setupdateOrderUpdate(order);
		int rb = orderMapper.setupdateOrderUpdate(order);
		return rb;
	}

	/**
	 * 删除
	 * @return 
	 */
	@Override
	public int delOrderDelete(Pager<Order> pager,Order order) throws Exception {
		// TODO 自动生成的方法存根
//		return this.orderMapper.setdelOrderDelete(order);
		int rb = orderMapper.setdelOrderDelete(order);
		return rb;
	}

	/**
	 * 验证
	 * 
	 */
	@Override
	public Result getOrderByIdRemote(Pager<Order> pager, Order order) {
		// TODO 自动生成的方法存根
		Result rb = new Result();
		pager.setBeanList(orderMapper.setOrderByIdRemote(order));
		rb.setResultData(pager);
		return rb;
	}

	@Override
	public int addOrderAdd(Pager<Order> pager, Order order) throws Exception {
		// TODO 自动生成的方法存根
		int rb = orderMapper.insert(order);
		return rb;
	}

	

}
