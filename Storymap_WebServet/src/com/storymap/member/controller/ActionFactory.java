package com.storymap.member.controller;

import com.storymap.common.action.Action;
import com.storymap.member.action.MemberAddAction;
import com.storymap.member.action.MemberImageAddAction;
import com.storymap.member.action.MyPageInfoAction;
import com.storymap.member.action.MyStorymapListAction;

public class ActionFactory {
	private static ActionFactory instance =null;
	private ActionFactory() {
	}
	
	public static ActionFactory getInstance()
	{
		instance = new ActionFactory();
		return instance;
	}
	
	public Action getAction(String command){
		Action action =null;
		
		if(command.equals("mystorymap_list"))
			action = new MyStorymapListAction();
		else if(command.equals("mypage_info"))
			action = new MyPageInfoAction();
		else if(command.equals("member_add"))
			action = new MemberAddAction();
		else if(command.equals("member_image_add"))
			action = new MemberImageAddAction();
		
		
		return action;
	}
	
	

}
