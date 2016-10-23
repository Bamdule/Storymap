package com.storymap.common.vo;

/**
 * Created by kim on 2016-10-23.
 */

public class Sub_City {
    private String sub_city_code;
    private String sub_city_name;

    public String getSub_city_code() {
        return sub_city_code;
    }

    public void setSub_city_code(String sub_city_code) {
        this.sub_city_code = sub_city_code;
    }

    public String getSub_city_name() {
        return sub_city_name;
    }

    public void setSub_city_name(String sub_city_name) {
        this.sub_city_name = sub_city_name;
    }

    @Override
    public String toString() {
        return "Sub_City{" +
                "sub_city_code='" + sub_city_code + '\'' +
                ", sub_city_name='" + sub_city_name + '\'' +
                '}';
    }
}
