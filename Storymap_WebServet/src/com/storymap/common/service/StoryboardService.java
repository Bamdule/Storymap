package com.storymap.common.service;


import com.storymap.common.dao.StoryboardDao;
import com.storymap.common.dto.StoryboardDto;
import com.storymap.common.dto.Storyboard_imgs;
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
		return sbDto;
	}
	public Storyboard_imgs selectStoryboardImgs(String sm_code,int mk_seq){
		return sbDao.selectStoryboardImgs(sm_code, mk_seq);
	}

}
