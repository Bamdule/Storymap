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
	/*
	 * MEM_CODE,MEM_EMAIL,MEM_PWD,MEM_NAME,MEM_IMG_PATH
	 */
	
	public MemberDto selectMemberByCode(String mem_code){
		MemberDto mDto = null;
		String sql="select * from member";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
			
				String MEM_CODE=rs.getString("MEM_CODE");
				String MEM_NAME=rs.getString("MEM_NAME");
				System.out.println("MEM_CODE : " + MEM_CODE );
				System.out.println("MEM_NAME : " + MEM_NAME );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		
		return mDto;
		
	}
	

}
