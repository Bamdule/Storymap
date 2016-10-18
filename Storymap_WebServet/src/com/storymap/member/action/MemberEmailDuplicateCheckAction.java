package com.storymap.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.storymap.common.action.Action;
import com.storymap.common.service.MemberService;

public class MemberEmailDuplicateCheckAction implements Action {

	MemberService mService = MemberService.getInstance();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mem_email=request.getParameter("mem_email");
		
		boolean result = mService.emailDuplicateCheck(mem_email);

		response.setContentType("charset=UTF-8");
		response.getWriter().print(String.valueOf(result));
	}

}
