package com.storymap.friend.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.storymap.common.action.Action;
import com.storymap.common.service.MemberService;

public class FriendRequestListCountAction implements Action {
	MemberService mService =MemberService.getInstance();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mem_code = Integer.parseInt(request.getParameter("mem_code"));

		int result =mService.selectAllFriendRequestCount(mem_code);
		
		response.setContentType("charset=UTF-8");
		response.getWriter().print(String.valueOf(result));
		

	}

}
