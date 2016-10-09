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

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	/*	String storymap_Json =request.getParameter("storymap");
		int mem_code = Integer.parseInt(request.getParameter("mem_code"));
		JsonManager jsonManager =JsonManager.getInstance();
		StorymapDto smDto=(StorymapDto)jsonManager.jsonStringToInstance(storymap_Json);
		
		System.out.println(smDto);
		StorymapService smService = StorymapService.getInstance();
		
		boolean result=smService.insertStorymap(smDto, mem_code);*/
		JsonManager jsonManager = JsonManager.getInstance();
		int mem_code=1609262;
		StorymapService smService = StorymapService.getInstance();
		TestStorymap ts =new TestStorymap();
		String storymapString = ts.requestStorymap(mem_code);
		
		StorymapDto sDto =(StorymapDto)jsonManager.jsonStringToInstance(storymapString);
		smService.insertStorymap(sDto, mem_code);
		
		
	    StorymapDto smDto = smService.selectStorymap(sDto.getSm_code());
		System.out.println(smDto);
		List<StorymapDto> smList=smService.selectAllStorymap(mem_code);
		
		for(StorymapDto Dto : smList){
			smService.storymapView(Dto);
		}
	}

}
