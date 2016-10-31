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
		String mem_email = request.getParameter("mem_email");
		
		System.out.println("mem_email : " +mem_email);
		
		String jsonString=jsonManager.instanceToJsonString(memberService.searchMemberByEmail(mem_email));
		System.out.println("jsonString : "+ jsonString);
		response.setContentType("charset=UTF-8");
		response.getWriter().print(jsonString);
	}

}
