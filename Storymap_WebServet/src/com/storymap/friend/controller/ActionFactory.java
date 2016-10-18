package com.storymap.friend.controller;

import com.storymap.common.action.Action;
import com.storymap.friend.action.FriendRequestAction;

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

		if(command.equals("friend_request"))
			action = new FriendRequestAction();
		
		return action;
	}
	
	

}
