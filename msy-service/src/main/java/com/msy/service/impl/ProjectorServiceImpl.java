package com.msy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msy.annotation.DataSource;

import com.msy.mapper.ProjectorMapper;
import com.msy.model.Entity;
import com.msy.model.Projector;
import com.msy.model.Result;
import com.msy.service.ProjectorService;
import com.msy.utils.Pager;
import com.msy.utils.PagerEntity;

//@Service 一定要留，他可以在类路径底下寻找标注了@Component,@Service,@Controller,@Repository注解的类，并把这些类纳入进spring容器中管理。
@Service
@Transactional
@DataSource("ds_master")
public class ProjectorServiceImpl implements ProjectorService {

	@Autowired
	private ProjectorMapper projectorMapper;

	@Override
	public Projector getProjectorById(String projectorId) throws Exception {
		
		return this.projectorMapper.selectByPrimaryKey(projectorId);
	}
	
	@Override
	public void add(Projector projector) throws Exception {
		
		this.projectorMapper.insert(projector);
	}

	@Override
	public void deleteProjectorById(String projectorId) throws Exception {
		
		this.projectorMapper.deleteByPrimaryKey(projectorId);
	}

	@Override
	public void updateProjectorById(Projector projector) throws Exception {
		
		this.projectorMapper.updateByPrimaryKey(projector);
	}

	@Override
	public List<Projector> getAllProjector() throws Exception {
		// TODO Auto-generated method stub
		
		
		return this.projectorMapper.selectall();
	
	
	}

}
