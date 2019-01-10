package com.msy.mapper;

import java.util.List;

import com.msy.model.AgentUser;
import com.msy.model.Order;
import com.msy.model.OrderVo;
import com.msy.utils.PagerEntity;

public interface AgentUserMapper {
    int deleteByPrimaryKey(String agentId);

    int insert(AgentUser record);

    int insertSelective(AgentUser record);

    AgentUser selectByPrimaryKey(String agentId);

    int updateByPrimaryKeySelective(AgentUser record);

    int updateByPrimaryKey(AgentUser record);
    
    List<Order> selectAllByAgentId(String agentId);
    
    /**
	 * 分页查询
	 * 
	 * @param pagerEntity
	 * @return
	 */
	public List<OrderVo> search(PagerEntity pagerEntity);

	/**
	 * 统计
	 * 
	 * @param pagerEntity
	 * @return
	 */
	public Long count(PagerEntity pagerEntity);
}