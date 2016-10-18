package com.storymap.member.controller;

import com.storymap.common.action.Action;
import com.storymap.member.action.MemberAddAction;
import com.storymap.member.action.MemberEmailDuplicateCheckAction;
import com.storymap.member.action.MemberImageAddAction;
import com.storymap.member.action.MemberLoginAction;

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
		
		if(command.equals("member_add"))
			action = new MemberAddAction();
		else if(command.equals("member_image_add"))
			action = new MemberImageAddAction();
		else if(command.equals("member_email_duplicate_check"))
			action = new MemberEmailDuplicateCheckAction();
		else if(command.equals("member_login"))
			action = new MemberLoginAction();
		
		return action;
	}
	
	

}
