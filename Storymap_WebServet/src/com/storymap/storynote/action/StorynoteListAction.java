package com.storymap.storynote.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.storymap.common.action.Action;
import com.storymap.common.dto.StorynoteDto;
import com.storymap.common.service.StorynoteService;
import com.storymap.util.JsonManager;


/*
 * 자신의 스토리맵 리스트 : my_storynote_list
 * 친구의 스토노트 리스트 : friend_storynote_list
 * 테마에 대한 스토리노트 리스트 : theme_storynote_List
 */
public class StorynoteListAction implements Action {
	JsonManager jsonManager = JsonManager.getInstance();
	StorynoteService snService = StorynoteService.getInstance();
	private final String myCode="my";
	private final String friendCode="friend";
	private final String allCode="all";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mem_code = Integer.parseInt(request.getParameter("mem_code"));
		String list_type=request.getParameter("list_type");
		List<StorynoteDto> storynoteList=null;
		String jsonString=null;
		if(list_type==null)
			list_type=allCode;
		switch(list_type){
		case myCode:
			storynoteList = snService.selectAllStorynoteByMem(mem_code);
			break;
		case friendCode :
			storynoteList = snService.selectAllStorynoteByMem(mem_code);
			break;
		case allCode :
			storynoteList=snService.selectAllStorynote();
			break;
		
		}
		
		System.out.println("storynoteList : " + storynoteList.toString());
		if(storynoteList!=null){
			jsonString = jsonManager.instanceToJsonString(storynoteList);
		}
	    
		
		System.out.println(jsonString);

		response.setContentType("charset=UTF-8");
		response.getWriter().print(jsonString);

	}

}
