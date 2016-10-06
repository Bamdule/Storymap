package com.storymap.member.action;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.storymap.common.action.Action;
import com.storymap.util.UploadManager;

public class MemberImageAddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MultipartRequest m=UploadManager.getMultiPartRequest(request,"images/member_images");
		Enumeration files=m.getFileNames();
		String file_name=null;
		
		while(files.hasMoreElements()){
			String file = (String)files.nextElement();
			file_name= m.getFilesystemName(file);
			String org_file_name=m.getOriginalFileName(file);
			
			System.out.println(file);
			System.out.println(file_name);
			System.out.println(org_file_name);
			System.out.println();
			System.out.println();
			System.out.println();
		}
		System.out.println("mem_code : "+m.getParameter("mem_code"));
		
		response.getWriter().print(file_name);
		
		
	}

}
