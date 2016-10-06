package com.storymap.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.storymap.common.action.Action;
import com.storymap.util.UploadManager;

public class MyPageInfoAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		MultipartRequest multi = UploadManager.getMultiPartRequest(request, "images/member_images");
		
		
		/*MemberDao mDao = MemberDao.getInstance();
		mDao.selectMemberByCode("0000");
		
		
		  // 데이터를 안드로이드에서 받음
		String recvMessage = request.getParameter("msg");

	  // 초기 선언
		JsonObject jsonMain = new JsonObject();
		JsonArray jArray = new JsonArray();
		
		JsonObject jObject1 = new JsonObject();
		JsonObject jObject2 = new JsonObject();
		JsonObject jObject3 = new JsonObject();

	        // 안드로이드로 보낼 메시지를 만듬
		jObject1.addProperty("msg1", recvMessage);
		jObject2.addProperty("msg2", "한글!!");
		jObject3.addProperty("msg3", "됩니까!!???3!!");
		
	        // 위에서 만든 각각의 객체를 하나의 배열 형태로 만듬
		jArray.add(jObject1);
		jArray.add(jObject2);
		jArray.add(jObject3);

	        // 최종적으로 배열을 하나로 묶음
		jsonMain.add("List", jArray);
		
	        // 안드로이드에 보낼 데이터를 출력
		response.getWriter().println(jsonMain.toString());*/

	}

}
