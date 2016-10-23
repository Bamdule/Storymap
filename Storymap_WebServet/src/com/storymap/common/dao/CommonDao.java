package com.storymap.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.storymap.common.vo.City;
import com.storymap.common.vo.Country;
import com.storymap.common.vo.Sub_City;
import com.storymap.util.DBManager;

public class CommonDao {
	private static CommonDao instance=null;
	
	private CommonDao() {
	}
	
	public static CommonDao getInstance(){
		if(instance==null)
			instance=new CommonDao();
		return instance;
	}
	
	public Country selectAllCityList(){
		String sql ="select * from city";
		Country country=Country.getInstance();
		List<City> cityList = null;
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			cityList=new ArrayList<City>();
			while(rs.next()){
				City city = new City();
				city.setCity_code(rs.getString("city_code"));
				city.setCity_name(rs.getString("city_name"));
				cityList.add(city);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		selectAllSubCityList(cityList);
		country.setCityList(cityList);
		country.setCountryName("korea");
		return country;
	}
	
	public void selectAllSubCityList(List<City> cityList){
		String sql ="select SUB_CITY_CODE , SUB_CITY_NAME FROM sub_city WHERE CITY_CODE=?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Sub_City> subCityList =null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			for(City city : cityList){
				pstmt.setString(1, city.getCity_code());
				rs=pstmt.executeQuery();
				subCityList=new ArrayList<Sub_City>();
				while(rs.next()){
					Sub_City sub_City = new Sub_City();
					sub_City.setSub_city_code(rs.getString("sub_city_code"));
					sub_City.setSub_city_name(rs.getString("sub_city_name"));
					subCityList.add(sub_City);
					city.setSub_cityList(subCityList);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
	}
}
