package com.storymap.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.storymap.common.action.Action;
import com.storymap.common.dto.MemberDto;
import com.storymap.common.service.MemberService;
import com.storymap.util.JsonManager;

public class MemberDetailAction implements Action {
	MemberService mService = MemberService.getInstance();
	JsonManager jsonManager=JsonManager.getInstance();

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mem_code= Integer.parseInt(request.getParameter("mem_code"));
		MemberDto mDto=null;
		
		mDto=mService.searchMemberDetail(mem_code);
		
		System.out.println(mDto);
		response.setContentType("charset=UTF-8");
		response.getWriter().print(jsonManager.instanceToJsonString(mDto));
	}

}
