package com.storymap.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.storymap.common.dto.BlogDto;
import com.storymap.common.vo.Country;
import com.storymap.util.DBManager;

public class BlogDao {
	private static BlogDao instance=null;
	
	private BlogDao() {
	}
	
	public static BlogDao getInstance(){
		if(instance==null)
			instance=new BlogDao();
		return instance;
	}
	
	public BlogDto selectBlogImageId(int mem_code){
		String sql ="select * from blog where mem_code = ?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BlogDto bDto=null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_code);
			rs=pstmt.executeQuery();
			if(rs.next()){
				bDto=new BlogDto();
				bDto.setMem_code(rs.getInt("mem_code"));
				bDto.setImage_id(rs.getString("image_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		return bDto;
	}
}
