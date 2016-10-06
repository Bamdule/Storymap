package com.storymap.story_map.controller;

import com.storymap.common.action.Action;
import com.storymap.member.action.MemberAddAction;
import com.storymap.member.action.MemberImageAddAction;
import com.storymap.member.action.MyPageInfoAction;
import com.storymap.member.action.MyStorymapListAction;
import com.storymap.story_map.action.StorymapAddAction;

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
		if(command.equals("storymap_add"))
			action = new StorymapAddAction();
		
		return action;
	}
	
	

}
