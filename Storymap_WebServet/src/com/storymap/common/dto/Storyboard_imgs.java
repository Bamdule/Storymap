package com.storymap.common.dto;

import java.util.List;

public class Storyboard_imgs {
	private int mk_seq;
	private List<String> imageList;
	private List<String> thumbnailList;

	public List<String> getThumbnailList() {
		return thumbnailList;
	}
	public void setThumbnailList(List<String> thumbnailList) {
		this.thumbnailList = thumbnailList;
	}
	public int getMk_seq() {
		return mk_seq;
	}
	public List<String> getImageList() {
		return imageList;
	}
	public void setMk_seq(int mk_seq) {
		this.mk_seq = mk_seq;
	}
	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}
	@Override
	public String toString() {
		return "Storyboard_imgs [mk_seq=" + mk_seq + ", imageList=" + imageList + ", thumbnailList=" + thumbnailList
				+ "]";
	}
	

}
