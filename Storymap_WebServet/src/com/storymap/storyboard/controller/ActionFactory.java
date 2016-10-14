package com.storymap.storyboard.controller;

import com.storymap.common.action.Action;

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
		if(command.equals("storyboard_images_add"))
			action = new com.storymap.storyboard.action.StoryboardImagesAddAction();
		return action;
	}
	
	

}
