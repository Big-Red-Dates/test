package com.msy.constant;

public class Constants {

	public static final String RESULT_SUCCESS = "0000";
	public static final String RESULT_FAIL = "0001";
	
	public static final String RESULTMO_SUCCESS = "200";
	public static final String RESULTMO_FAIL = "400";
	
	public final static String DefaultSessionIdName = "MSY_SID";
	public final static String LoginUser = "LoginUser-";
	public final static String UserSessionHeaderPrefix = "H-";
	public final static String UserSessionAttributePrefix = "A-";
	
	public static String AJAX_REQUEST_CHECKING = "AJAX_REQUEST_CHECKING";
	public final static String GSM_TOKEN = "MSY_TOKEN";
	public final static String GSM_SESSION = "MSY_SESSION";
	public final static String VerifyCode = "MSY_VC";
	public final static String VerifyCodeToken = "verifyCodeToken";
	public final static String RememberMeCookieName = "MSY_RM";
	
	/** 跨域默认方法名 */
	public final static String CROSS_DOMAIN_PARAMERNAME = "callback";
	
	
	public static class Order
	{
		/**
		 * 支付方式(0微信、1支付宝)
		 */
		public static int PAY_STYLE_WECHAT = 0;
		public static int PAY_STYLE_ALIPAY = 1;
		
		/**
		 * 支付状态(0未支付、1已支付)
		 */
		public static int PAY_STATUS_READY = 0;
		public static int PAY_STATUS_COMPLETE = 1;
		
		
		/**
		 * 业务支付状态(0待开通、1已扫码开通、2换房处理、3退款处理)
		 */
		public static int PAY_BUSINESS_STATUS_READY = 0;
		public static int PAY_BUSINESS_STATUS_COMPLETE = 1;
		public static int PAY_BUSINESS_STATUS_EXCHANGE_ROOM = 2;
		public static int PAY_BUSINESS_STATUS_RETURN = 3;
		
	}
}
