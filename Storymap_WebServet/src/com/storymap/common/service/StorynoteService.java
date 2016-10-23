package com.storymap.common.service;

import java.util.List;

import com.storymap.common.dao.StorynoteDao;
import com.storymap.common.dto.StorynoteDto;

public class StorynoteService {
	private static StorynoteService instance = null;
	StorynoteDao snDao =StorynoteDao.getInstance();
	private StorynoteService() {
	}

	public static StorynoteService getInstance() {
		if (instance == null)
			instance = new StorynoteService();
		return instance;
	}
	public String createNextStorynoteCode(int mem_code){
		return snDao.createNextStorynoteCode(mem_code);
	}
	public boolean insertStorynote(int mem_code, String sn_code, StorynoteDto snDto){
		return snDao.insertStorynote(mem_code, sn_code, snDto);
	}
	public StorynoteDto selectStorynote(String sn_code){
		return snDao.selectStorynote(sn_code);
	}
	public List<StorynoteDto> selectAllStorynote(int mem_code){
		return snDao.selectAllStorynote(mem_code);
	}
	
	public boolean updateStorynoteImage(String sn_img_path,String sn_code){
		return snDao.updateStorynoteImage(sn_img_path, sn_code);
	}
	
}
