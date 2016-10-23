package com.storymap.common.vo;

import java.util.List;

/**
 * Created by kim on 2016-10-23.
 */

public class City {
    private String city_code;
    private String city_name;

    private List<Sub_City>  sub_cityList;


    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public List<Sub_City> getSub_cityList() {
        return sub_cityList;
    }

    public void setSub_cityList(List<Sub_City> sub_cityList) {
        this.sub_cityList = sub_cityList;
    }

    @Override
    public String toString() {
        return "City{" +
                "city_code='" + city_code + '\'' +
                ", city_name='" + city_name + '\'' +
                ", sub_cityList=" + sub_cityList +
                '}';
    }
}
