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

public class StorymapListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mem_code = Integer.parseInt(request.getParameter("mem_code"));
		//int mem_code =1610041;
		JsonManager jsonManager = JsonManager.getInstance();
		StorymapService smService=StorymapService.getInstance();
		
		//mem_code와 맞는 StorymapList를 DB에서 가져온다.
		List<StorymapDto> storymapList= smService.selectAllStorymap(mem_code);
		String JsonString=null;
		if(storymapList!=null)
			JsonString = jsonManager.instanceToJsonString(storymapList);
		
		System.out.println(JsonString);

		response.setContentType("charset=UTF-8");
		response.getWriter().print(JsonString);

	}

}
