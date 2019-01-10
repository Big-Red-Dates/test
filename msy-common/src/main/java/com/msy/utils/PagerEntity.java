package com.msy.utils;

import java.math.BigDecimal;
import java.util.Date;

public class PagerEntity
{
	private int offset;
	private int pageSize;
	private Object entity;
	
	/**
     *订单ID
     */
    private String orderId;

    /**
     *订单总价
     */
    private BigDecimal allPrice;

    /**
     *支付金额
     */
    private BigDecimal payPrice;

    /**
     *支付时间
     */
    private Date payTime;
    
    /**
     *支付时间起
     */
    private String startDt;
    /**
     *支付时间结
     */
    private String endDt;
    
    /**
     *支付方式
     */
    private Byte payType;

    /**
     *支付状态
     */
    private Byte payStatus;

    /**
     *业务支付状态
     */
    private Byte businessStatus;

    /**
     *投影仪ID
     */
    private String projectorId;

    /**
     *创建时间
     */
    private Date createdTime;

    /**
     *更新时间
     */
    private Date updatedTime;

    /**
     *房间标识
     */
    private String houseId;

    /**
     *用户标识
     */
    private String userId;

    /**
     *异步通知时间
     */
    private Date notifyTime;

    /**
     *记录是否有效
     */
    private int rowflag;

    /**
     *应答码
     */
    private String respCode;

    /**
     *应答码描述
     */
    private String respDesc;
	public PagerEntity()
	{
	}
	public PagerEntity(Object entity, int offset, int pageSize)
	{
		this.entity = entity;
		this.offset = offset;
		this.pageSize = pageSize;
	}
	public PagerEntity(Object entity, int offset, int pageSize, String orderId, BigDecimal allPrice,
			BigDecimal payPrice, Date payTime, String startDt, String endDt, Byte payType, Byte payStatus,
			Byte businessStatus, String projectorId, Date createdTime, Date updatedTime, String houseId, String userId,
			Date notifyTime, int rowflag, String respCode, String respDesc) {
		super();
		this.entity = entity;
		this.offset = offset;
		this.pageSize = pageSize;
		this.orderId = orderId;
		this.allPrice = allPrice;
		this.payPrice = payPrice;
		this.payTime = payTime;
		this.startDt = startDt;
		this.endDt = endDt;
		this.payType = payType;
		this.payStatus = payStatus;
		this.businessStatus = businessStatus;
		this.projectorId = projectorId;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.houseId = houseId;
		this.userId = userId;
		this.notifyTime = notifyTime;
		this.rowflag = rowflag;
		this.respCode = respCode;
		this.respDesc = respDesc;
	}

	public int getOffset()
	{
		return offset;
	}

	public void setOffset(int offset)
	{
		this.offset = offset;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public Object getEntity()
	{
		return entity;
	}

	public void setEntity(Object entity)
	{
		this.entity = entity;
	}

	/**
	 * @return orderId
	 */
	public synchronized String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId 要设置的 orderId
	 */
	public synchronized void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return allPrice
	 */
	public synchronized BigDecimal getAllPrice() {
		return allPrice;
	}

	/**
	 * @param allPrice 要设置的 allPrice
	 */
	public synchronized void setAllPrice(BigDecimal allPrice) {
		this.allPrice = allPrice;
	}

	/**
	 * @return payPrice
	 */
	public synchronized BigDecimal getPayPrice() {
		return payPrice;
	}

	/**
	 * @param payPrice 要设置的 payPrice
	 */
	public synchronized void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
	}

	/**
	 * @return payTime
	 */
	public synchronized Date getPayTime() {
		return payTime;
	}

	/**
	 * @param payTime 要设置的 payTime
	 */
	public synchronized void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	/**
	 * @return startDt
	 */
	public synchronized String getStartDt() {
		return startDt;
	}

	/**
	 * @param startDt 要设置的 startDt
	 */
	public synchronized void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	/**
	 * @return endDt
	 */
	public synchronized String getEndDt() {
		return endDt;
	}

