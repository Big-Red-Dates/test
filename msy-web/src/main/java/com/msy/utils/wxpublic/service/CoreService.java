
package com.msy.utils.wxpublic.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.msy.netty.NettyUtil;
import com.msy.utils.wxpublic.MessageUtil;
import com.msy.weixin.message.TextMessage;


public class CoreService {
	
	private static Log log = LogFactory.getLog(CoreService.class);
    
	
	 public static String processRequest(HttpServletRequest request) {
	        // xml格式的消息数据
	        String respXml = "success";
	        // 默认返回的文本消息内容
	        String respContent = "欢迎关注码上影";
	        try {
	            // 调用parseXml方法解析请求消息
	            Map<String, String> requestMap = MessageUtil.parseXml(request);
	            // 发送方帐号
	            
	            String fromUserName = requestMap.get("FromUserName");
	            // 开发者微信号
	            String toUserName = requestMap.get("ToUserName");
	            // 消息类型
	            String msgType = requestMap.get("MsgType");
	            
	            String eventkey = requestMap.get("EventKey").replace("qrscene_", "");
	            
	            // 回复文本消息
	            TextMessage textMessage = new TextMessage();
	            textMessage.setToUserName(fromUserName);
	            textMessage.setFromUserName(toUserName);
	            textMessage.setCreateTime(new Date().getTime());
	            textMessage.setMsgType("text");
	            
	            if(eventkey != null) {
	            	NettyUtil.send(eventkey, 2);
	            }
	          
	            log.debug(eventkey);
	            log.debug(msgType);
	            log.debug(fromUserName+","+toUserName);
	            
	            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
	                // 事件类型
	                String eventType = requestMap.get("Event");
	                log.debug(eventType);
	                // 关注
	                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
	                	log.debug("谢谢关注");
	                	// 设置文本消息的内容
	    	            textMessage.setContent(respContent);
	    	            // 将文本消息对象转换成xml
	    	            respXml = MessageUtil.messageToXml(textMessage);
	                	
	                }
	                // 取消关注
	                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
	                    // TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
	                }
	                // 扫描带参数二维码
	                else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
	                    // TODO 处理扫描带参数二维码事件
	                	log.debug("已关注");
	                }
	            }
	            
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return respXml;
	    }	
	 
}

