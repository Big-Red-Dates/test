package com.msy.controller.api.out;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.msy.config.WXConfig;
import com.msy.constant.Constants;
import com.msy.model.EntityVo;
import com.msy.model.Order;
import com.msy.model.OrderVo;
import com.msy.model.PayRecord;
import com.msy.model.Result;
import com.msy.model.RoomProjector;
import com.msy.netty.NettyChannelMap;
import com.msy.service.EntityService;
import com.msy.service.OrderService;
import com.msy.service.PayRecordService;
import com.msy.service.RoomProjactorService;
import com.msy.service.SequenceService;
import com.msy.utils.DateTimeUtil;
import com.msy.utils.RequestUtil;
import com.msy.utils.WXUtils;
import com.msy.utils.XmlUtil;
import com.msy.weixin.Signature;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.SocketChannel;

@RestController
@RequestMapping(value = "/api/out/wx" )
public class WXController {

	private static final Logger logger = Logger.getLogger(WXController.class);
	
	@Autowired 
	private OrderService orderService;
	@Autowired
	private EntityService entityService;
	@Autowired
	private PayRecordService payRecordService;
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private RoomProjactorService roomprojectorservice;
	@Autowired
	WXConfig wxConfig;
	
	private static final String RETURN_XML = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";

	@ResponseBody
	@RequestMapping(value = "/getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response){
		String url = wxConfig.getAuthUrl();
		String appid = wxConfig.getAppId();
		String redirectUri = wxConfig.getRediretUri();
		
		String roomId = request.getParameter("roomId");
		String entityId = request.getParameter("entityId");
		
		logger.info("code====>"+wxConfig.getAppId());
		

//		logger.info("code====>"+WXConfig.APP_ID+WXConfig.APP_SECRET);


		String out_trade_no = DateTimeUtil.getCurrentTimestamp() + this.sequenceService.getOrderNoSeqVal();

		logger.info("out_trade_no:"+out_trade_no);
		
		try {
			
			EntityVo vo = entityService.selectEntityVo(entityId);
			
			String price = String.valueOf(vo.getProductPrice().doubleValue());
			
			StringBuffer sbf = new StringBuffer();
			sbf.append(redirectUri);
			sbf.append("?");
			sbf.append("&out_trade_no="+out_trade_no);
			sbf.append("&roomId="+roomId);
			sbf.append("&entityId="+entityId);
			sbf.append("&price="+price);
			
			redirectUri = URLEncoder.encode(sbf.toString());
			logger.info("redirectUri ===>"+redirectUri);
			
			String uri = WXUtils.getAuthUrl(appid,url,redirectUri);
			response.sendRedirect(uri);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("调用异常",e);
		}
		
    }
	@ResponseBody
	@RequestMapping(value = "/payed")
	public Result payed(HttpServletRequest request, HttpServletResponse response) {

		Result result = new Result();
		String code = request.getParameter("code");
		String out_trade_no = request.getParameter("out_trade_no");
		String flag = request.getParameter("flag");
		String entityId = request.getParameter("entityId");
		boolean isRept = false;
		logger.info("payed code==>" + code + " out_trade_no==>" + out_trade_no + " flag==>" + flag + " entityId==>"+entityId);

		/**
		 * flag 1 支付成功 0支付失败
		 */
		Order order = null;
		if ("1".equals(flag)) {
			try {
				
				EntityVo vo = entityService.selectEntityVo(entityId);
				
				// 新增支付成功订单
				order = this.orderService.getOrderById(out_trade_no);
				PayRecord payed = new PayRecord();
				payed.setOrderId(out_trade_no);
				payed.setRoomId(order.getHouseId());
				payed.setCreatedTime(new Date());
				payed.setOrderRoomId(UUID.randomUUID().toString().replaceAll("-", ""));
				payed.setActiveTime(DateTimeUtil.getTimeByHour(vo.getOpenTimeLength().intValue()));// 设置有效时间
				int i = this.payRecordService.insert(payed);
				if (i > 0) {
					logger.info("新增支付记录成功==>" + out_trade_no);
				}
				RoomProjector roomprojector = this.roomprojectorservice.getRoomProjectorByroomId(order.getHouseId());
                String projectorid = roomprojector.getProjectorId();
				
                if(projectorid != null) {
	            	SocketChannel ctx = (SocketChannel) NettyChannelMap.get(projectorid);
	            	System.out.println(projectorid);
	            	if(ctx != null) {
	            		System.out.println("拿到map里面对应的projectorId的通道===="+ctx);
	            		ByteBuf bb = ctx.alloc().buffer(4);
	                    bb.writeInt(1);
	                    ctx.writeAndFlush(bb);
	            	}
	            }
			} catch (SQLIntegrityConstraintViolationException e1) {
				isRept = true;
				e1.printStackTrace();
				logger.error("重复异常", e1);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("新增支付记录异常", e);
			}
			// 更新订单状态
			if (!isRept) {
				try {
					order.setPayStatus((byte) Constants.Order.PAY_STATUS_COMPLETE);
					order.setBusinessStatus((byte) Constants.Order.PAY_BUSINESS_STATUS_COMPLETE);
					order.setUpdatedTime(new Date());
					this.orderService.updateOrderById(order);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("更新订单状态异常", e);
				}
			}

		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/pay")
    public Result pay(HttpServletRequest request, HttpServletResponse response ){
		Result result = new Result();
		result.setResultCode(Constants.RESULT_FAIL);
		result.setResultMsg("预支付失败");
		
		String code = request.getParameter("code");
		String out_trade_no = request.getParameter("out_trade_no");
		String roomId = request.getParameter("roomId");
		String entityId = request.getParameter("entityId");
		
		logger.info("pay code ==> "+code + " out_trade_no ==>"+out_trade_no + " roomId ==>"+roomId + " entityId ==>"+entityId );
		
		String  openid = WXUtils.getOpenId(code,this.wxConfig);
		
		if("".equals(openid)){
			return result;
		}
		
		logger.info("get openid ==>"+openid);
		String nonce_str = RequestUtil.getNonceStr();
		
		String notify_url = wxConfig.getNotifyUrl();
		
		StringBuffer sbf = new StringBuffer();
		sbf.append(notify_url);
		sbf.append("?");
		sbf.append("&out_trade_no="+out_trade_no);
		
		/**
		 * 下订单
		 */
		String productName = "";
		int total_fee = 0;
		try{
			Result r = this.orderService.add(entityId, roomId,out_trade_no);
			if(!r.getResultCode().equals(Constants.RESULT_SUCCESS)){
				result.setResultMsg("下订单失败");
				return result;
			}
			EntityVo vo = (EntityVo) r.getResultData();
			productName = vo.getProductName();
			total_fee = (int)(vo.getProductPrice().doubleValue() * 100);
		}catch(Exception e){
			result.setResultMsg("下订单失败");
			return result;
		}
		
		/**
		 * 发起支付交易
		 */
		Map<String, String> map = WXUtils.payUnifiedorder(productName, total_fee, wxConfig.getIp(), out_trade_no, sbf.toString(), nonce_str, openid,wxConfig);
		logger.info("map==>"+map);
		if (map != null) {
			for(String s: map.keySet()){
				logger.info(String.format("%s=%s,", s, map.get(s)));
			}
			if (map.containsKey("result_code")) {
				if (map.get("result_code").equals("SUCCESS")) {
					String appId = map.get("appid");
					String prepay_id = map.get("prepay_id");
					String timeStamp = Long.toString(System.currentTimeMillis() / 1000);
					Map<String, Object> pmap = new HashMap<String,Object>();
					pmap.put("appId", appId);
					pmap.put("timeStamp", timeStamp);
					pmap.put("nonceStr", nonce_str);
					pmap.put("signType", "MD5");
					pmap.put("package", "prepay_id="+prepay_id);
					String paySign = Signature.getSign(pmap,wxConfig.getMerchatIdKey());
					
					Map<String, String> ret = new HashMap<String,String>();
					
					ret.put("appId", appId);
					ret.put("timeStamp",timeStamp);
					ret.put("nonceStr", nonce_str);
					ret.put("prepay_id", prepay_id);
					ret.put("signType", "MD5");
					
					ret.put("paySign", paySign);

					result.setResultData(ret);
					
					result.setResultCode(Constants.RESULT_SUCCESS);
					result.setResultMsg("预支付成功");
				} 
			}
		}
    
		return result;
	}
	@ResponseBody
	@RequestMapping(value = "/toPay")
	public void toPay(HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("code");
		String out_trade_no = request.getParameter("out_trade_no");
		String roomId = request.getParameter("roomId");
		String entityId = request.getParameter("entityId");
		
		logger.info("toPay code ==> " + code + " out_trade_no ==>" + out_trade_no
				+ " roomId ==>" + roomId + " entityId ==>" + entityId
				);
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/front/weixin/pay.html"
					);
			dispatcher.forward(request, response);
			
			logger.info("请求转发==>" + "/front/weixin/pay.html");
		} catch (ServletException e) {
			e.printStackTrace();
			logger.info("请求转发==>/front/weixin/pay.html  异常");
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("请求转发==>/front/weixin/pay.html  异常");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/notify")
    public void notify(HttpServletRequest request, HttpServletResponse response ){
		logger.info("notify<===start");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			Date date = new Date();
			
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
	        response.setHeader("Content-type", "text/html;charset=UTF-8");
	        String resString = XmlUtil.parseRequst(request);
	        logger.info(String.format("notify.html===>resString=%s", resString));
	        
	        String respString = "FAIL";
	        
            if (resString != null && !"".equals(resString)) {
                Map<String, String> map = XmlUtil.toMap(resString.getBytes(), "utf-8");
                String result_code = map.get("result_code");
                String return_code = map.get("return_code");
                if (return_code != null && result_code != null && "SUCCESS".equals(result_code) && "SUCCESS".equals(return_code)) {
                	String out_trade_no = map.get("out_trade_no");
                    String transaction_id = map.get("transaction_id");
                    logger.info("success by out_trade_no==>"+out_trade_no +" transaction_id==>"+transaction_id);
                    
                    //更新订单状态
                    OrderVo order = this.orderService.getOrderVoById(out_trade_no);
                    order.setRespDesc(resString);
                    order.setRespCode("SUCCESS");
                    order.setPayStatus((byte) Constants.Order.PAY_STATUS_COMPLETE);
					order.setBusinessStatus((byte) Constants.Order.PAY_BUSINESS_STATUS_COMPLETE);
					order.setUpdatedTime(date);
					order.setNotifyTime(date);
                    this.orderService.updateOrderById(order);
                    
                    logger.debug("activeTime:"+order.getOpenTimeLength().intValue());
                    
                    //新增支付成功订单
                    PayRecord payed = new PayRecord();
                    payed.setOrderId(out_trade_no);
                    payed.setRoomId(order.getHouseId());
                    payed.setCreatedTime(new Date());
                    payed.setOrderRoomId(UUID.randomUUID().toString().replaceAll("-", ""));
                    payed.setActiveTime(DateTimeUtil.getTimeByHour(order.getOpenTimeLength().intValue()));//设置有效时间
                    int i = this.payRecordService.insert(payed);
                    
                    if(i > 0){
                    	respString = RETURN_XML;
                    }
                    
                    writer.print(RETURN_XML);
                    logger.info("notify<===end");
                    writer.flush();
                }
            }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch (SQLIntegrityConstraintViolationException e1) {
			e1.printStackTrace();
			logger.error("重复异常", e1);
            writer.print(RETURN_XML);
            writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			writer.close();
		}
        
	}
}
