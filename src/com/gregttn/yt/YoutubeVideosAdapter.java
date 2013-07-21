package com.gregttn.yt;

import com.gregttn.yt.model.ChannelData;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class YoutubeVideosAdapter extends ArrayAdapter<ChannelData>{
	private ChannelData channelData;
	
	public YoutubeVideosAdapter(Context context, ChannelData data) {
		super(context, 0);
		this.channelData = data;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return null;
	}
}
