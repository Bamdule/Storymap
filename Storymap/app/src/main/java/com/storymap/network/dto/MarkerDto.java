package com.storymap.network.dto;

import java.util.List;

/**
 * Created by S501-04 on 2016-09-21.
 */
public class MarkerDto {
	private int mk_seq;
    private String mk_type;
    private double mk_x;
    private double mk_y;
    private String is_sb;
    private StoryboardDto storyboard;
    
    
    
    public int getMk_seq() {
		return mk_seq;
	}

	public void setMk_seq(int mk_seq) {
		this.mk_seq = mk_seq;
	}

	public String getMk_type() {
        return mk_type;
    }

    public void setMk_type(String mk_type) {
        this.mk_type = mk_type;
    }

    public double getMk_x() {
		return mk_x;
	}

	public void setMk_x(double mk_x) {
		this.mk_x = mk_x;
	}

	public double getMk_y() {
		return mk_y;
	}

	public void setMk_y(double mk_y) {
		this.mk_y = mk_y;
	}

	public String getIs_sb() {
        return is_sb;
    }

    public void setIs_sb(String is_sb) {
        this.is_sb = is_sb;
    }
	

	public StoryboardDto getStoryboard() {
		return storyboard;
	}

	public void setStoryboard(StoryboardDto storyboard) {
		this.storyboard = storyboard;
	}


	
	@Override
	public String toString() {
		return "MarkerDto [mk_seq=" + mk_seq + ", mk_type=" + mk_type + ", mk_x=" + mk_x + ", mk_y=" + mk_y + ", is_sb="
				+ is_sb + ", storyboard=" + storyboard + "]";
	}
    
}
