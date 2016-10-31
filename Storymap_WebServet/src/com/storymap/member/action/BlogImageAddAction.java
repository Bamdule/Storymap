package com.storymap.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.storymap.common.action.Action;
import com.storymap.common.service.MemberService;
import com.storymap.util.ThumbnailHelper;
import com.storymap.util.UploadManager;

public class BlogImageAddAction implements Action{
	
	private ThumbnailHelper thumbnailHelper= ThumbnailHelper.getInstance();
	String storyboardSavePath="D:\\dev_jsp\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Storymap_WebServet\\images\\blog_images";
	MemberService mService = MemberService.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MultipartRequest mr=UploadManager.getMultiPartRequest(request,"images/blog_images");
		String blog_image_id=mr.getFilesystemName("blog_image_id");
		int mem_code = Integer.parseInt(mr.getParameter("mem_code"));
		//String file_name = mr.getOriginalFileName("member_image");

		
		boolean result = mService.updateBlogImageId(blog_image_id, mem_code);
		System.out.println("BlogImageAddAction : " + result);
		
		
	}

}
