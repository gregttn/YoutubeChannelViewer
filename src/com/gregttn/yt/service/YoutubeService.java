package com.gregttn.yt.service;

public class YoutubeService {
	private final static String CHANNEL_URL = "http://gdata.youtube.com/feeds/api/videos?author=%s&max-results=%d&v=2&alt=json";

	public String getChannelUrl(String channelName, int videosLimit) {
		return String.format(CHANNEL_URL, channelName, videosLimit);
	}
}
