package com.gregttn.yt.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gregttn.yt.model.ChannelData;

public class GsonParser {
	private Gson gson;
	
	public GsonParser() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(ChannelData.class, new YTChannelDataDeserializer());
		this.gson = gsonBuilder.create();
	}
	
	public <T> T getObject(String json, Class<T> clazz) {
		return gson.fromJson(json, clazz);
	}
}
