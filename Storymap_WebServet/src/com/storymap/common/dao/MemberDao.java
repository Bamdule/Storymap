package com.storymap.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.storymap.common.dto.MemberDto;
import com.storymap.util.DBManager;

public class MemberDao {
	private static MemberDao mDao = null;
	
	private MemberDao() {}
	
	public static MemberDao getInstance(){
		if(mDao == null)
			mDao = new MemberDao();
		return mDao;
	}
	public MemberDto selectMember(String mem_email,String mem_pwd){
		MemberDto mDto = null;
		String sql="select * from member where mem_email=? and mem_pwd = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_email);
			pstmt.setString(2, mem_pwd);
			rs=pstmt.executeQuery();
			if(rs.next()){
				mDto=new MemberDto();
				mDto.setmem_code(rs.getString("MEM_CODE"));
				mDto.setMem_email(rs.getString("MEM_EMAIL"));
				mDto.setMem_name(rs.getString("MEM_NAME"));
				mDto.setMem_img_path(rs.getString("mem_img_path"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		return mDto;
	}
	
	public boolean insertMember(MemberDto mDto){
		String sql = " insert into member values(CREATE_NEXT_MEMBERID,?,?,?,?)";
		boolean result =false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mDto.getMem_email());
			pstmt.setString(2, mDto.getMem_pwd());
			pstmt.setString(3, mDto.getMem_name());
			pstmt.setString(4, mDto.getMem_img_path());
			if(pstmt.executeUpdate()==1);
				result=true;
		}
		catch (SQLException e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		
		return result;
	}
	public boolean emailDuplicateCheck(String mem_email){
		String sql="select mem_email from member where mem_email = ?";
		boolean result=false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_email);
			rs=pstmt.executeQuery();
			if(rs.next()){
				result=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}
	public int memberLoginCheck(String mem_email,String mem_pwd){
		String sql ="select mem_pwd from member where mem_email=?";
		int result=3;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_email);
			rs=pstmt.executeQuery();
			if(rs.next()){
				if(rs.getString(1).equals(mem_pwd))
					result=1; // 1: id와 pwd 모두 일치
				else{
					result=2; // 2: id만 일치
				}			  // 3: id 불일치
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		
		return result;
	}
	
	
	public int friendRequest(int mem_code,int friend_code){
		String sql ="insert into friend values(?,?,0)";
		int result=3;
		Connection conn = null;
		PreparedStatement pstmt = null;
		result =friendReuqestDuplicateCheck(mem_code,friend_code);
		if(result==3){
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mem_code);
				pstmt.setInt(2, friend_code);
				
				if(pstmt.executeUpdate()!=1)
					result=4;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBManager.close(conn, pstmt);
			}
		}
		
		return result;
	}
	/*
	 * return 
	 * 1 : already friend 
	 * 2 : already friend Requesting
	 * 3 : request friend
	 */
	public int friendReuqestDuplicateCheck(int mem_code,int friend_code){
		String sql ="select REG_STATUS from friend where mem_code=? and friend_code=?";
		int result =3;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_code);
			pstmt.setInt(2, friend_code);
			rs=pstmt.executeQuery();
			if(rs.next()){
				if(rs.getInt(1)==1)
					result=1; // 1:이미친구
				else{
					result=2; // 2:이미 친구 요청중
				}			  // 3:친구요청 시작
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		
		return result;
	}

}
