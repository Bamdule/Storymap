package com.storymap.member.action;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.storymap.common.action.Action;
import com.storymap.common.service.MemberService;
import com.storymap.util.ThumbnailHelper;
import com.storymap.util.UploadManager;

public class MemberImageAddAction implements Action {
	
	private ThumbnailHelper thumbnailHelper= ThumbnailHelper.getInstance();
	String storyboardSavePath="C:\\Users\\sun\\Desktop\\dev_jsp\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\Storymap_WebServet\\images\\member_images";
	MemberService mService = MemberService.getInstance();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MultipartRequest mr=UploadManager.getMultiPartRequest(request,"images/member_images");
		String mem_img_path=mr.getFilesystemName("member_image");
		int mem_code = Integer.parseInt(mr.getParameter("mem_code"));
		String file_name = mr.getOriginalFileName("member_image");

		
		String thumbnail_id=thumbnailHelper.createThumbnail(storyboardSavePath,mem_img_path,100,100);
		mService.updateMemberImage(mem_img_path, thumbnail_id, mem_code);
		
		
		
		
		
	}

}
