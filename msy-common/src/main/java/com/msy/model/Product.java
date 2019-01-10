package com.msy.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author yanz.wu add by 2018.03.17
 *
 */
public class Product implements Serializable{

	/**
	 * 商品实体类
	 */
	private static final long serialVersionUID = 7283860766830499670L;

	/**
	 * 商品ID
	 */
	private String productId;
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 投影仪端运行开通的时间长度
	 */
	private Long openTimeLength;
	/**
	 * 产品价格
	 */
	private BigDecimal productPrice;
	
	public Product(){}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getOpenTimeLength() {
		return openTimeLength;
	}

	public void setOpenTimeLength(Long openTimeLength) {
		this.openTimeLength = openTimeLength;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	
	  
}
