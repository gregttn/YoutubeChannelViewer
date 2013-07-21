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
	private static final String DISLIKES = "numDislikes";
	private static final String LIKES = "numLikes";
	private static final String VIEW_COUNT = "viewCount";
	private static final String VALUE = "$t";
	private static final String RATING = "yt$rating";
	private static final String STATISTICS = "yt$statistics";
	private static final String TITLE = "title";
	private static final String ID = "id";
	private static final String ENTRIES = "entry";
	private static final String FEED = "feed";

	@Override
	public ChannelData deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
		ChannelData channelData = new ChannelData();
		List<VideoEntry> videoEntries = new ArrayList<VideoEntry>();
		
		JsonObject jsonObject = json.getAsJsonObject();
		JsonObject feedObject = jsonObject.getAsJsonObject(FEED);
		
		JsonArray videoEntriesArray = feedObject.getAsJsonArray(ENTRIES);

		Iterator<JsonElement> it = videoEntriesArray.iterator();
		while(it.hasNext()) {
			JsonObject entry = it.next().getAsJsonObject();
			videoEntries.add(extractVideoEntry(entry));
		}
		
		channelData.setVideos(videoEntries);
		return channelData;
	}
	
	private VideoEntry extractVideoEntry(JsonObject json) {
		String id = json.getAsJsonObject(ID).get(VALUE).getAsString();
		String title = json.getAsJsonObject(TITLE).get(VALUE).getAsString();
		Long views = json.getAsJsonObject(STATISTICS).get(VIEW_COUNT).getAsLong();
		Long likes = json.getAsJsonObject(RATING).get(LIKES).getAsLong();
		Long dislikes = json.getAsJsonObject(RATING).get(DISLIKES).getAsLong();
		
		VideoEntry entry = new VideoEntry.Builder(id)
				.setTitle(title)
				.setViews(views)
				.setLikes(likes)
				.setDislikes(dislikes)
				.build();
		
		return entry;
	}

}
