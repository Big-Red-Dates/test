package com.msy.service;

import java.util.Map;

import com.msy.model.AgentUser;
import com.msy.model.AgentUserVo;
import com.msy.model.OrderVo;
import com.msy.model.Result;
import com.msy.utils.Pager;

public interface AgentUserService {

    int deleteByPrimaryKey(String agentId);

    int insert(AgentUser record);

    int insertSelective(AgentUser record);

    AgentUser selectByPrimaryKey(String agentId);

    int updateByPrimaryKeySelective(AgentUser record);

    int updateByPrimaryKey(AgentUser record);
    
    Map selectAllByAgentId(String agentId);

    /**
     * 分页查询统计
     * @param pager
     * @param agentUserVo
     * @return
     */
	Result search(Pager<OrderVo> pager, AgentUserVo agentUserVo);
    
}
