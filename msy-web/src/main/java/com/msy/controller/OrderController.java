package com.msy.controller;



import java.util.List;


import java.util.Date;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.msy.constant.Constants;
import com.msy.model.Operator;
import com.msy.model.Order;
import com.msy.model.Result;
import com.msy.service.CacheSessionService;
import com.msy.service.OrderService;
import com.msy.service.SequenceService;
import com.msy.utils.DateTimeUtil;
import com.msy.utils.Pager;


@Controller
@RequestMapping(value = "/order/" )
public class OrderController extends BaseController {

	private Log log = LogFactory.getLog(this.getClass().getName());
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CacheSessionService cacheSessionService;
	
	@Autowired
	private SequenceService sequenceService;
	
	/**
	 * 查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/search", "" })
	public Result search(HttpServletRequest request, HttpServletResponse response,Pager<Order> pager, Order order)
	{
		Operator operator = super.getLoginUser(request);
		Result rb = new Result();
		
		if(null == operator){
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("用户未登录或会话超时");
			return rb;
		}
		
		try {
			order.setUserId(operator.getOperatorId().trim());
			rb = this.orderService.getOrderByUserId(pager,order);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("业务处理异常",e);
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("业务处理异常");
			return rb;
		}
		return rb;
	}
	public CacheSessionService getCacheSessionService() {
		return cacheSessionService;
	}
	public void setCacheSessionService(CacheSessionService cacheSessionService) {
		this.cacheSessionService = cacheSessionService;
	}
	/**
	 * 根据OrderId条件查询一条数据
	 * 
	 * @param request
	 * @param response
	 * @param pager
	 * @param order
	 * @return
	 */
	@RequestMapping("edit")
	@ResponseBody
	public Result getCustomerById(HttpServletRequest request, HttpServletResponse response,Pager<Order> pager, Order order) {
		Operator operator = super.getLoginUser(request);
		Result rb = new Result();
		if(null == operator){
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("用户未登录或会话超时");
			return rb;
		}
		try {
			order.setUserId(operator.getOperatorId().trim());
			rb = this.orderService.getOrderByIdList(pager,order);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			log.error("业务处理异常",e);
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("业务处理异常");
		}
		ss
		return rb;
	}
	
	/**
	 * 新增
	 * 
	 * @param request
	 * @param response
	 * @param pager
	 * @param order
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public String customerAdd(HttpServletRequest request, HttpServletResponse response,Pager<Order> pager, Order order) {
//		Operator operator = super.getLoginUser(request);
		Result rb = new Result();
		rb.setResultCode(Constants.RESULT_FAIL);
		rb.setResultMsg("此功能暂时不开放！敬请期待！");
		return "此功能暂时不开放！敬请期待！";
		/*if(null == operator){
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("用户未登录或会话超时");
			return "No";
		}
		Date currentTime = new Date();
		String out_trade_no = DateTimeUtil.getCurrentTimestamp() + this.sequenceService.getOrderNoSeqVal();
		try {
			order.setOrderId(out_trade_no);
			order.setCreatedTime(currentTime);
			order.setUserId(operator.getOperatorId().trim());
			this.orderService.addOrderAdd(pager,order);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			log.error("业务处理异常",e);
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("业务处理异常");
		}
		return "OK";*/
	}
	/**
	 * 保存
	 * 根据OrderId条件保存数据
	 * 
	 * @param request
	 * @param response
	 * @param pager
	 * @param order
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public String customerUpdate(HttpServletRequest request, HttpServletResponse response,Pager<Order> pager, Order order) {
//		Operator operator = super.getLoginUser(request);
		Result rb = new Result();
		rb.setResultCode(Constants.RESULT_FAIL);
		rb.setResultMsg("此功能暂时不开放！敬请期待！");
		return "此功能暂时不开放！敬请期待！";
		/*if(null == operator){
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("用户未登录或会话超时");
			return "No";
		}
		Date currentTime = new Date();//.getTime();
		try {
			order.setUpdatedTime(currentTime);
			order.setUserId(operator.getOperatorId().trim());
			this.orderService.updateOrderUpdate(pager,order);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			log.error("业务处理异常",e);
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("业务处理异常");
		}
		return "OK";*/
	}
	/**
	 * 删除
	 * 根据OrderId条件删除一条数据
	 * 
	 * @param request
	 * @param response
	 * @param pager
	 * @param order
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public String customerDelete(HttpServletRequest request, HttpServletResponse response,Pager<Order> pager, Order order) {
//		Operator operator = super.getLoginUser(request);
		Result rb = new Result();
		rb.setResultCode(Constants.RESULT_FAIL);
		rb.setResultMsg("此功能暂时不开放！敬请期待！");
		return "此功能暂时不开放！敬请期待！";
		/*if(null == operator){
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("用户未登录或会话超时");
//			int nrb = 01;
//			return nrb;
			return "No";
		}
		try {
			order.setUserId(operator.getOperatorId().trim());
			this.orderService.delOrderDelete(pager,order);
//			return grb; int grb = 
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			log.error("业务处理异常",e);
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("业务处理异常");
//			int nrb = 02;
//			return nrb;
		}
		return "OK";*/
	}
	/**
	 * 验证
	 * 
	 * @param request
	 * @param response
	 * @param pager
	 * @param order
	 * @return
	 */
	@RequestMapping("remote")
	@ResponseBody
	public boolean getCustomerRemote(HttpServletRequest request, HttpServletResponse response,Pager<Order> pager, Order order) {
		Operator operator = super.getLoginUser(request);
		Result rb = new Result();
		if(null == operator){
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("用户未登录或会话超时");
			return false;
		}
		try {
			order.setUserId(operator.getOperatorId().trim());
			rb = this.orderService.getOrderByIdRemote(pager,order);
			
			Object gorb = rb.getResultData();
			String text2 = JSON.toJSONString(gorb);
			JSONObject text = JSON.parseObject(text2);
			Object objte = text.get("beanList");
			String jfkhj = objte.toString();
			jfkhj = jfkhj.substring(1,jfkhj.length() - 1);
			JSONObject text4 = JSON.parseObject(jfkhj);
			if(null != text4.get("orderId")) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			log.error("业务处理异常",e);
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("业务处理异常");
			return false;
		}
	}
	/**
	 * 取投影仪设备ID
	 * 
	 * @param request
	 * @param response
	 * @param pager
	 * @param order
	 * @return
	 */
	@RequestMapping("remoteid")
	@ResponseBody
	public String getCustomerRemoteId(HttpServletRequest request, HttpServletResponse response,Pager<Order> pager, Order order) {
		Operator operator = super.getLoginUser(request);
		Result rb = new Result();
		if(null == operator){
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("用户未登录或会话超时");
			return "No";
		}
		try {
			order.setUserId(operator.getOperatorId().trim());
			rb = this.orderService.getOrderByIdRemote(pager,order);
			
			Object gorb = rb.getResultData();
			String text2 = JSON.toJSONString(gorb);
			JSONObject text = JSON.parseObject(text2);
			Object objte = text.get("beanList");
			String jfkhj = objte.toString();
			jfkhj = jfkhj.substring(1,jfkhj.length() - 1);
			JSONObject text4 = JSON.parseObject(jfkhj);
			return text4.get("orderId").toString();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			log.error("业务处理异常",e);
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("业务处理异常");
			return "No";
		}
	}
}
