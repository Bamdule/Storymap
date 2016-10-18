package com.storymap.common.service;

import java.util.List;

import com.storymap.common.dao.MemberDao;
import com.storymap.common.dto.FriendDto;
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
	//친구요청
	public int friendRequest(int mem_code,int friend_code){
		return mDao.friendRequest(mem_code, friend_code);
	}
	//친구요청수락
	public boolean acceptFriendRequest(int mem_code,int friend_code){
		return mDao.acceptFriendRequest(mem_code, friend_code);
	}
	//친구요청 거절
	public boolean cancelFriendRequest(int mem_code,int friend_code){
		return mDao.cancelFriendRequest(mem_code, friend_code);
	}
	//친구요청리스트
	public List<FriendDto> selectAllFriendRequest(int mem_code){
		return mDao.selectAllFriendRequest(mem_code);
	}
	//친구리스트
	public List<Integer> selectAllFriends(int mem_code){
		return mDao.selectAllFriends(mem_code);
	}
}
