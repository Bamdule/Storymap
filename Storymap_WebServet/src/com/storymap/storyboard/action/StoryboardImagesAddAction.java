package com.storymap.storyboard.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.storymap.common.action.Action;
import com.storymap.common.dao.StoryboardDao;
import com.storymap.common.dto.Storyboard_imgs;
import com.storymap.util.ThumbnailHelper;
import com.storymap.util.UploadManager;

public class StoryboardImagesAddAction implements Action {
	private ThumbnailHelper thumbnailHelper= ThumbnailHelper.getInstance();
	String storyboardSavePath="C:\\Users\\sun\\Desktop\\dev_jsp\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\Storymap_WebServet\\images\\storyboard_images";
	StoryboardDao sbDao = StoryboardDao.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MultipartRequest mr=UploadManager.getMultiPartRequest(request,"images/storyboard_images");
		String sm_code = mr.getParameter("sm_code");
		int mk_seq = Integer.parseInt(mr.getParameter("mk_seq"));
		Enumeration files=mr.getFileNames(); // 전송이미지들에 대한 정보를 가진 Enumeration
		String file_name=null;
		List<String> imageList = new ArrayList<>();
		List<String> thumbnailList = new ArrayList<>();
		
		while(files.hasMoreElements()){
			//String org_file_name=mr.getOriginalFileName(param_name); // 원래 저장파일명
			String param_name = (String)files.nextElement();//파라미터 이름
			file_name= mr.getFilesystemName(param_name);//저장 파일명
			String thumbnail_id=thumbnailHelper.createThumbnail(storyboardSavePath,file_name,100,100);
			imageList.add(file_name);
			thumbnailList.add(thumbnail_id);
			
			/*
			System.out.println(param_name);
			System.out.println(file_name);
			System.out.println(org_file_name);*/
		}
		Storyboard_imgs storyboard_imgs=new Storyboard_imgs();
		storyboard_imgs.setImageList(imageList);
		storyboard_imgs.setThumbnailList(thumbnailList);
		
		sbDao.insertStoryboardImages(sm_code, mk_seq, storyboard_imgs);
		//response.getWriter().print(file_name);
	}
	
}
