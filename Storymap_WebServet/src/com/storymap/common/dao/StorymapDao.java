package com.storymap.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.storymap.common.dto.StorymapDto;
import com.storymap.util.DBManager;

public class StorymapDao {

	private static StorymapDao instance = null;

	private StorymapDao() {
	}

	public static StorymapDao getInstance() {
		if (instance == null)
			instance = new StorymapDao();

		return instance;
	}
	/*
	 * MEM_CODE SM_CODE CITY_CODE SUB_CITY_CODE SM_SEQ SM_TITLE SM_DATE
	 * SM_IMG_PATH
	 */

	public boolean insertStorymap(StorymapDto smDto) {
		String sql = "call insert_storymap(?,?,?,?,?,?)";

		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, smDto.getMem_code());
			pstmt.setString(2, smDto.getSm_code());
			pstmt.setString(3, smDto.getCity_code());
			pstmt.setString(4, smDto.getSub_city_code());
			pstmt.setString(5, smDto.getSm_title());
			pstmt.setString(6, smDto.getSm_img_path());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}

	public List<StorymapDto> selectAllStorymap(int mem_code) {
		StringBuilder sql = new StringBuilder();
		sql.append("select  s.sm_code ");
		sql.append("	  , c.CITY_NAME ");
		sql.append("	  , sc.SUB_CITY_NAME ");
		sql.append("	  , s.SM_TITLE ");
		sql.append("	  , s.SM_DATE ");
		sql.append("	  , s.SM_IMG_PATH ");
		sql.append("from STORYMAP s left outer JOIN CITY c  ");
		sql.append("ON s.CITY_CODE=c.CITY_CODE ");
		sql.append("left JOIN SUB_CITY sc ");
		sql.append("ON s.SUB_CITY_CODE = sc.SUB_CITY_CODE ");
		sql.append("where mem_code = ? ");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<StorymapDto> smList = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, mem_code);
			rs = pstmt.executeQuery();
			smList = new ArrayList<StorymapDto>();
			StorymapDto smDto = null;
			while (rs.next()) {
				smDto = new StorymapDto();
				smDto.setSm_code(rs.getString("sm_code"));
				smDto.setCity_name(rs.getString("city_name"));
				smDto.setSub_city_name(rs.getString("sub_city_name"));
				smDto.setSm_title(rs.getString("sm_title"));
				smDto.setSm_date(rs.getString("sm_date"));
				smDto.setSm_img_path(rs.getString("sm_img_path"));
				smList.add(smDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		System.out.println("smDao List "+ smList);
		return smList;
	}

	public StorymapDto selectStorymap(String sm_code) {
		StringBuilder sql = new StringBuilder();
		sql.append("select  s.sm_code ");
		sql.append("	  , c.CITY_NAME ");
		sql.append("	  , sc.SUB_CITY_NAME ");
		sql.append("	  , s.SM_TITLE ");
		sql.append("	  , s.SM_DATE ");
		sql.append("	  , s.SM_IMG_PATH ");
		sql.append("from STORYMAP s left outer JOIN CITY c  ");
		sql.append("ON s.CITY_CODE=c.CITY_CODE ");
		sql.append("left JOIN SUB_CITY sc ");
		sql.append("ON s.SUB_CITY_CODE = sc.SUB_CITY_CODE ");
		sql.append("where sm_code = ? ");
		StorymapDto smDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, sm_code);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				smDto = new StorymapDto();
				smDto.setSm_code(rs.getString("sm_code"));
				smDto.setCity_name(rs.getString("city_name"));
				smDto.setSub_city_name(rs.getString("sub_city_name"));
				smDto.setSm_title(rs.getString("sm_title"));
				smDto.setSm_date(rs.getString("sm_date"));
				smDto.setSm_img_path(rs.getString("sm_img_path"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return smDto;
	}

	public String createNextStorymapByMemCode(int mem_code) {
		String sql = "select create_next_storymapid(?) from dual";
		String newStorymapId = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_code);
			rs = pstmt.executeQuery();
			rs.next();
			newStorymapId = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return newStorymapId;
	}

}
