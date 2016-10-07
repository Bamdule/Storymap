package com.storymap.storymap.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.storymap.common.action.Action;
import com.storymap.common.dto.StorymapDto;
import com.storymap.common.service.StorymapService;
import com.storymap.util.JsonManager;

public class StorymapDetailAction implements Action  {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sm_code = request.getParameter("sm_code");
		JsonManager jsonManager = new JsonManager();
		StorymapService smService = StorymapService.getInstance();
		
		StorymapDto smDto=smService.selectStorymap(sm_code);
		String Storymap_jsonString = jsonManager.instanceToJsonString(smDto);
		
		System.out.println(Storymap_jsonString);

		response.setContentType("charset=UTF-8");
		response.getWriter().print(Storymap_jsonString);
	}

}