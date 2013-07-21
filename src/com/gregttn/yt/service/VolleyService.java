package com.gregttn.yt.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyService {
	private RequestQueue requestQueue;
	
	public VolleyService(Context context) {
		this.requestQueue = Volley.newRequestQueue(context);
	}
	
	public <T> void addRequest(Request<T> request) {
		requestQueue.add(request);
	}
}
