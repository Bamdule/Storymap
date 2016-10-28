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
		List<FriendDto> friendList = mDao.selectAllFriendRequest(mem_code);
		if(friendList.size()>0&& friendList !=null)
			return friendList ;
		else
			return null;
	}
	//친구리스트
	public List<FriendDto> selectAllFriends(int mem_code){
		List<Integer> friend_codes = mDao.selectAllFriends(mem_code);
		if(friend_codes.size()>0 && friend_codes!=null)
			return mDao.selectAllFriendInfo(friend_codes);
		
		return null;
	}
	//친구 요청 수
	public int selectAllFriendRequestCount(int mem_code){
		return mDao.selectAllFriendRequestCount(mem_code);
	}
	public MemberDto searchMember(int mem_code){
		return mDao.searchMember(mem_code);
	}
	public boolean deleteFriend(int mem_code, int friend_code){
		return mDao.deleteFriend(mem_code, friend_code);
	}
	
	public MemberDto searchMemberByEmail(String mem_email){
		return mDao.searchMemberByEmail(mem_email);
	}
	public boolean updateMemberImage(String mem_img_path,String thumbnail_id,int mem_code){
		return mDao.updateMemberImage(mem_img_path, thumbnail_id, mem_code);
	}
	
}
