package com.storymap.util;

import java.util.ArrayList;
import java.util.List;

import com.storymap.common.dao.StorymapDao;
import com.storymap.common.dto.MarkerDto;
import com.storymap.common.dto.Point;
import com.storymap.common.dto.RouteDto;
import com.storymap.common.dto.StoryboardDto;
import com.storymap.common.dto.StorymapDto;

public class TestStorymap {
	
	public JsonManager jm=JsonManager.getInstance();
	StorymapDao smDao = StorymapDao.getInstance();
	
	public StorymapDto createStorymap(int mem_code){
		StorymapDto sDto =new StorymapDto();
		String sm_code=smDao.createNextStorymapByMemCode(mem_code);
		sDto.setMem_code(mem_code);
		sDto.setSm_code(sm_code);
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
		sbDto.setSb_title("스토리보드 제목!!");
		sbDto.setSb_content("스토리보드 내용");
		List<String> imgList =new ArrayList<String>();
		imgList.add("imag1");
		imgList.add("imag2");
		sbDto.setImgPathList(imgList);
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
