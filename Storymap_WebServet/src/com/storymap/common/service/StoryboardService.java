package com.storymap.common.service;

import java.util.List;

import com.storymap.common.dao.StoryboardDao;
import com.storymap.common.dto.StoryboardDto;
public class StoryboardService {
	private StoryboardDao sbDao =StoryboardDao.getInstance();
	private static StoryboardService instance = null;

	private StoryboardService() {
	}
	public static StoryboardService getInstance() {
		if (instance == null)
			instance = new StoryboardService();
		return instance;
	}
	public StoryboardDto selectStoryboard(String sm_code,int mk_seq){
		StoryboardDto sbDto=sbDao.selectStoryboard(sm_code, mk_seq);
		//sbDto.setImgPathList(selectStoryboardImgs(sm_code,mk_seq));
		return sbDto;
	}
	public List<String> selectStoryboardImgs(String sm_code,int mk_seq){
		return sbDao.selectStoryboardImgs(sm_code, mk_seq);
	}
	public List<Integer> findStoryboardOfMarker(String sm_code){
		return sbDao.findStoryboardOfMarker(sm_code);
	}
}
