package com.msy.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * @author jung.chen add by 2018.03.20
 *
 */
public class Order extends Product{
    /**
	 *订单实体类 
	 */
	private static final long serialVersionUID = -9083686516307142358L;
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

    public String getOrderId() {
        return orderId;
    }


    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAllPrice() {
        return allPrice;
    }

    
    public void setAllPrice(BigDecimal allPrice) {
        this.allPrice = allPrice;
    }

   
    public BigDecimal getPayPrice() {
        return payPrice;
    }

   
    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    
    public Date getPayTime() {
        return payTime;
    }

    
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    
    public Byte getPayType() {
        return payType;
    }

    
    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    
    public Byte getPayStatus() {
        return payStatus;
    }

    
    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    
    public Byte getBusinessStatus() {
        return businessStatus;
    }

   
    public void setBusinessStatus(Byte businessStatus) {
        this.businessStatus = businessStatus;
    }

    
    public String getProjectorId() {
        return projectorId;
    }

    
    public void setProjectorId(String projectorId) {
        this.projectorId = projectorId;
    }

   
    public Date getCreatedTime() {
        return createdTime;
    }

   
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    
    public Date getUpdatedTime() {
        return updatedTime;
    }

    
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    
    public String getHouseId() {
        return houseId;
    }

    
    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

   
    public String getUserId() {
        return userId;
    }

    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    
    public Date getNotifyTime() {
        return notifyTime;
    }

    
    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    
    public int getRowflag() {
        return rowflag;
    }

    
    public void setRowflag(int rowflag) {
        this.rowflag = rowflag;
    }

   
    public String getRespCode() {
        return respCode;
    }

    
    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    
    public String getRespDesc() {
        return respDesc;
    }

    
    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }
    


	public String getStartDt() {
		return startDt;
	}


	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}


	public String getEndDt() {
		return endDt;
	}


	public void setEndDt(String string) {
		this.endDt = string;
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

	public Order() {
		// TODO 自动生成的构造函数存根
	}

	public Order(Object entity, int offset, int pageSize, String orderId, BigDecimal allPrice,
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


	


	/* （非 Javadoc）
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Order [startIndex=" + offset + ", endIndex=" + pageSize + ", entity=" + entity + ",offset=" + offset + ", pageSize=" + pageSize + ", orderId=" + orderId
				+ ", allPrice=" + allPrice + ", payPrice=" + payPrice + ", payTime=" + payTime + ", startDt=" + startDt
				+ ", endDt=" + endDt + ", payType=" + payType + ", payStatus=" + payStatus + ", businessStatus="
				+ businessStatus + ", projectorId=" + projectorId + ", createdTime=" + createdTime + ", updatedTime="
				+ updatedTime + ", houseId=" + houseId + ", userId=" + userId + ", notifyTime=" + notifyTime
				+ ", rowflag=" + rowflag + ", respCode=" + respCode + ", respDesc=" + respDesc + "]";
	}


}