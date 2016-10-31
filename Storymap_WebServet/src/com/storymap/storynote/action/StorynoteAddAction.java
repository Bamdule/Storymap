package com.storymap.storynote.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.storymap.common.action.Action;
import com.storymap.common.dto.StorynoteDto;
import com.storymap.common.service.StorynoteService;
import com.storymap.util.JsonManager;

public class StorynoteAddAction implements Action{

	StorynoteService storynoteService=StorynoteService.getInstance();
	JsonManager jsonManager= JsonManager.getInstance();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int mem_code = Integer.parseInt(request.getParameter("mem_code"));
		String jsonString = request.getParameter("storynote");
		String sn_code = null;
		boolean result =false;
		StorynoteDto snDto = (StorynoteDto)jsonManager.jsonStringToInstance(jsonString, StorynoteDto.class);
		System.out.println("snDto : " +snDto);
		if(snDto!=null){
			sn_code=storynoteService.createNextStorynoteCode(mem_code);
			if(sn_code!=null){
				result=storynoteService.insertStorynote(mem_code, sn_code, snDto);
				storynoteService.insertStorymapOfStorynote(sn_code, snDto.getStorymap_codes());
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(sn_code);
	}
	

}
