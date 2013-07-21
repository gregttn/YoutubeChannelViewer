package com.gregttn.yt;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.gregttn.yt.json.JsonRequest;
import com.gregttn.yt.model.ChannelData;
import com.gregttn.yt.service.VolleyService;
import com.gregttn.yt.service.YoutubeService;
import com.gregttn.yt.utils.ImageCacheHelper;

public class MainActivity extends Activity implements Listener<ChannelData>, ErrorListener {
	private static final String CHANNEL_NAME = "RayWilliamJohnson";
	private static final int MAX_VIDEOS = 20;
	private static final String TAG = "MainActivity";
	
	private ListView channelVideosList;

	private YoutubeService ytService;
	private VolleyService volleyService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		volleyService  = new VolleyService(this);
		ytService = new YoutubeService();
		channelVideosList = (ListView) findViewById(R.id.channelVideos);
		
		initImageCache();
		dispatchChannelRequest();
	}
	
	private void initImageCache() {
		ImageCacheHelper cacheHelper = ImageCacheHelper.getInstance();
		cacheHelper.init(this, volleyService.getRequestQueue());
	}
	
	private void dispatchChannelRequest() {
		String channelUrl = ytService.getChannelUrl(CHANNEL_NAME, MAX_VIDEOS);
		JsonRequest<ChannelData> request = new JsonRequest<ChannelData>(ChannelData.class, this, this, channelUrl);
		volleyService.addRequest(request);
	}

	@Override
	public void onErrorResponse(VolleyError error) {
		AlertDialog errorDialog = new AlertDialog.Builder(this)
			.setTitle("Connection error")
			.setMessage("Could not download channel data")
			.create();
		
		errorDialog.show();
		
		Log.wtf(TAG, "Error while downloading data", error);
	}

	@Override
	public void onResponse(ChannelData channelData) {
		if(channelData != null) {
			YoutubeVideosAdapter adapter = new YoutubeVideosAdapter(this, channelData);
			channelVideosList.setAdapter(adapter);
			adapter.notifyDataSetChanged();

			Log.i(TAG, "Updated channel data");
		}
	}
}
