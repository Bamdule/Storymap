package com.storymap.common.dto;

import java.util.List;

/**
 * Created by S501-04 on 2016-09-21.
 */
public class RouteDto {
	private String rt_type;
	private List<Point> moving_route;



	public List<Point> getMoving_route() {
		return moving_route;
	}

	public void setMoving_route(List<Point> moving_route) {
		this.moving_route = moving_route;
	}

    public String getRt_type() {
        return rt_type;
    }

    public void setRt_type(String rt_type) {
        this.rt_type = rt_type;
    }

	@Override
	public String toString() {
		return "RouteDto [rt_type=" + rt_type + ", moving_route=" + moving_route + "]";
	}

    
  
}



