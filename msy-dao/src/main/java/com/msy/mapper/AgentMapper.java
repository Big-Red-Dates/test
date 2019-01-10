package com.msy.mapper;

import com.msy.model.Agent;

public interface AgentMapper {
    int deleteByPrimaryKey(String agentId);

    int insert(Agent record);

    int insertSelective(Agent record);

    Agent selectByPrimaryKey(String agentId);

    int updateByPrimaryKeySelective(Agent record);

    int updateByPrimaryKey(Agent record);
}