package com.storymap.storynote.controller;

import com.storymap.common.action.Action;
import com.storymap.storynote.action.StorynoteAddAction;
import com.storymap.storynote.action.StorynoteDetailAction;
import com.storymap.storynote.action.StorynoteImageAddAction;
import com.storymap.storynote.action.StorynoteListAction;

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
		if(command.equals("storynote_detail"))
			action = new StorynoteDetailAction();
		else if(command.equals("storynote_list"))
			action = new StorynoteListAction();
		else if(command.equals("storynote_add"))
			action = new StorynoteAddAction();
		else if(command.equals("storynote_image_add"))
			action = new StorynoteImageAddAction();
		
		
		return action;
	}
	
	

}
