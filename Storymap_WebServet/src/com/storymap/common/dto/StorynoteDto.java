package com.storymap.common.dto;

import java.util.List;

/*
 *      mem_code 		NUMBER	NOT NULL
   , sn_code 		VARCHAR2(30)
   , sn_seq			NUMBER			DEFAULT 1
   , sn_title		VARCHAR2(100)	NOT NULL
   , sn_content		VARCHAR2(3000)	NOT NULL
   , sn_img_path	VARCHAR2(1000)	NOT NULL
   , sn_date		DATE			DEFAULT sysdate
   , sn_readcount	NUMBER			DEFAULT 0
   , sn_likecount	NUMBER			DEFAULT 0
   , theme_code		VARCHAR2(10)
 */
public class StorynoteDto {
	private int mem_code;
	private String sn_code;
	private int sn_seq;
	private String sn_title;
	private String sn_content;
	private String sn_img_path;
	private String sn_date;
	private int sn_readcount;
	private int sn_likecount;
	private String theme_code;
	private String theme_name;
	private List<String> Storymap_codes;
	private List<StorymapDto> storymapDtos;
	
	
	public List<StorymapDto> getStorymapDtos() {
		return storymapDtos;
	}
	public void setStorymapDtos(List<StorymapDto> storymapDtos) {
		this.storymapDtos = storymapDtos;
	}
	
	public List<String> getStorymap_codes() {
		return Storymap_codes;
	}
	public void setStorymap_codes(List<String> storymap_codes) {
		Storymap_codes = storymap_codes;
	}
	public int getMem_code() {
		return mem_code;
	}
	public void setMem_code(int mem_code) {
		this.mem_code = mem_code;
	}
	public String getSn_code() {
		return sn_code;
	}
	public void setSn_code(String sn_code) {
		this.sn_code = sn_code;
	}
	public int getSn_seq() {
		return sn_seq;
	}
	public void setSn_seq(int sn_seq) {
		this.sn_seq = sn_seq;
	}
	public String getSn_title() {
		return sn_title;
	}
	public void setSn_title(String sn_title) {
		this.sn_title = sn_title;
	}
	public String getSn_content() {
		return sn_content;
	}
	public void setSn_content(String sn_content) {
		this.sn_content = sn_content;
	}
	public String getSn_img_path() {
		return sn_img_path;
	}
	public void setSn_img_path(String sn_img_path) {
		this.sn_img_path = sn_img_path;
	}
	public String getSn_date() {
		return sn_date;
	}
	public void setSn_date(String sn_date) {
		this.sn_date = sn_date;
	}
	public int getSn_readcount() {
		return sn_readcount;
	}
	public void setSn_readcount(int sn_readcount) {
		this.sn_readcount = sn_readcount;
	}
	public int getSn_likecount() {
		return sn_likecount;
	}
	public void setSn_likecount(int sn_likecount) {
		this.sn_likecount = sn_likecount;
	}
	public String getTheme_code() {
		return theme_code;
	}
	public void setTheme_code(String theme_code) {
		this.theme_code = theme_code;
	}
	public String getTheme_name() {
		return theme_name;
	}
	public void setTheme_name(String theme_name) {
		this.theme_name = theme_name;
	}
	@Override
	public String toString() {
		return "StorynoteDto [mem_code=" + mem_code + ", sn_code=" + sn_code + ", sn_seq=" + sn_seq + ", sn_title="
				+ sn_title + ", sn_content=" + sn_content + ", sn_img_path=" + sn_img_path + ", sn_date=" + sn_date
				+ ", sn_readcount=" + sn_readcount + ", sn_likecount=" + sn_likecount + ", theme_code=" + theme_code
				+ ", theme_name=" + theme_name + ", Storymap_codes=" + Storymap_codes + ", storymapDtos=" + storymapDtos
				+ "]";
	}
	
	

}
