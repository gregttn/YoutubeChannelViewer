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

public class MainActivity extends Activity implements Listener<ChannelData>, ErrorListener {
	private static final String CHANNEL_NAME = "djfazii";
	private static final int MAX_VIDEOS = 2;
	private static final String TAG = "MainActivity";
	
	private ListView channelVideosList;

	private YoutubeService ytService = new YoutubeService();
	private VolleyService volleyService = new VolleyService(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		channelVideosList = (ListView) findViewById(R.id.channelVideos);

		dispatchChannelRequest();
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
		
	}
}
