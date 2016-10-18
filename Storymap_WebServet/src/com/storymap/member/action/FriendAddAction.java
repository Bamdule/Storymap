package com.storymap.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.storymap.common.action.Action;
import com.storymap.common.service.MemberService;

public class FriendAddAction implements Action {
	MemberService mService =MemberService.getInstance();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mem_code = Integer.parseInt(request.getParameter("mem_code"));
		int friend_code = Integer.parseInt(request.getParameter("friend_code"));
		int result =mService.friendRequest(mem_code, friend_code);
		
		/*
		 * return 
		 * 1 : already friend 
		 * 2 : already friend Requesting
		 * 3 : request friend
		 * 4 : insert table failed
		 */
		response.setContentType("charset=UTF-8");
		response.getWriter().print(String.valueOf(result));
		

	}

}
