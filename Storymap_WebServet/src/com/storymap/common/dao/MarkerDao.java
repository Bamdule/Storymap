package com.storymap.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.storymap.common.dto.MarkerDto;
import com.storymap.util.DBManager;

public class MarkerDao {/*
	Connection conn =null;dd
	PreparedStatement pstmt = null;
	ResultSet rs = null;*/
	private static MarkerDao instance=null;
	
	private MarkerDao() {
	}
	
	public static MarkerDao getInstance(){
		if(instance==null)
			instance=new MarkerDao();
		return instance;
	}
	
	
	/*
	 * SM_CODE	NOT NULL	VARCHAR2(30)
		MK_SEQ	NOT NULL	NUMBER
		MK_TYPE		VARCHAR2(10)
		MK_X	NOT NULL	NUMBER
		MK_Y	NOT NULL	NUMBER
		IS_SB		CHAR(1)
	 */
	public boolean insertMarkerList(List<MarkerDto> markerList,String sm_code)
	{
		
		String sql ="insert into marker values(?,?,?,?,?,?)";
		Connection conn =null;
		PreparedStatement pstmt = null;
		boolean result = true;
		boolean sb_result=true;
		StoryboardDao sbDao =StoryboardDao.getInstance();
	
		try {
			int mk_seq=1;
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			for(MarkerDto mDto : markerList){
				
				mDto.setMk_seq(mk_seq);
				pstmt.setString(1,sm_code);
				pstmt.setInt(2,mDto.getMk_seq());
				pstmt.setString(3, mDto.getMk_type());
				pstmt.setDouble(4, mDto.getMk_x());
				pstmt.setDouble(5, mDto.getMk_y());
				pstmt.setString(6, mDto.getIs_sb());
				pstmt.executeUpdate();
				mk_seq++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			result=false;
			sb_result=false;
		}finally {
			DBManager.close(conn, pstmt);
		}
		System.out.println("sb_result : "+sb_result);
		
		int sb_seq=1;
		//마커의 스토리보드 DB에 저장 (DBConnection이 너무 많이 호출되어 분리함)
	
		sbDao.insertStoryboard(markerList, sm_code);
		
		
		return result;
	}
	public List<MarkerDto> selectAllMarker(String sm_code){
		String sql ="SELECT * FROM MARKER WHERE sm_code = ?";
		List<MarkerDto> markerList =null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, sm_code);
			rs = pstmt.executeQuery();
			markerList=new ArrayList<MarkerDto>();
			MarkerDto mDto =null;
			/*
			 * SM_CODE	NOT NULL	VARCHAR2(30)
				MK_SEQ	NOT NULL	NUMBER
				MK_TYPE		VARCHAR2(10)
				MK_X	NOT NULL	NUMBER
				MK_Y	NOT NULL	NUMBER
				IS_SB		CHAR(1)
			 */
			while(rs.next()){
				mDto=new MarkerDto();
				mDto.setMk_seq(rs.getInt("mk_seq"));
				mDto.setMk_type(rs.getString("mk_type"));
				mDto.setMk_x(rs.getDouble("mk_x"));
				mDto.setMk_y(rs.getDouble("mk_y"));
				mDto.setIs_sb(rs.getString("is_sb"));
				markerList.add(mDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return markerList;
	}
	
	
}
