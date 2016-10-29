package com.storymap.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.storymap.common.action.Action;
import com.storymap.common.dto.MemberDto;
import com.storymap.common.service.MemberService;

public class MemberAddAction implements Action {
	MemberService mService =MemberService.getInstance();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mem_email = request.getParameter("mem_email");
		String mem_pwd = request.getParameter("mem_pwd");
		String mem_name= request.getParameter("mem_name");
		MemberDto mDto = new MemberDto();
	
		boolean result =false;
		mDto.setMem_email(mem_email);
		mDto.setMem_name(mem_name);
		mDto.setMem_pwd(mem_pwd);

		System.out.println(mem_email);
		System.out.println(mem_pwd);
		System.out.println(mem_name);
		
		if(memberNullCheck(mDto)){
			if(!mService.emailDuplicateCheck(mem_email))
				result=mService.insertMember(mDto);
		}

		response.setContentType("charset=UTF-8");
		response.getWriter().print(String.valueOf(result));
	}
	
	
	private boolean memberNullCheck(MemberDto mDto){
		boolean result=false;
		if(mDto.getMem_email()!=null && mDto.getMem_name()!=null &&mDto.getMem_pwd()!=null)
			result = true;
		
		return result;
	}

}
