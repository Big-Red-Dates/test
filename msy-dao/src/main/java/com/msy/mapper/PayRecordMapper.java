package com.msy.mapper;

import java.util.List;

import com.msy.model.PayRecord;

/**
 * 
 * @author yanz.wu
 *
 */
public interface PayRecordMapper {

	/**
	 * 新增订单记录
	 * @param record
	 * @return
	 */
    int insert(PayRecord record);

    /**
     * 查询订单记录
     * @param orderRoomId
     * @return
     */
    PayRecord selectByPrimaryKey(String orderRoomId);

    /**
     * 查询所有
     * @return
     */
    List<PayRecord> selectAll();
}