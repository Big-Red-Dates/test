package com.msy.model;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 
 * @author jung.chen add by 2018.03.20
 *
 */
public class OrderProduct implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1300793519199192458L;

	/**
     *订单商品ID
     */
    private String orderProductId;

    /**
     *商品ID
     */
    private String productId;

    /**
     *商品名称
     */
    private String productName;

    /**
     *时间长度
     */
    private Byte openTimeLength;

    /**
     *商品价格
     */
    private BigDecimal productPrice;

    /**
     *订单ID
     */
    private String orderId;


    public String getOrderProductId() {
        return orderProductId;
    }

   
    public void setOrderProductId(String orderProductId) {
        this.orderProductId = orderProductId;
    }

    
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

    
    public Byte getOpenTimeLength() {
        return openTimeLength;
    }

    
    public void setOpenTimeLength(Byte openTimeLength) {
        this.openTimeLength = openTimeLength;
    }

   
    public BigDecimal getProductPrice() {
        return productPrice;
    }

   
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    
    public String getOrderId() {
        return orderId;
    }

    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}