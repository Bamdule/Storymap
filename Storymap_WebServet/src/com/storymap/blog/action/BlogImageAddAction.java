package com.storymap.blog.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.storymap.common.action.Action;
import com.storymap.common.service.CommonService;
import com.storymap.common.vo.Country;
import com.storymap.util.JsonManager;

public class BlogImageAddAction implements Action {

	CommonService commonService=CommonService.getInstance();
	JsonManager jsonManager =JsonManager.getInstance();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Country country= commonService.selectAllCityList();
		String JsonString=null; 
		if(country!=null)
			JsonString = jsonManager.instanceToJsonString(country);
		
		response.setCharacterEncoding("UTF-8");
		if(JsonString!=null)
			response.getWriter().print(JsonString);
		else
			response.getWriter().print("none");

	}

}
