package com.msy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msy.mapper.CategoryMapper;
import com.msy.mapper.MovieMapper;
import com.msy.model.Category;
import com.msy.service.CateGoryService;
@Service
@Transactional
public class CateGoryServiceImpl implements CateGoryService {

	
	@Autowired
	private CategoryMapper categorymapper;
	
	@Override
	public List<Category> getCategoryList() throws Exception {
		// TODO Auto-generated method stub
		return this.categorymapper.selectall();
	}

	@Override
	public List<Category> getCategoryListBycateid(String categoryid) throws Exception {
		// TODO Auto-generated method stub
		return this.categorymapper.selectforcategoryid(categoryid);
	}

}
