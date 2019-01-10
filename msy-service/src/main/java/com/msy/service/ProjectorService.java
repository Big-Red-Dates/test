package com.msy.service;

import java.util.List;

import com.msy.model.Entity;
import com.msy.model.Projector;
import com.msy.model.Result;
import com.msy.utils.Pager;

public interface ProjectorService {
	/**
	 * 通过投影仪Id，拿到投影仪对象
	 * @param projectorId
	 * @return
	 */
	Projector getProjectorById(String projectorId) throws Exception;
	
	
	/**
     * 分页查询投影仪列表
	 * @param pager 
     * @return
     */
     List<Projector> getAllProjector()  throws Exception;
	
	/**
	 * 添加投影仪对象
	 * @param projectorId
	 * @return
	 */
	void add(Projector projector) throws Exception;
	
	/**
	 * 通过投影仪ID删除投影仪
	 * @param projectorId
	 * @throws Exception
	 */
	void deleteProjectorById(String projectorId) throws Exception;

	/**
	 * 通过投影仪Id更新投影仪数据
	 * @param projector
	 * @throws Exception
	 */
	void updateProjectorById(Projector projector) throws Exception;
}
