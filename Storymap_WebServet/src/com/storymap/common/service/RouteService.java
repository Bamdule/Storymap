package com.storymap.common.service;

import java.util.List;

import com.storymap.common.dao.RouteDao;
import com.storymap.common.dto.RouteDto;

public class RouteService {
	private RouteDao rDao =RouteDao.getInstance();
	private static RouteService instance = null;

	private RouteService() {
	}
	public static RouteService getInstance() {
		if (instance == null)
			instance = new RouteService();
		return instance;
	}
	
	public List<RouteDto> selectAllRoute(String sm_code){
		return rDao.selectAllRoute(sm_code);
	}
}
