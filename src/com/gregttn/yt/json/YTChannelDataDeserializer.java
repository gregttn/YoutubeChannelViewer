package com.gregttn.yt.json;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.gregttn.yt.model.ChannelData;
import com.gregttn.yt.model.VideoEntry;

public class YTChannelDataDeserializer implements JsonDeserializer<ChannelData>{
	@Override
	public ChannelData deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
		ChannelData channelData = new ChannelData();
		List<VideoEntry> videoEntries = new ArrayList<VideoEntry>();
		
		JsonObject jsonObject = json.getAsJsonObject();
		JsonObject feedObject = jsonObject.getAsJsonObject("feed");
		
		JsonArray videoEntriesArray = feedObject.getAsJsonArray("entry");

		Iterator<JsonElement> it = videoEntriesArray.iterator();
		while(it.hasNext()) {
			JsonObject entry = it.next().getAsJsonObject();
			videoEntries.add(extractVideoEntry(entry));
		}
		
		channelData.setVideos(videoEntries);
		return channelData;
	}
	
	private VideoEntry extractVideoEntry(JsonObject json) {
		String id = json.getAsJsonObject("id").get("$t").getAsString();
		String title = json.getAsJsonObject("title").get("$t").getAsString();
		Long views = json.getAsJsonObject("yt$statistics").get("viewCount").getAsLong();
		Long likes = json.getAsJsonObject("yt$rating").get("numLikes").getAsLong();
		Long dislikes = json.getAsJsonObject("yt$rating").get("numDislikes").getAsLong();
		
		VideoEntry entry = new VideoEntry.Builder(id)
				.setTitle(title)
				.setViews(views)
				.setLikes(likes)
				.setDislikes(dislikes)
				.build();
		
		return entry;
	}

}
