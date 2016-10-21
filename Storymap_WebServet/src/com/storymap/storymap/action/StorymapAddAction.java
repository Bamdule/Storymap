package com.storymap.storymap.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.storymap.common.action.Action;
import com.storymap.common.dto.StorymapDto;
import com.storymap.common.service.StorymapService;
import com.storymap.util.JsonManager;
import com.storymap.util.TestStorymap;

public class StorymapAddAction implements Action {
	StorymapService smService = StorymapService.getInstance();
	JsonManager jsonManager = JsonManager.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String storymap_Json =request.getParameter("storymap");
		StorymapDto smDto=null;
		try{
			int mem_code = Integer.parseInt(request.getParameter("mem_code"));
			
			//받은 Json을 instance로 변환한다.
			smDto=(StorymapDto)jsonManager.jsonStringToInstance(storymap_Json,StorymapDto.class);
			System.out.println(smDto);
			boolean result=smService.insertStorymap(smDto, mem_code);
			
		}
		catch(NumberFormatException e){
			e.printStackTrace();
		}
	//	testAddStorymap();

		response.setContentType("charset=UTF-8");
		response.getWriter().print(smDto.getSm_code());
	}
	
	public void testAddStorymap(){
		int mem_code=1610041;
		StorymapService smService = StorymapService.getInstance();
		TestStorymap ts =new TestStorymap();
		StorymapDto smDto =ts.createStorymap(mem_code);
		smService.insertStorymap(smDto, mem_code);
	}

}
