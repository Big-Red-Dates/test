package com.msy.model;

import java.math.BigDecimal;

public class EntityVo extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4172785591351858010L;
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
	
	public EntityVo(){}

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
