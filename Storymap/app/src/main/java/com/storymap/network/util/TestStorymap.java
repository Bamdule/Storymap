package com.storymap.network.util;

import com.storymap.network.dto.MarkerDto;
import com.storymap.network.dto.Point;
import com.storymap.network.dto.RouteDto;
import com.storymap.network.dto.StoryboardDto;
import com.storymap.network.dto.StorymapDto;

import java.util.ArrayList;
import java.util.List;


public class TestStorymap {
	
	public JsonManager jm=new JsonManager();
	
	public StorymapDto createStorymap(int mem_code){
		StorymapDto sDto =new StorymapDto();
		sDto.setMem_code(mem_code);
		sDto.setCity_code("c0001");
		sDto.setSub_city_code("c0001_1");
		sDto.setSm_img_path("image_path");
		sDto.setSm_title("스토리맵 제목");		
		
		MarkerDto mDto = new MarkerDto();
		
		mDto.setIs_sb("1");
		mDto.setMk_type("mk0000");
		mDto.setMk_x(150.165465);
		mDto.setMk_y(55.789789);
		
		StoryboardDto sbDto =new StoryboardDto();
		List<String> imgPathList =new ArrayList<String>();
		imgPathList.add("C:\\Users\\S501-04\\Desktop\\스토리맵\\PL-SQL");
		imgPathList.add("C:\\Users\\S501-04\\Desktop\\스토리맵\\PL-SQL");
		sbDto.setSb_content("스토리보드 내용입니다.!");
		sbDto.setSb_title("스토리보드 제목!!");
		sbDto.setImgPathList(imgPathList);
		mDto.setStoryboard(sbDto);
		
		List<MarkerDto> markerList =new ArrayList<MarkerDto>();
		for(int i=0; i<5;i++)
			markerList.add(mDto);
		
		RouteDto rDto =new RouteDto();
		
		List<Point> moving_route =new ArrayList<Point>();
		Point point = new Point(32.12121, 74.54545);
		Point point2 = new Point(55.13312821, 66.335);
		Point point3 = new Point(88.9999, 174.57745);
		
		moving_route.add(point);
		moving_route.add(point2);
		moving_route.add(point3);
		
		rDto.setMoving_route(moving_route);
		rDto.setRt_type("rt0000");
		
		List<RouteDto> routeList =new ArrayList<RouteDto>();
		routeList.add(rDto);
		routeList.add(rDto);
		
		sDto.setRouteList(routeList);
		sDto.setMarkerList(markerList);
		
		return sDto;	
	}
	
	public String requestStorymap(int mem_code)
	{
		String storymapJsonString = jm.instanceToJsonString(createStorymap(mem_code));
		
		return storymapJsonString;
	}

}
