package com.storymap.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.storymap.common.action.Action;
import com.storymap.common.dto.MemberDto;
import com.storymap.common.service.MemberService;
import com.storymap.util.JsonManager;

public class MemberLoginAction implements Action {
	MemberService mService = MemberService.getInstance();
	JsonManager jsonManager=JsonManager.getInstance();

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mem_email= request.getParameter("mem_email");
		String mem_pwd= request.getParameter("mem_pwd");
		int result =mService.memberLoginCheck(mem_email, mem_pwd);
		MemberDto mDto=null;

		switch(result){
		case 1://Log Success
			mDto=mService.selectMember(mem_email, mem_pwd);
			break;
		case 2://pwd dismatch
			mDto=new MemberDto();
			mDto.setLoginFlag("2");
			break;
		case 3://id dismatch
			mDto=new MemberDto();
			mDto.setLoginFlag("3");
			break;
		}
		System.out.println(mDto);
		response.setContentType("charset=UTF-8");
		response.getWriter().print(jsonManager.instanceToJsonString(mDto));
	}

}
