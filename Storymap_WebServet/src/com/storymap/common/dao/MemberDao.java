package com.storymap.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.storymap.common.dto.FriendDto;
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
				mDto.setMem_code(rs.getInt("MEM_CODE"));
				mDto.setMem_email(rs.getString("MEM_EMAIL"));
				mDto.setMem_name(rs.getString("MEM_NAME"));
				mDto.setMem_img_path(rs.getString("mem_img_path"));
				mDto.setThumbnail_id(rs.getString("thumbnail_id"));
				mDto.setBlog_image_id(rs.getString("blog_image_id"));
				mDto.setLoginFlag("1");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		return mDto;
	}
	
	public boolean insertMember(MemberDto mDto){
		
		System.out.println(mDto);
		String sql = " insert into member values(CREATE_NEXT_MEMBERID,?,?,?,?,?,?)";
		boolean result =false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mDto.getMem_email());
			pstmt.setString(2, mDto.getMem_pwd());
			pstmt.setString(3, mDto.getMem_name());
			pstmt.setString(4, null);
			pstmt.setString(5, null);
			pstmt.setString(6, null);
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
	
	//회원의 모든 친구 목록
	public List<Integer> selectAllFriends(int mem_code){
		String sql ="SELECT friend_code FRIEND from friend where mem_code = ? AND reg_status=1 "
				+   "UNION "
                +   "SELECT mem_code from FRIEND friend where friend_code = ? AND reg_status=1 ";
		List<Integer> friendList = new ArrayList<Integer>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mem_code);
				pstmt.setInt(2, mem_code);
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					friendList.add(rs.getInt("FRIEND"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBManager.close(conn, pstmt,rs);
			}
		return friendList;
	}
	
	public List<FriendDto> selectAllFriendInfo(List<Integer> friend_codes){
		String sql="select * from member where mem_code = ?";
		List<FriendDto> friendList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				friendList=new ArrayList<FriendDto>();
				for(Integer friend_code : friend_codes){
					pstmt.setInt(1, friend_code);
					rs=pstmt.executeQuery();
					if(rs.next()){
						FriendDto fDto = new FriendDto();
						fDto.setFriend_code(friend_code);
						fDto.setMem_name(rs.getString("mem_name"));
						fDto.setMem_img_path(rs.getString("mem_img_path"));
						fDto.setThumbnail_id(rs.getString("thumbnail_id"));
						fDto.setBlog_image_id(rs.getString("blog_image_id"));
						
						friendList.add(fDto);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBManager.close(conn, pstmt,rs);
			}
		
		return friendList;
	}
	
	
	//자신에게 온 친구요청 리스트 
	public List<FriendDto> selectAllFriendRequest(int mem_code){
		String sql ="select f.friend_code , f.REG_STATUS, m.MEM_NAME, m.MEM_IMG_PATH,m.thumbnail_id,m.blog_image_id "  
					+"from FRIEND f , MEMBER m " 
					+"where f.mem_code = ? and reg_status=0  AND f.FRIEND_CODE=m.MEM_CODE ";
		List<FriendDto> friendRequestList = new ArrayList<FriendDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mem_code);
				
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					FriendDto fDto = new FriendDto();
					fDto.setFriend_code(rs.getInt(1));
					fDto.setReg_status(rs.getInt(2));
					fDto.setMem_name(rs.getString(3));
					fDto.setMem_img_path(rs.getString(4));
					fDto.setThumbnail_id(rs.getString(5));
					fDto.setBlog_image_id(rs.getString(6));
					friendRequestList.add(fDto);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBManager.close(conn, pstmt,rs);
			}
		return friendRequestList;
	}
	//자신에게 온 친구요청 리스트 
	public int selectAllFriendRequestCount(int mem_code){
		String sql ="SELECT COUNT(*) FROM FRIEND WHERE mem_code=? AND reg_status = 0";
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mem_code);
				
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					count=rs.getInt(1);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBManager.close(conn, pstmt,rs);
			}
		return count;
	}
	
	//친구요청 수락
	public boolean acceptFriendRequest(int mem_code,int friend_code){
		String sql ="update friend set reg_status = 1 where mem_code = ? and friend_code = ? and reg_status = 0";
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mem_code);
				pstmt.setInt(2, friend_code);
				if(pstmt.executeUpdate()==1)
					result = true;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBManager.close(conn, pstmt);
			}
			
		return result;
	}
	//친구요청
	public int friendRequest(int mem_code,int friend_code){
		String sql ="insert into friend values(?,?,0)";
		int result=4;
		Connection conn = null;
		PreparedStatement pstmt = null;
		result =friendReuqestDuplicateCheck(friend_code,mem_code);
		if(result==3){
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, friend_code);
				pstmt.setInt(2, mem_code);
				
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
	 * 
	 * 이미 친구요청을 했는지 체크
	 * 
	 * 중복 상황
	 * 친구가 나에게 친구 요청을 했을 경우
	 * 내가 친구에게 친구 요청을 했을 경우
	 */
	public int friendReuqestDuplicateCheck(int mem_code,int friend_code){
		String sql = "SELECT reg_status "
					+"FROM FRIEND " 
					+"WHERE mem_code = ? AND friend_code = ? " //내가 친구에게 이미 요청을 했는지 검사
					+"OR mem_code = ? AND friend_code = ? ";   //친구가 나에게 요청을 했는지 검사

		System.out.println("mem_code : " +mem_code);
		System.out.println("friend_code : " +friend_code);
		//String sql ="select REG_STATUS from friend where mem_code=? and friend_code=?";
		int result =3;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_code);
			pstmt.setInt(2, friend_code);
			
			pstmt.setInt(3, friend_code);
			pstmt.setInt(4, mem_code);
			
			rs=pstmt.executeQuery();
			
			
			if(rs.next()){
				if(rs.getInt(1)==1)
					result=1; // 1:이미친구
				else{
					result=2; // 2: 내가 친구에게 요청 중이거나, 친구가 나에게 요청중이거나
				}			  // 3:친구요청 시작
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		
		return result;
	}
	
	//친구요청 취소
		public boolean cancelFriendRequest(int mem_code,int friend_code){
			String sql ="delete from friend where mem_code = ? and friend_code = ? and reg_status = 0";
			boolean result = false;
			Connection conn = null;
			PreparedStatement pstmt = null;
				try {
					conn = DBManager.getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, mem_code);
					pstmt.setInt(2, friend_code);
					if(pstmt.executeUpdate()==1)
						result = true;
					
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					DBManager.close(conn, pstmt);
				}
				
			return result;
		}
		
		/*
		 * DELETE 
FROM FRIEND f
WHERE EXISTS
(
	SELECT 1
    FROM(				
		SELECT mem_code mem_code, friend_code friend_code from FRIEND  where mem_code = 1610186 AND friend_code = 1610188 AND reg_status=1 
		UNION 
    	SELECT mem_code mem_code, friend_code friend_code from FRIEND  where mem_code = 1610188 AND friend_code = 1610186 AND reg_status=1) target
    WHERE f.MEM_CODE = target.mem_code AND f.FRIEND_CODE=target.friend_code                                                      
);        
		 * 
		 * 
		 */
		//친구삭제
		public boolean deleteFriend(int mem_code,int friend_code){
			StringBuilder sql =new StringBuilder();
			sql.append("DELETE ");
			sql.append("FROM FRIEND f ");
			sql.append("WHERE EXISTS ");
				 sql.append("( SELECT 1 ");
				   sql.append("FROM(SELECT mem_code mem_code, friend_code friend_code from FRIEND  where mem_code = ? AND friend_code = ? AND reg_status=1 ");
			       sql.append("UNION ");
			       sql.append("SELECT mem_code mem_code, friend_code friend_code from FRIEND  where mem_code = ? AND friend_code = ? AND reg_status=1) target ");
			     sql.append("WHERE f.MEM_CODE = target.mem_code AND f.FRIEND_CODE=target.friend_code ) ");
			boolean result = false;
			Connection conn = null;
			PreparedStatement pstmt = null;
				try {
					conn = DBManager.getConnection();
					pstmt = conn.prepareStatement(sql.toString());
					pstmt.setInt(1, mem_code);
					pstmt.setInt(2, friend_code);
					pstmt.setInt(3, friend_code);
					pstmt.setInt(4, mem_code);
					
					if(pstmt.executeUpdate()==1)
						result = true;
							
					} catch (SQLException e) {
						e.printStackTrace();
					}finally{
						DBManager.close(conn, pstmt);
					}
					
			return result;
		}
		
		
		public MemberDto searchMemberByEmail(String mem_email){
			String sql ="select mem_code,mem_name,mem_img_path,thumbnail_id ,blog_image_id from member where mem_email=?";
			MemberDto mDto = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
				try {
					conn = DBManager.getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, mem_email);
					rs=pstmt.executeQuery();
					
					if(rs.next()){
						mDto=new MemberDto();
						mDto.setMem_code(rs.getInt("mem_code"));
						mDto.setMem_name(rs.getString("mem_name"));
						mDto.setMem_img_path(rs.getString("mem_img_path"));
						mDto.setThumbnail_id(rs.getString("thumbnail_id"));
						mDto.setBlog_image_id(rs.getString("blog_image_id"));
					}
							
					} catch (SQLException e) {
						e.printStackTrace();
					}finally{
						DBManager.close(conn, pstmt,rs);
					}
			return mDto;
		}
		public MemberDto searchMember(int mem_code){
			String sql ="select mem_code,mem_name,mem_img_path,thumbnail_id,mem_email,blog_image_id from member where mem_code=?";
			MemberDto mDto = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
				try {
					conn = DBManager.getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, mem_code);
					rs=pstmt.executeQuery();
					
					if(rs.next()){
						mDto=new MemberDto();
						mDto.setMem_code(mem_code);
						mDto.setMem_code(rs.getInt("mem_code"));
						mDto.setMem_name(rs.getString("mem_name"));
						mDto.setMem_img_path(rs.getString("mem_img_path"));
						mDto.setMem_email(rs.getString("mem_email"));
						mDto.setThumbnail_id(rs.getString("thumbnail_id"));
						mDto.setBlog_image_id(rs.getString("blog_image_id"));
					}
							
					} catch (SQLException e) {
						e.printStackTrace();
					}finally{
						DBManager.close(conn, pstmt,rs);
					}
					
			return mDto;
		}
		
		public boolean updateMemberImage(String mem_img_path,String thumbnail_id,int mem_code){
			String sql="update member set mem_img_path = ? , thumbnail_id = ? where mem_code = ?";
			boolean result =false;
			
			Connection conn = null;
			PreparedStatement pstmt = null;

			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mem_img_path);
				pstmt.setString(2, thumbnail_id);
				pstmt.setInt(3, mem_code);
				if(pstmt.executeUpdate()==1)
					result=true;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
			
			return result;
		}
		
		public boolean updateBlogImageId(String blog_image_id,int mem_code){
			String sql="update member set blog_image_id = ? where mem_code = ?";
			boolean result =false;
			
			Connection conn = null;
			PreparedStatement pstmt = null;

			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, blog_image_id);
				pstmt.setInt(2, mem_code);
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
