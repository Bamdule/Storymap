package com.storymap.common.service;

public class MemberService {
	private static MemberService instance = null;

	private MemberService() {
	}
	public static MemberService getInstance() {
		if (instance == null)
			instance = new MemberService();
		return instance;
	}
}
