package com.storymap.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.storymap.common.dto.MarkerDto;
import com.storymap.common.dto.RouteDto;
import com.storymap.util.DBManager;
import com.storymap.util.JsonManager;

public class RouteDao {
	private JsonManager jm  =new JsonManager();
	/*
	Connection conn =null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;*/
	private static RouteDao instance=null;
	
	private RouteDao() {
	}
	
	public static RouteDao getInstance(){
		if(instance==null)
			instance=new RouteDao();
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public List<RouteDto> selectAllRoute(String sm_code){

		String sql ="SELECT rt_type,moving_route FROM ROUTE WHERE sm_code=?";
		List<RouteDto> routeList =null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, sm_code);
			rs = pstmt.executeQuery();
			routeList=new ArrayList<RouteDto>();
			RouteDto rDto =null;
			JsonManager jsonManager=new JsonManager();
			/*
			 * SM_CODE	NOT NULL	VARCHAR2(30)
				MK_SEQ	NOT NULL	NUMBER
				MK_TYPE		VARCHAR2(10)
				MK_X	NOT NULL	NUMBER
				MK_Y	NOT NULL	NUMBER
				IS_SB		CHAR(1)
			 */
			while(rs.next()){
				rDto=new RouteDto();
				rDto.setRt_type(rs.getString("rt_type"));
				rDto.setMoving_route(jsonManager.jsonArrayToList(rs.getString("moving_route")));
				routeList.add(rDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return routeList;
		
	}
	/*
	 * SM_CODE	NOT NULL	VARCHAR2(30)
	RT_SEQ	NOT NULL	NUMBER
	RT_TYPE		VARCHAR2(20)
	MOVING_ROUTE	NOT NULL	VARCHAR2(1000)
	 */
	public boolean insertRouteList(List<RouteDto> routeList,String sm_code){
		boolean result=true;
		String sql="insert into route values(?,?,?,?)";
		Connection conn =null;
		PreparedStatement pstmt = null;
		
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			int rt_seq=1;
			for(RouteDto rDto : routeList){
				String moving_route = jm.instanceToJsonString(rDto.getMoving_route());
				pstmt.setString(1, sm_code);
				pstmt.setInt(2, rt_seq);
				pstmt.setString(3, rDto.getRt_type());
				pstmt.setString(4, moving_route);
				pstmt.executeUpdate();
				rt_seq++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result=false;
		}finally{
			DBManager.close(conn, pstmt);
		}
		
		return result;
	}
	
}
