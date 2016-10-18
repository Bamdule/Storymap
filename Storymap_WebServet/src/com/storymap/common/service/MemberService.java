package com.storymap.common.service;

import com.storymap.common.dao.MemberDao;
import com.storymap.common.dto.MemberDto;

public class MemberService {
	private static MemberService instance = null;
	private MemberDao mDao = MemberDao.getInstance();

	private MemberService() {
	}
	public static MemberService getInstance() {
		if (instance == null)
			instance = new MemberService();
		return instance;
	}
	
	public boolean emailDuplicateCheck(String mem_email){
		return mDao.emailDuplicateCheck(mem_email);
	}
	public boolean insertMember(MemberDto mDto){
		return mDao.insertMember(mDto);
	}
	public int memberLoginCheck(String mem_email, String mem_pwd){
		return mDao.memberLoginCheck(mem_email, mem_pwd);
	}
	public MemberDto selectMember(String mem_email,String mem_pwd){
		return mDao.selectMember(mem_email, mem_pwd);
	
	}
	public int friendRequest(int mem_code,int friend_code){
		return mDao.friendRequest(mem_code, friend_code);
	}
}
