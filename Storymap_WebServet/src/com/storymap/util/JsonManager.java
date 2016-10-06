package com.storymap.util;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.storymap.common.dto.MarkerDto;
import com.storymap.common.dto.StoryboardDto;
import com.storymap.common.dto.StorymapDto;

public class JsonManager {

	public static void main(String[] args) {
		JsonManager test = new JsonManager();
	}
	
	
	
	//List를 JsonArray로 변환
	public JsonArray listToJsonArray(List<StorymapDto> list){
		return new Gson().toJsonTree(list, new TypeToken<List<StorymapDto>>() {}.getType()).getAsJsonArray();
	}
	
	
	//JsonArrayString을 List로 변환
	public List jsonArrayToList(String JsonArrayStr){
		List<MarkerDto> list =null;
		Gson gson = new Gson();
		list=gson.fromJson(JsonArrayStr, new TypeToken<List>(){}.getType());
		return list;
	}
	//인스턴스를 JsonObject로 변환
	public JsonObject instanceToJsonObject(Object object){
		return new JsonParser().parse(new Gson().toJson(object)).getAsJsonObject();
	}
	//인스턴스를 JsonString으로 변환
	public String instanceToJsonString(Object object){
		return new Gson().toJson(object);
	}
	
	//json을 Instance로 변환
	public Object jsonStringToInstance(String json){
		return new Gson().fromJson(json, StorymapDto.class);
	}
	

}
