package com.msy.mapper;

import com.msy.model.EntityUser;

public interface EntityUserMapper {
    int deleteByPrimaryKey(String entityUserId);

    int insert(EntityUser record);

    int insertSelective(EntityUser record);

    EntityUser selectByPrimaryKey(String entityUserId);

    int updateByPrimaryKeySelective(EntityUser record);

    int updateByPrimaryKey(EntityUser record);
}