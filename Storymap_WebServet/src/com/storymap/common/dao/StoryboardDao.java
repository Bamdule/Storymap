package com.storymap.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.storymap.common.dto.MarkerDto;
import com.storymap.common.dto.StoryboardDto;
import com.storymap.common.dto.Storyboard_imgs;
import com.storymap.util.DBManager;

public class StoryboardDao {
	private static StoryboardDao instance = null;

	private StoryboardDao() {
	}

	public static StoryboardDao getInstance() {
		if (instance == null)
			instance = new StoryboardDao();
		return instance;
	}

	public StoryboardDto selectStoryboard(String sm_code, int mk_seq) {
		StoryboardDto sbDto = null;
		String sql = "SELECT mk_seq, sb_title,sb_content FROM STORYBOARD WHERE sm_code = ? AND mk_seq = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sm_code);
			pstmt.setInt(2, mk_seq);
			rs = pstmt.executeQuery();

			sbDto = new StoryboardDto();
			while (rs.next()) {
				sbDto.setMk_seq(rs.getInt("mk_seq"));
				sbDto.setSb_title(rs.getString("sb_title"));
				sbDto.setSb_content(rs.getString("sb_content"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		sbDto.setStoryboard_imgs(selectStoryboardImgs(sm_code, mk_seq));
		return sbDto;
	}

	public Storyboard_imgs selectStoryboardImgs(String sm_code, int mk_seq) {
		List<String> imageList = null;
		List<String> thumbnailList=null;
		Storyboard_imgs storyboard_imgs=null;

		String sql = "SELECT IMG_PATH,thumbnail_id FROM STORYBOARD_IMGS WHERE sm_code=? AND mk_seq = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sm_code);
			pstmt.setInt(2, mk_seq);
			rs = pstmt.executeQuery();
			storyboard_imgs=new Storyboard_imgs();
			imageList = new ArrayList<String>();
			thumbnailList = new ArrayList<String>();
			while (rs.next()) {
				imageList.add(rs.getString("img_path"));
				thumbnailList.add(rs.getString("thumbnail_id"));
			}
			storyboard_imgs.setImageList(imageList);
			storyboard_imgs.setThumbnailList(thumbnailList);
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return storyboard_imgs;
	}

	public boolean insertStoryboard(List<MarkerDto> markerList, String sm_code) {
		boolean result = true;
		String sql = "insert into storyboard values(?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		/*
		 * SM_CODE NOT NULL VARCHAR2(30) MK_SEQ NOT NULL NUMBER SB_TITLE
		 * VARCHAR2(100) SB_CONTENT NOT NULL VARCHAR2(3000)
		 */
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			for (MarkerDto mkDto : markerList) {
				if (mkDto.getIs_sb().equals("1")) {
					pstmt.setString(1, sm_code);
					pstmt.setInt(2, mkDto.getMk_seq());
					pstmt.setString(3, mkDto.getStoryboard().getSb_title());
					pstmt.setString(4, mkDto.getStoryboard().getSb_content());
					pstmt.executeUpdate();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		} finally {
			DBManager.close(conn, pstmt);
		}

	//	insertStoryboard_imgs(markerList, sm_code);
		return result;
	}

	public void insertStoryboardImages(String sm_code, int mk_seq, Storyboard_imgs storyboard_imgs) {
		String sql = "insert into storyboard_imgs values(STORYBOARD_IMG_SEQ.nextval,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			int index=0;
			for (String imgPath : storyboard_imgs.getImageList()) {
				pstmt.setString(1, sm_code);
				pstmt.setInt(2, mk_seq);
				pstmt.setString(3, imgPath);
				pstmt.setString(4, storyboard_imgs.getThumbnailList().get(index));
				pstmt.executeUpdate();
				index++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

	}
}
