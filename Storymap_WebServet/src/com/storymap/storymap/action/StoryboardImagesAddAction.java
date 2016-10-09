package com.storymap.storymap.action;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.storymap.common.action.Action;
import com.storymap.common.service.StoryboardService;
import com.storymap.util.UploadManager;

public class StoryboardImagesAddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MultipartRequest multipartRequest=UploadManager.getMultiPartRequest(request,"images/storyboard_images");
		Enumeration files=multipartRequest.getFileNames();
		String file_name=null;
		StoryboardService sbService =StoryboardService.getInstance();
		String sm_code = multipartRequest.getParameter("sm_code");
		int mk_seq = Integer.parseInt(multipartRequest.getParameter("mk_seq"));
		
		List<Integer> sb_seqList = sbService.findStoryboardOfMarker(sm_code);
		
		while(files.hasMoreElements()){
			String file = (String)files.nextElement();
			file_name= multipartRequest.getFilesystemName(file);
			String org_file_name=multipartRequest.getOriginalFileName(file);
			
			System.out.println(file);
			System.out.println(file_name);
			System.out.println(org_file_name);
		}
		//System.out.println("mem_code : "+m.getParameter("mem_code"));
		
		//response.getWriter().print(file_name);
		
	}

}
