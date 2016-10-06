package com.storymap.common.service;

import java.util.List;

import com.storymap.common.dao.MarkerDao;
import com.storymap.common.dto.MarkerDto;

public class MarkerService {
	private static MarkerService instance = null;
	private MarkerDao mkDao = MarkerDao.getInstance();
	private MarkerService() {
	}
	public static MarkerService getInstance() {
		if (instance == null)
			instance = new MarkerService();
		return instance;
	}
	
	public List<MarkerDto> selectAllMarker(String sm_code){
		List<MarkerDto> mkList =mkDao.selectAllMarker(sm_code);
		return mkList;
	}
}
