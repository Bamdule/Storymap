package com.storymap.storymap.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.storymap.common.action.Action;
import com.storymap.common.service.StorymapService;
import com.storymap.util.ThumbnailHelper;
import com.storymap.util.UploadManager;

public class StorymapImageAddAction implements Action {

	private ThumbnailHelper thumbnailHelper= ThumbnailHelper.getInstance();
	StorymapService smService=StorymapService.getInstance();
	String storyboardSavePath="C:\\Users\\sun\\Desktop\\dev_jsp\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\Storymap_WebServet\\images\\storymap_images";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MultipartRequest mr=UploadManager.getMultiPartRequest(request,"images/storymap_images");
		String sm_img_path=mr.getFilesystemName("storymap_image");
		String sm_code = mr.getParameter("sm_code");
		String file_name = mr.getOriginalFileName("storymap_image");
		String thumbnail_id=thumbnailHelper.createThumbnail(storyboardSavePath,sm_img_path,400,400);
		boolean result=smService.updateStorymapImage(sm_img_path,thumbnail_id, sm_code);
		System.out.println("sm_code : "+ sm_code);
		System.out.println("result : "+result);
		
		
	}

}
