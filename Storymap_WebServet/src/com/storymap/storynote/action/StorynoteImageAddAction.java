package com.storymap.storynote.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.storymap.common.action.Action;
import com.storymap.common.dao.StorynoteDao;
import com.storymap.util.UploadManager;

public class StorynoteImageAddAction implements Action {

	StorynoteDao snDao = StorynoteDao.getInstance();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MultipartRequest mr=UploadManager.getMultiPartRequest(request,"images/storynote_images");
		String sn_img_path=mr.getFilesystemName("storynote_image");
		String sn_code = mr.getParameter("sn_code");
		String orgin = mr.getOriginalFileName("storynote_image");
		snDao.updateStorynoteImage(sn_img_path, sn_code);
		System.out.println("storymap_Path : "+sn_img_path);
		System.out.println("orgin : "+orgin);
		
	}

}
