package com.msy.model;

import java.io.Serializable;

public class Entity implements Serializable{

    /**
	 * （酒店或会所）实体类
	 */
	private static final long serialVersionUID = -2350326045912770577L;

    /**
    *实体Id
    */
	private String entityId;

    /**
     *实体名称
     */
    private String entityName;

    /**
     *实体类型
     */
    private Byte entityType;

    /**
     *产品Id
     */
    private String productId;

    /**
     *所在城市
     */
    private String city;

    /**
     *所在省份
     */
    private String province;

    public String getEntityId() {
        return entityId;
    }


    public void setEntityId(String entityId) {
        this.entityId = entityId == null ? null : entityId.trim();
    }


    public String getEntityName() {
        return entityName;
    }


    public void setEntityName(String entityName) {
        this.entityName = entityName == null ? null : entityName.trim();
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
        this.productId = productId == null ? null : productId.trim();
    }


    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }


    public String getProvince() {
        return province;
    }


    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }
}