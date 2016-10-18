package com.storymap.friend.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.storymap.common.action.Action;
import com.storymap.common.service.MemberService;

public class FriendListAction implements Action {
	MemberService mService =MemberService.getInstance();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mem_code = Integer.parseInt(request.getParameter("mem_code"));

		List<Integer> friendList =mService.selectAllFriends(mem_code);
		
	}

}
