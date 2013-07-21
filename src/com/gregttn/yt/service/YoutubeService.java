package com.gregttn.yt.service;

public class YoutubeService {
	private final static String CHANNEL_URL = "http://gdata.youtube.com/feeds/api/videos?author=%s&max-results=%d&v=2&alt=json";
	private final static String THUMBNAIL_URL = "http://img.youtube.com/vi/%s/0.jpg";
	
	public String getChannelUrl(String channelName, int videosLimit) {
		return String.format(CHANNEL_URL, channelName, videosLimit);
	}
	
	public String getThumbnailUrl(String videoId) {
		return String.format(THUMBNAIL_URL, videoId);
	}
}
