package com.msy.service;

import java.util.List;

import com.msy.model.PayRecord;

/**
 * 
 * @author yan.wu by 2018.0407
 *
 */
public interface PayRecordService {


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
