package com.storymap.storynote.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.storymap.common.action.Action;
import com.storymap.common.dto.StorynoteDto;
import com.storymap.common.service.StorynoteService;
import com.storymap.util.JsonManager;

public class StorynoteListAction implements Action {
	JsonManager jsonManager = JsonManager.getInstance();
	StorynoteService snService = StorynoteService.getInstance();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mem_code = Integer.parseInt(request.getParameter("mem_code"));
		List<StorynoteDto> storynoteList=snService.selectAllStorynote(mem_code);

		
	    String jsonString = jsonManager.instanceToJsonString(storynoteList);
		
		System.out.println(jsonString);

		response.setContentType("charset=UTF-8");
		response.getWriter().print(jsonString);

	}

}
