package com.storymap.storymap.controller;

import com.storymap.common.action.Action;
import com.storymap.member.action.MemberAddAction;
import com.storymap.member.action.MemberImageAddAction;
import com.storymap.member.action.MyPageInfoAction;
import com.storymap.member.action.MyStorymapListAction;
import com.storymap.storymap.action.StoryboardImagesAddAction;
import com.storymap.storymap.action.StorymapAddAction;
import com.storymap.storymap.action.StorymapDetailAction;
import com.storymap.storymap.action.StorymapImageAddAction;
import com.storymap.storymap.action.StorymapListAction;

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
		else if(command.equals("storymap_detail"))
			action = new StorymapDetailAction();
		else if(command.equals("storymap_list"))
			action = new StorymapListAction();
		else if(command.equals("storymap_image_add"))
			action = new StorymapImageAddAction();
		else if(command.equals("storyboard_images_add"))
			action = new StoryboardImagesAddAction();
		
		return action;
	}
	
	

}
