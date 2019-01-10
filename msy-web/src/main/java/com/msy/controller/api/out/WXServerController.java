package com.msy.controller.api.out;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;


import com.msy.utils.wxpublic.SignUtil;
import com.msy.utils.wxpublic.service.CoreService;




@RestController
@RequestMapping(value = "/api/out" )
public class WXServerController {

	private static final Logger logger = Logger.getLogger(WXServerController.class);
	
	@RequestMapping(value = "/wechat",method = RequestMethod.GET)
    public void doget(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub
		  String signature = request.getParameter("signature");
	        // 时间戳
	        String timestamp = request.getParameter("timestamp");
	        // 随机数
	        String nonce = request.getParameter("nonce");
	        // 随机字符串
	        String echostr = request.getParameter("echostr");
	        
	        
	        System.out.println(timestamp);
	        PrintWriter out = response.getWriter();
	 
	        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
	        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
	            out.print(echostr);
	        }
	        System.out.println("...");
	        out.close();
	        out = null;
    }
	
	@RequestMapping(value = "/wechat",method = RequestMethod.POST)
    public void dopost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub
		/**
	     * 处理微信服务器发来的消息
	     */
	        // 消息的接收、处理、响应
	        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
	        request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");

	        // 调用核心业务类接收消息、处理消息
	        String respXml = CoreService.processRequest(request);
	       
	        // 响应消息
	        PrintWriter out = response.getWriter();
	        out.print(respXml);
	        out.close();
	    
    }
}