	/**
	 * @param endDt 要设置的 endDt
	 */
	public synchronized void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	/**
	 * @return payType
	 */
	public synchronized Byte getPayType() {
		return payType;
	}

	/**
	 * @param payType 要设置的 payType
	 */
	public synchronized void setPayType(Byte payType) {
		this.payType = payType;
	}

	/**
	 * @return payStatus
	 */
	public synchronized Byte getPayStatus() {
		return payStatus;
	}

	/**
	 * @param payStatus 要设置的 payStatus
	 */
	public synchronized void setPayStatus(Byte payStatus) {
		this.payStatus = payStatus;
	}

	/**
	 * @return businessStatus
	 */
	public synchronized Byte getBusinessStatus() {
		return businessStatus;
	}

	/**
	 * @param businessStatus 要设置的 businessStatus
	 */
	public synchronized void setBusinessStatus(Byte businessStatus) {
		this.businessStatus = businessStatus;
	}

	/**
	 * @return projectorId
	 */
	public synchronized String getProjectorId() {
		return projectorId;
	}

	/**
	 * @param projectorId 要设置的 projectorId
	 */
	public synchronized void setProjectorId(String projectorId) {
		this.projectorId = projectorId;
	}

	/**
	 * @return createdTime
	 */
	public synchronized Date getCreatedTime() {
		return createdTime;
	}

	/**
	 * @param createdTime 要设置的 createdTime
	 */
	public synchronized void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * @return updatedTime
	 */
	public synchronized Date getUpdatedTime() {
		return updatedTime;
	}

	/**
	 * @param updatedTime 要设置的 updatedTime
	 */
	public synchronized void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	/**
	 * @return houseId
	 */
	public synchronized String getHouseId() {
		return houseId;
	}

	/**
	 * @param houseId 要设置的 houseId
	 */
	public synchronized void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	/**
	 * @return userId
	 */
	public synchronized String getUserId() {
		return userId;
	}

	/**
	 * @param userId 要设置的 userId
	 */
	public synchronized void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return notifyTime
	 */
	public synchronized Date getNotifyTime() {
		return notifyTime;
	}

	/**
	 * @param notifyTime 要设置的 notifyTime
	 */
	public synchronized void setNotifyTime(Date notifyTime) {
		this.notifyTime = notifyTime;
	}

	/**
	 * @return rowflag
	 */
	public synchronized int getRowflag() {
		return rowflag;
	}

	/**
	 * @param rowflag 要设置的 rowflag
	 */
	public synchronized void setRowflag(int rowflag) {
		this.rowflag = rowflag;
	}

	/**
	 * @return respCode
	 */
	public synchronized String getRespCode() {
		return respCode;
	}

	/**
	 * @param respCode 要设置的 respCode
	 */
	public synchronized void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	/**
	 * @return respDesc
	 */
	public synchronized String getRespDesc() {
		return respDesc;
	}

	/**
	 * @param respDesc 要设置的 respDesc
	 */
	public synchronized void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}

	/* （非 Javadoc）
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PagerEntity [startIndex=" + offset + ", endIndex=" + pageSize + ", entity=" + entity + ", offset=" + offset + ", pageSize=" + pageSize + ", orderId="
				+ orderId + ", allPrice=" + allPrice + ", payPrice=" + payPrice + ", payTime=" + payTime + ", startDt="
				+ startDt + ", endDt=" + endDt + ", payType=" + payType + ", payStatus=" + payStatus
				+ ", businessStatus=" + businessStatus + ", projectorId=" + projectorId + ", createdTime=" + createdTime
				+ ", updatedTime=" + updatedTime + ", houseId=" + houseId + ", userId=" + userId + ", notifyTime="
				+ notifyTime + ", rowflag=" + rowflag + ", respCode=" + respCode + ", respDesc=" + respDesc + "]";
	}
	
}
