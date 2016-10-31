package com.storymap.common.dto;

public class BlogDto {
	private int mem_code;
	private String image_id;

	public int getMem_code() {
		return mem_code;
	}
	public void setMem_code(int mem_code) {
		this.mem_code = mem_code;
	}
	public String getImage_id() {
		return image_id;
	}
	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}
	@Override
	public String toString() {
		return "BlogDto [mem_code=" + mem_code + ", image_id=" + image_id + "]";
	}
	
	

}
