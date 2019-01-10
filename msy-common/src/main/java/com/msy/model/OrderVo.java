package com.msy.model;

import java.math.BigDecimal;

public class OrderVo extends Order{

	/**
	 * 
	 */
	private static final long serialVersionUID = 847726536239416398L;

	/**
     *房间ID
     */
    private String roomId;

    /**
     * 房间号码
     */
    private String roomNo;

    /**
     *房间名称
     */
    private String roomName;

    /**
     *实体ID
     */
    private String entityId;
    
    /**
     * 实体名称
     */
    private String entityName;

    /**
     * 实体类型
     */
    private Byte entityType;

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 城市
     */
    private String city;

    /**
     * 省份
     */
    private String province;
    
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
	
	private int total;
	
	private BigDecimal allPrice;
	
    public OrderVo(){
    	
    }

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Byte getEntityType() {
		return entityType;
	}

	public void setEntityType(Byte entityType) {
		this.entityType = entityType;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
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

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public BigDecimal getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(BigDecimal allPrice) {
		this.allPrice = allPrice;
	}

}
