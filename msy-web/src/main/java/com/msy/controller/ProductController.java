package com.msy.controller;


import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msy.constant.Constants;
import com.msy.model.Product;
import com.msy.model.Result;
import com.msy.service.ProductService;

@RestController
@RequestMapping(value = "/api/out" )
public class ProductController {

	private static final Logger logger = Logger.getLogger(ProductController.class);
	
	final Base64 base64 = new Base64();
	
	@Autowired
	private ProductService productService;
	
	/**
	 * 获取商品信息
	 * @param productId
	 * @return
	 */
	@RequestMapping(value = "/product/getInfo")
    public Result sendData(@RequestParam(value="sendData") String sendData ){
		Result result = new Result();
		try {
			Product p = this.productService.getProductById(sendData);
			result.setResultData(p);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(Constants.RESULT_FAIL);
			result.setResultMsg("查询产品异常");
			return result;
		}
    }
	/*
	@RequestMapping(value = "/product/getInfo2")
    public Result sendData2(@RequestParam(value="sendData") String sendData ){
		logger.info("加密数据:"+sendData);
		
		Result result = new Result();
		String decodeResult;
		try {
			decodeResult = new String(base64.decode(sendData), "UTF-8");
			logger.info("解密内容:"+decodeResult);
			
			JSONObject json = JSONObject.parseObject(decodeResult);
			Product p = this.productService.getProductById(json.get("productId").toString());
			result.setResultData(p);
			return result;
		}catch (UnsupportedEncodingException e1) {
			logger.error("加解密数据异常",e1);
			result.setResultCode(Constants.RESULT_FAIL);
			result.setResultMsg("加解密数据异常");
			return result;
		} catch (Exception e) {
			logger.error("查询产品异常",e);
			result.setResultCode(Constants.RESULT_FAIL);
			result.setResultMsg("查询产品异常");
			return result;
		}
    }*/
}
