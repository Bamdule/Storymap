package com.storymap.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.storymap.common.dto.StorynoteDto;
import com.storymap.util.DBManager;

public class StorynoteDao {
	private static StorynoteDao instance=null;
	
	private StorynoteDao() {
	}
	
	public static StorynoteDao getInstance(){
		if(instance==null)
			instance=new StorynoteDao();
		return instance;
	}
	
	/*
	 * 	 mem_code storynote.mem_code%TYPE
   , new_sn_code storynote.sn_code%TYPE
   , sn_title storynote.sn_title%TYPE
   , sn_content storynote.sn_content%TYPE
   , sn_img_path storynote.sn_img_path%TYPE
   , theme_code storynote.theme_code%TYPE
	 */
	
	public boolean insertStorynote(int mem_code, String sn_code, StorynoteDto snDto){
		boolean result=false;
		String sql="call insert_storynote(?,?,?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn = DBManager.getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, mem_code);
			pstmt.setString(2, sn_code);
			pstmt.setString(3, snDto.getSn_title());
			pstmt.setString(4, snDto.getSn_content());
			pstmt.setString(5, snDto.getTheme_code());
			if(pstmt.executeUpdate()==1)
				result=true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}

	public boolean insertStorymapOfStorynote(String sn_code, List<String> StorymapCodes){
		boolean result=false;
		String sql="insert into STORYMAPS_OF_STORYNOTE values(?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn = DBManager.getConnection();
			pstmt= conn.prepareStatement(sql);
			if(StorymapCodes!=null){
				for(String sm_code : StorymapCodes){
					pstmt.setString(1, sn_code);
					pstmt.setString(2, sm_code);
					if(pstmt.executeUpdate()==1)
						result=true;
					
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	
	
	public String createNextStorynoteCode(int mem_code){
		String sn_code=null;
		String sql="SELECT create_next_storynoteid(?) from dual";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, mem_code);
			rs=pstmt.executeQuery();
			if(rs.next()){
				sn_code=rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		return sn_code;
	}
	
	public List<String> selectStorymapsOfStorynote(String sn_code){
		String sql="select sm_code from storymaps_of_storynote where sn_code = ?";	
		List<String> storymapCodes =null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, sn_code);
			rs=pstmt.executeQuery();
			storymapCodes = new ArrayList<String>();
			while(rs.next()){
				storymapCodes.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		
		return storymapCodes;		
	}
	public List<StorynoteDto> selectAllStorynote(){
		String sql="select * from storynote";
		
		List<StorynoteDto> storynoteList =null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt= conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			storynoteList= new ArrayList<StorynoteDto>();
			//EXEC insert_storynote(1609262,create_next_storynoteid(1609262),'스토리노트 제목','스토리노트 내용','이미지','theme_002');
			while(rs.next()){
				StorynoteDto snDto = new StorynoteDto();
				snDto.setMem_code(rs.getInt("mem_code"));
				snDto.setSn_code(rs.getString("sn_code"));
				snDto.setSn_title(rs.getString("sn_title"));
				snDto.setSn_content(rs.getString("sn_content"));
				snDto.setSn_img_path(rs.getString("sn_img_path"));
				snDto.setTheme_code(rs.getString("theme_code"));
				snDto.setSn_readcount(rs.getInt("sn_readcount"));
				snDto.setSn_likecount(rs.getInt("sn_likecount"));
				storynoteList.add(snDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		for(StorynoteDto snDto : storynoteList){
			snDto.setStorymap_codes(selectStorymapsOfStorynote(snDto.getSn_code()));
		}
		return storynoteList;
	}
	
	public List<StorynoteDto> selectAllStorynoteByMem(int mem_code){
		String sql="select * from storynote where mem_code = ?";
		
		List<StorynoteDto> storynoteList =null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, mem_code);
			rs=pstmt.executeQuery();
			storynoteList= new ArrayList<StorynoteDto>();
			//EXEC insert_storynote(1609262,create_next_storynoteid(1609262),'스토리노트 제목','스토리노트 내용','이미지','theme_002');
			while(rs.next()){
				StorynoteDto snDto = new StorynoteDto();
				snDto.setMem_code(rs.getInt("mem_code"));
				snDto.setSn_code(rs.getString("sn_code"));
				snDto.setSn_title(rs.getString("sn_title"));
				snDto.setSn_content(rs.getString("sn_content"));
				snDto.setSn_img_path(rs.getString("sn_img_path"));
				snDto.setTheme_code(rs.getString("theme_code"));
				snDto.setSn_readcount(rs.getInt("sn_readcount"));
				snDto.setSn_likecount(rs.getInt("sn_likecount"));
				storynoteList.add(snDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		for(StorynoteDto snDto : storynoteList){
			snDto.setStorymap_codes(selectStorymapsOfStorynote(snDto.getSn_code()));
		}
		return storynoteList;
	}
	
	public List<StorynoteDto> selectAllFriendStorynote(int mem_code,int count){
		String sql="select * from storynote where mem_code = ?";
		
		List<StorynoteDto> storynoteList =null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, mem_code);
			rs=pstmt.executeQuery();
			storynoteList= new ArrayList<StorynoteDto>();
			//EXEC insert_storynote(1609262,create_next_storynoteid(1609262),'스토리노트 제목','스토리노트 내용','이미지','theme_002');
			while(rs.next()){
				StorynoteDto snDto = new StorynoteDto();
				snDto.setMem_code(rs.getInt("mem_code"));
				snDto.setSn_code(rs.getString("sn_code"));
				snDto.setSn_title(rs.getString("sn_title"));
				snDto.setSn_content(rs.getString("sn_content"));
				snDto.setSn_img_path(rs.getString("sn_img_path"));
				snDto.setTheme_code(rs.getString("theme_code"));
				snDto.setSn_readcount(rs.getInt("sn_readcount"));
				snDto.setSn_likecount(rs.getInt("sn_likecount"));
				storynoteList.add(snDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		for(StorynoteDto snDto : storynoteList){
			snDto.setStorymap_codes(selectStorymapsOfStorynote(snDto.getSn_code()));
		}
		return storynoteList;
	}
	
	
	//스토리노트 디테일
	public StorynoteDto selectStorynoteDetail(String sn_code){
		String sql="select * from storynote where sn_code = ?";
		
		StorynoteDto snDto=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, sn_code);
			rs=pstmt.executeQuery();
			snDto= new StorynoteDto();
			//EXEC insert_storynote(1609262,create_next_storynoteid(1609262),'스토리노트 제목','스토리노트 내용','이미지','theme_002');
			if(rs.next()){
				snDto.setMem_code(rs.getInt("mem_code"));
				snDto.setSn_code(sn_code);
				snDto.setSn_title(rs.getString("sn_title"));
				snDto.setSn_content(rs.getString("sn_content"));
				snDto.setSn_img_path(rs.getString("sn_img_path"));
				snDto.setTheme_code(rs.getString("theme_code"));
				snDto.setSn_readcount(rs.getInt("sn_readcount"));
				snDto.setSn_likecount(rs.getInt("sn_likecount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
			snDto.setStorymap_codes(selectStorymapsOfStorynote(snDto.getSn_code()));
		return snDto;
	}
	
	public boolean updateStorynoteImage(String sn_img_path,String sn_code){
		String sql="update storynote set sn_img_path = ? where sn_code = ?";
		boolean result =false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sn_img_path);
			pstmt.setString(2, sn_code);
			if(pstmt.executeUpdate()==1)
				result=true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return result;
	}
}
