package com.storymap.friend.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.storymap.common.action.Action;
import com.storymap.common.dto.FriendDto;
import com.storymap.common.service.MemberService;
import com.storymap.util.JsonManager;

public class FriendListAction implements Action {
	MemberService mService =MemberService.getInstance();
	JsonManager jsonManager = JsonManager.getInstance();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mem_code = Integer.parseInt(request.getParameter("mem_code"));

		System.out.println("mem_code : "+mem_code);
		List<FriendDto> friendList =mService.selectAllFriends(mem_code);
		String jsonString=jsonManager.instanceToJsonString(friendList);

		System.out.println(jsonString);
		response.setContentType("charset=UTF-8");
		response.getWriter().print(jsonString);
	}

}
