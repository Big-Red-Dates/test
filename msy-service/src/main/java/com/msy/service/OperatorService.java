package com.msy.service;

import com.msy.model.Operator;

public interface OperatorService {

	/**
	 * 删除操作员
	 * @param operatorId
	 * @return
	 */
    int deleteByPrimaryKey(String operatorId);

    /**
     * 新增操作员
     * @param record
     * @return
     */
    int insert(Operator record);

    /**
     * 新增操作员（非空)
     * @param record
     * @return
     */
    int insertSelective(Operator record);

    /**
     * 根据ID查询操作员
     * @param operatorId
     * @return
     */
    Operator selectByPrimaryKey(String operatorId);

    /**
     * 根据操作员名称查询操作员
     * @param operatorId
     * @return
     */
    Operator selectByName(String operatorId);
    
    /**
     * 非空更新操作员
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Operator record);

    /**
     * 更新操作员
     * @param record
     * @return
     */
    int updateByPrimaryKey(Operator record);

}
