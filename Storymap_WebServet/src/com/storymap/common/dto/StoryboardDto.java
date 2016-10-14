package com.storymap.common.dto;

import java.util.List;

/**
 * Created by S501-04 on 2016-09-21.
 */
public class StoryboardDto {
	private int mk_seq;
	private String sb_title;
	private String sb_content;
	private List<String> imgPathList;

	/*
    mk_seq
	sb_title
	sb_content
	imgPathList


			SM_CODE	NOT NULL	VARCHAR2(30)
		MK_SEQ	NOT NULL	NUMBER
		SB_TITLE		VARCHAR2(100)
		SB_CONTENT	NOT NULL	VARCHAR2(3000)
	 */
	

    public String getSb_title() {
		return sb_title;
	}

	public int getMk_seq() {
		return mk_seq;
	}

	public void setMk_seq(int mk_seq) {
		this.mk_seq = mk_seq;
	}

	
	public void setSb_title(String sb_title) {
		this.sb_title = sb_title;
	}
	

	
	

	public String getSb_content() {
		return sb_content;
	}

	public void setSb_content(String sb_content) {
		this.sb_content = sb_content;
	}
	


	public List<String> getImgPathList() {
		return imgPathList;
	}

	public void setImgPathList(List<String> imgPathList) {
		this.imgPathList = imgPathList;
	}

	@Override
	public String toString() {
		return "StoryboardDto [mk_seq=" + mk_seq + ", sb_title=" + sb_title + ", sb_content=" + sb_content
				+ ", imgPathList=" + imgPathList + "]";
	}


	

    
}
