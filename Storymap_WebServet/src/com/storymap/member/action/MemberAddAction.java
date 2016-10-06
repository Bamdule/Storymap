package com.storymap.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.storymap.common.action.Action;

public class MemberAddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mem_email = request.getParameter("mem_email");
		String mem_pwd = request.getParameter("mem_pwd");
		String mem_name= request.getParameter("mem_name");
		String mem_img_path= request.getParameter("mem_img_path");
		
		System.out.println(mem_email);
		System.out.println(mem_pwd);
		System.out.println(mem_name);
		System.out.println(mem_img_path);
		  // 초기 선언
			JsonObject jsonMain = new JsonObject();
			JsonArray jArray = new JsonArray();
			
			JsonObject jObject1 = new JsonObject();
			JsonObject jObject2 = new JsonObject();
			JsonObject jObject3 = new JsonObject();

		        // 안드로이드로 보낼 메시지를 만듬
			jObject1.addProperty("msg1", mem_email);
			jObject2.addProperty("msg2", mem_pwd);
			jObject3.addProperty("msg3", mem_name);
			jObject3.addProperty("msg4", mem_img_path);
			
		        // 위에서 만든 각각의 객체를 하나의 배열 형태로 만듬
			jArray.add(jObject1);
			jArray.add(jObject2);
			jArray.add(jObject3);

		        // 최종적으로 배열을 하나로 묶음
			jsonMain.add("List", jArray);
			
		        // 안드로이드에 보낼 데이터를 출력
			response.getWriter().print(jsonMain.toString());
		
	}

}
