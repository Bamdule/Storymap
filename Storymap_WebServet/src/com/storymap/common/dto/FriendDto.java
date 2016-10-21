package com.storymap.common.dto;

public class FriendDto {
	private int mem_code;
	private int friend_code;
	private int reg_status;
	private String mem_name;
	private String mem_img_path;
	
	
	
	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_img_path() {
		return mem_img_path;
	}
	
	public void setMem_img_path(String mem_img_path) {
		this.mem_img_path = mem_img_path;
	}
	public int getMem_code() {
		return mem_code;
	}
	public int getFriend_code() {
		return friend_code;
	}
	public int getReg_status() {
		return reg_status;
	}
	public void setMem_code(int mem_code) {
		this.mem_code = mem_code;
	}
	public void setFriend_code(int friend_code) {
		this.friend_code = friend_code;
	}
	public void setReg_status(int reg_status) {
		this.reg_status = reg_status;
	}
	@Override
	public String toString() {
		return "FriendDto [mem_code=" + mem_code + ", friend_code=" + friend_code + ", reg_status=" + reg_status
				+ ", mem_name=" + mem_name + ", mem_img_path=" + mem_img_path + "]";
	}
	
	
	

}
