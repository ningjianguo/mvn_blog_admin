package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;

import com.comm.dao.BaseDaoImpl;
import com.dao.ICategory;
import com.entity.Category;
import com.service.ICategoryService;

@Service
public class CategoryServerImpl extends BaseDaoImpl<Category> implements ICategoryService {

	@Resource
	private ICategory categoryDaoImpl;

	public String getCategories() {
		return JSONArray.fromObject(queryAllInfo()).toString();
	}
}
