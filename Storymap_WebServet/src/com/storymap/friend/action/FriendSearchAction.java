package com.storymap.friend.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.storymap.common.action.Action;
import com.storymap.common.service.MemberService;
import com.storymap.util.JsonManager;

public class FriendSearchAction implements Action {

	MemberService memberService=MemberService.getInstance();
	JsonManager jsonManager =JsonManager.getInstance();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mem_code = Integer.valueOf(request.getParameter("mem_code"));
		
		
		String jsonString=jsonManager.instanceToJsonString(memberService.searchMember(mem_code));
		response.setContentType("charset=UTF-8");
		response.getWriter().print(jsonString);
	}

}
