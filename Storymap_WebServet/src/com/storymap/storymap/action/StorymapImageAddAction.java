package com.storymap.storymap.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.storymap.common.action.Action;
import com.storymap.common.service.StorymapService;
import com.storymap.util.UploadManager;

public class StorymapImageAddAction implements Action {
	StorymapService smService=StorymapService.getInstance();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MultipartRequest mr=UploadManager.getMultiPartRequest(request,"images/storymap_images");
		String sm_img_path=mr.getFilesystemName("storymap_image");
		String sm_code = mr.getParameter("sm_code");
		String orgin = mr.getOriginalFileName("storymap_image");
 
		smService.updateStorymapImage(sm_img_path, sm_code);
		
		
	}

}
