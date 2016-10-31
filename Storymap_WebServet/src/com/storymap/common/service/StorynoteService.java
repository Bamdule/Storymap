package com.storymap.common.service;

import java.util.ArrayList;
import java.util.List;

import com.storymap.common.dao.StorynoteDao;
import com.storymap.common.dto.StorymapDto;
import com.storymap.common.dto.StorynoteDto;

public class StorynoteService {
	private static StorynoteService instance = null;
	StorymapService storymapService =StorymapService.getInstance();
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
	public StorynoteDto selectStorynoteDetail(String sn_code){
		StorynoteDto snDto =snDao.selectStorynoteDetail(sn_code);
		if(snDto.getStorymap_codes()!=null && snDto.getStorymap_codes().size()>0){
			List<StorymapDto> smDtoList =new ArrayList<StorymapDto>();
			for(String sm_code : snDto.getStorymap_codes()){
				smDtoList.add(storymapService.selectStorymapDetail(sm_code));
			}
			snDto.setStorymapDtos(smDtoList);
					
		}
		
		return snDto;
	}
	public List<StorynoteDto> selectAllStorynote(){
		return snDao.selectAllStorynote();
	}
	
	public boolean insertStorymapOfStorynote(String sn_code, List<String> StorymapCodes){
		return snDao.insertStorymapOfStorynote(sn_code, StorymapCodes);
	
	}
	public boolean updateStorynoteImage(String sn_img_path,String sn_code){
		return snDao.updateStorynoteImage(sn_img_path, sn_code);
	}
	
	public List<StorynoteDto> selectAllStorynoteByMem(int mem_code){
		return snDao.selectAllStorynoteByMem(mem_code);
	}
	
}
