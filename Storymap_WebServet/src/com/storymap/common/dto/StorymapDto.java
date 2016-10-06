package com.storymap.common.dto;

import java.util.List;

/**
 * Created by S501-04 on 2016-09-21.
 */
public class StorymapDto {
	private int mem_code;
	private String sm_code;
	private String city_name;
	private String sub_city_name;
	private String city_code;
	private String sub_city_code;
	private String sm_title;
	private String sm_date;
	private String sm_img_path;

	private List<MarkerDto> markerList;
	private List<RouteDto> routeList;
	
	
	
	public String getSm_code() {
		return sm_code;
	}
	public void setSm_code(String sm_code) {
		this.sm_code = sm_code;
	}
	public int getMem_code() {
		return mem_code;
	}
	public void setMem_code(int mem_code) {
		this.mem_code = mem_code;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getSub_city_name() {
		return sub_city_name;
	}
	public void setSub_city_name(String sub_city_name) {
		this.sub_city_name = sub_city_name;
	}
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public String getSub_city_code() {
		return sub_city_code;
	}
	public void setSub_city_code(String sub_city_code) {
		this.sub_city_code = sub_city_code;
	}
	public String getSm_title() {
		return sm_title;
	}
	public void setSm_title(String sm_title) {
		this.sm_title = sm_title;
	}
	public String getSm_date() {
		return sm_date;
	}
	public void setSm_date(String sm_date) {
		this.sm_date = sm_date;
	}
	
	public String getSm_img_path() {
		return sm_img_path;
	}
	public void setSm_img_path(String sm_img_path) {
		this.sm_img_path = sm_img_path;
	}
	public List<MarkerDto> getMarkerList() {
		return markerList;
	}
	public void setMarkerList(List<MarkerDto> markerList) {
		this.markerList = markerList;
	}
	public List<RouteDto> getRouteList() {
		return routeList;
	}
	public void setRouteList(List<RouteDto> routeList) {
		this.routeList = routeList;
	}
	@Override
	public String toString() {
		return "StorymapDto [mem_code=" + mem_code + ", sm_code=" + sm_code + ", city_name=" + city_name
				+ ", sub_city_name=" + sub_city_name + ", city_code=" + city_code + ", sub_city_code=" + sub_city_code
				+ ", sm_title=" + sm_title + ", sm_date=" + sm_date + ", sm_img_path=" + sm_img_path + ", markerList="
				+ markerList + ", routeList=" + routeList + "]";
	}

  

	
    
}
