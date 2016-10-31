package com.storymap.blog.controller;

import com.storymap.blog.action.BlogImageAddAction;
import com.storymap.common.action.Action;
import com.storymap.common.action.CityListAction;

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
		if(command.equals("blog_image_add"))
			action = new BlogImageAddAction();
	
		return action;
	}
	
	

}
