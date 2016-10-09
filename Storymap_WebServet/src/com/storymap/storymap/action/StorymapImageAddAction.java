package com.storymap.storymap.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.storymap.common.action.Action;
import com.storymap.util.UploadManager;

public class StorymapImageAddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MultipartRequest multipartRequest=UploadManager.getMultiPartRequest(request,"images/storymap_images");
		String storymap_Path=multipartRequest.getFilesystemName("storymap_image");
		
		System.out.println("storymap_Path : "+storymap_Path);
		
		

	}

}
