package com.storymap.friend.controller;

import com.storymap.common.action.Action;
import com.storymap.friend.action.FriendDeleteAction;
import com.storymap.friend.action.FriendListAction;
import com.storymap.friend.action.FriendRequestAcceptAction;
import com.storymap.friend.action.FriendRequestAction;
import com.storymap.friend.action.FriendRequestCancelAction;
import com.storymap.friend.action.FriendRequestListAction;

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
		else if(command.equals("friend_request_accept"))
			action = new FriendRequestAcceptAction();
		else if(command.equals("friend_request_cancel"))
			action = new FriendRequestCancelAction();
		else if(command.equals("friend_list"))
			action = new FriendListAction();
		else if(command.equals("friend_delete"))
			action = new FriendDeleteAction();
		else if(command.equals("friend_request_list"))
			action = new FriendRequestListAction();
		
		return action;
	}
	
	

}
