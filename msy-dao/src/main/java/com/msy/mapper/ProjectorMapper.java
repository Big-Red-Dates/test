package com.msy.mapper;

import java.util.List;

import com.msy.model.Projector;
import com.msy.utils.PagerEntity;
import com.msy.model.Projector;


public interface ProjectorMapper {
	/**
     * 通过投影仪ID查询投影仪
     * @param productId
     * @return
     */
    Projector selectByPrimaryKey(String projectorId);
    
    /**
     * 通过投影仪ID查询投影仪
     * @param productId
     * @return
     */
    List<Projector> selectall();
    
    /**
     * 添加投影仪
     * @param productId
     * @return
     */
    int insert(Projector projector);
    
    
    /**
     * 通过投影仪Id删除投影仪
     * @param projectorId
     * @return
     */
    int deleteByPrimaryKey(String projectorId);
    
    /**
     * 根据投影仪ID更新投影仪数据（替换投影仪对象）
     * @param record
     * @return
     */
    int updateByPrimaryKey(Projector record);
}
