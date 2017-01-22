package com.action;


import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.comm.action.BaseAction;
import com.entity.Category;
import com.service.ICategoryService;



@Controller
@Scope("prototype")
public class CategoryAction extends BaseAction<Category>
{
	private static final long serialVersionUID = 1L;
	@Resource
	ICategoryService cateICategoryService;
	String categories;
	
	public String category(){
		categories = cateICategoryService.getCategories();
		return "category";
	}
	
	public String getCategories() {
		return categories;
	}
	
}
