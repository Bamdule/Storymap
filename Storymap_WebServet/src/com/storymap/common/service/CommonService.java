package com.storymap.common.service;

import com.storymap.common.dao.CommonDao;
import com.storymap.common.vo.Country;

public class CommonService {
	private static CommonService instance = null;
	private CommonService() {
	}
	public static CommonService getInstance() {
		if (instance == null)
			instance = new CommonService();
		return instance;
	}
	private CommonDao commonDao = CommonDao.getInstance();
	public Country selectAllCityList(){
		return commonDao.selectAllCityList();
	}
}
