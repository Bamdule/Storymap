package com.storymap.common.service;

import java.util.List;

import com.storymap.common.dao.MarkerDao;
import com.storymap.common.dao.RouteDao;
import com.storymap.common.dao.StoryboardDao;
import com.storymap.common.dao.StorymapDao;
import com.storymap.common.dto.MarkerDto;
import com.storymap.common.dto.Point;
import com.storymap.common.dto.RouteDto;
import com.storymap.common.dto.StoryboardDto;
import com.storymap.common.dto.StorymapDto;

public class StorymapService {
	private static StorymapService instance = null;
	private StorymapDao smDao = StorymapDao.getInstance();
	private MarkerDao mkDao = MarkerDao.getInstance();
	private RouteDao rtDao = RouteDao.getInstance();
	private StoryboardDao sbDao = StoryboardDao.getInstance();

	private StorymapService() {
	}

	public static StorymapService getInstance() {
		if (instance == null)
			instance = new StorymapService();
		return instance;
	}

	public boolean insertStorymap(StorymapDto smDto, int mem_code) {
		boolean result = true;
		if (smDto != null) {
			String sm_code = smDao.createNextStorymapByMemCode(mem_code);
			smDto.setMem_code(mem_code);
			smDto.setSm_code(sm_code);
			smDao.insertStorymap(smDto);
			if (smDto.getMarkerList() != null)
				mkDao.insertMarkerList(smDto.getMarkerList(), sm_code);
			if (smDto.getRouteList() != null)
				rtDao.insertRouteList(smDto.getRouteList(), sm_code);
		} else
			result = false;

		return result;
	}

	// 사원의 모든 스토리맵을 DB에서 가져온다.
	public List<StorymapDto> selectAllStorymap(int mem_code) {
		List<StorymapDto> smList = null;
		List<MarkerDto> mkList = null;
		List<RouteDto> rtList = null;
		smList = smDao.selectAllStorymap(mem_code);

		if(smList!=null){
			for (StorymapDto smDto : smList) {
				mkList = mkDao.selectAllMarker(smDto.getSm_code());
				rtList = rtDao.selectAllRoute(smDto.getSm_code());
				for (MarkerDto mkDto : mkList) {
					StoryboardDto sbDto = sbDao.selectStoryboard(smDto.getSm_code(), mkDto.getMk_seq());
					sbDto.setStoryboard_imgs(sbDao.selectStoryboardImgs(smDto.getSm_code(), mkDto.getMk_seq()));
					mkDto.setStoryboard(sbDto);
				}
	
				smDto.setMarkerList(mkList);
				smDto.setRouteList(rtList);
			}
		}
		return smList;
	}

	public StorymapDto selectStorymapDetail(String sm_code) {
		StorymapDto smDto = null;
		List<MarkerDto> mkList = null;
		List<RouteDto> rtList = null;

		smDto = smDao.selectStorymapDetail(sm_code);
		if (smDto != null) {
			mkList = mkDao.selectAllMarker(sm_code);
			rtList = rtDao.selectAllRoute(sm_code);

			for (MarkerDto mkDto : mkList) {
				StoryboardDto sbDto = sbDao.selectStoryboard(sm_code, mkDto.getMk_seq());
				sbDto.setStoryboard_imgs(sbDao.selectStoryboardImgs(sm_code, mkDto.getMk_seq()));
				mkDto.setStoryboard(sbDto);
			}

			smDto.setMarkerList(mkList);
			smDto.setRouteList(rtList);
		}
		return smDto;
	}
	public boolean updateStorymapImage(String sm_img_path,String thumbnail_id,String sm_code){
		return smDao.updateStorymapImage(sm_img_path,thumbnail_id, sm_code);
	}

	public void storymapView(StorymapDto smDto) {

		System.out.println("Sm_title : " + smDto.getSm_title());
		System.out.println("City_name : " + smDto.getCity_name());
		System.out.println("Sub_city_name : " + smDto.getSub_city_name());
		System.out.println("Sm_date : " + smDto.getSm_date());
		System.out.println("Sm_img_path : " + smDto.getSm_img_path());

		for (MarkerDto mkDto : smDto.getMarkerList()) {
			System.out.println("Mk_type : " + mkDto.getMk_type());
			System.out.println("Mk_x : " + mkDto.getMk_x());
			System.out.println("Mk_y : " + mkDto.getMk_y());
			System.out.println("Sb_title : " + mkDto.getStoryboard().getSb_title());

		/*	for (String imgPath : mkDto.getStoryboard().getImgPathList()) {
				System.out.println("storyboard_Img_Path" + imgPath);
			}*/
		}
		for (RouteDto rtDto : smDto.getRouteList()) {
			System.out.println("getRt_type : " + rtDto.getRt_type());
			List<Point> pointList = rtDto.getMoving_route();
			for (int i = 0; i < pointList.size(); i++) {
				System.out.println(pointList.get(i));
			}
		}
	}
}
