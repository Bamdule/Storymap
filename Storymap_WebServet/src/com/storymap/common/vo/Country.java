package com.storymap.common.vo;

import java.util.List;

/**
 * Created by kim on 2016-10-23.
 */

public class Country {
    private static Country instance = null;
    private Country(){}
    public static Country getInstance(){
        if(instance==null)
            instance=new Country();
        return instance;
    }

    String countryName;
    List<City> cityList;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryName='" + countryName + '\'' +
                ", cityList=" + cityList +
                '}';
    }
}
