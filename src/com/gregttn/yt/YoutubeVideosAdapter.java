package com.gregttn.yt;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.gregttn.yt.model.ChannelData;
import com.gregttn.yt.model.VideoEntry;
import com.gregttn.yt.service.YoutubeService;
import com.gregttn.yt.utils.ImageCacheHelper;

public class YoutubeVideosAdapter extends ArrayAdapter<ChannelData>{
	private List<VideoEntry> videos;
	private YoutubeService ytService = new YoutubeService();
	
	public YoutubeVideosAdapter(Context context, ChannelData data) {
		super(context, R.layout.video_entry);
		this.videos = data.getVideos();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		
		if(convertView == null) {
			LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(R.layout.video_entry, null);
			
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) convertView.findViewById(R.id.title);
			viewHolder.views = (TextView) convertView.findViewById(R.id.views);
			viewHolder.likes = (TextView) convertView.findViewById(R.id.likes);
			viewHolder.dislikes = (TextView) convertView.findViewById(R.id.dislikes);
			viewHolder.coverImage = (NetworkImageView) convertView.findViewById(R.id.coverImage);
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		
		if(videos.size() > position) { 
			VideoEntry videoEntry = videos.get(position);
			String thumbnailUrl = ytService.getThumbnailUrl(videoEntry.getVideoId());
			
			viewHolder.title.setText(videoEntry.getTitle());
			viewHolder.views.setText(videoEntry.getViewsForDisplay());
			viewHolder.likes.setText(videoEntry.getLikesForDisplay());
			viewHolder.dislikes.setText(videoEntry.getDislikesForDisplay());
			viewHolder.coverImage.setImageUrl(thumbnailUrl, ImageCacheHelper.getInstance().getImageLoader());
		}		
		
		return convertView;
	}
	
	static class ViewHolder {
		TextView title;
		TextView views;
		TextView likes;
		TextView dislikes;
		NetworkImageView coverImage;
	}
	
	@Override
	public int getCount() {
		return videos.size();
	}
}
