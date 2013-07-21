package com.gregttn.yt.model;

public class VideoEntry {
	private String id;
	private String title;
	private long views;
	private long likes;
	private long dislikes;
	
	public long getDislikes() {
		return dislikes;
	}
	
	public void setDislikes(long dislikes) {
		this.dislikes = dislikes;
	}
	
	public long getLikes() {
		return likes;
	}
	
	public void setLikes(long likes) {
		this.likes = likes;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public long getViews() {
		return views;
	}

	public void setViews(long views) {
		this.views = views;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	public static class Builder {
		private VideoEntry entry;
		
		public Builder(String videoId) {
			this.entry = new VideoEntry();
			this.entry.setId(videoId);
		}
		
		public Builder setTitle(String title) {
			this.entry.setTitle(title);
			return this;
		}

		public Builder setViews(long views) {
			this.entry.setViews(views);
			return this;
		}

		public Builder setLikes(long likes) {
			this.entry.setLikes(likes);
			return this;
		}

		public Builder setDislikes(long dislikes) {
			this.entry.setDislikes(dislikes);
			return this;
		}
		
		public VideoEntry build() {
			return entry;
		}
	}
}
