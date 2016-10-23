package com.storymap.storynote.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.storymap.common.action.Action;
import com.storymap.common.dto.StorynoteDto;
import com.storymap.common.service.StorynoteService;
import com.storymap.util.JsonManager;

public class StorynoteDetailAction implements Action {

	StorynoteService snService =StorynoteService.getInstance();
	JsonManager jsonManager = JsonManager.getInstance();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sn_code = request.getParameter("sn_code");
		StorynoteDto snDto = snService.selectStorynote(sn_code);
		String jsonString ="none";
		if(snDto!=null){
			jsonString=jsonManager.instanceToJsonString(snDto);
		}
		System.out.println(jsonString);
		
		response.setContentType("charset=UTF-8");
		response.getWriter().print(jsonString);

	}

}
