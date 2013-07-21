package com.gregttn.yt;

import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.gregttn.yt.model.ChannelData;
import com.gregttn.yt.model.VideoEntry;
import com.gregttn.yt.service.VolleyService;
import com.gregttn.yt.service.YoutubeService;
import com.gregttn.yt.utils.ImageCacheHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
			viewHolder.coverImage = (NetworkImageView) convertView.findViewById(R.id.coverImage);
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		
		if(videos.size() > position && videos.get(position) != null) { 
			viewHolder.title.setText(videos.get(position).getTitle());
			viewHolder.coverImage.setImageUrl("http://www.youtube.com/img/pic_youtubelogo_123x63.gif", ImageCacheHelper.getInstance().getImageLoader());
		}		
		
		return convertView;
	}
	
	static class ViewHolder {
		TextView title;
		NetworkImageView coverImage;
	}
	
	@Override
	public int getCount() {
		return videos.size();
	}
}
