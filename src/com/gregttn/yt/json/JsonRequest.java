package com.gregttn.yt.json;

import java.io.UnsupportedEncodingException;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

public class JsonRequest<T> extends Request<T>{
	private Listener<T> listener;
	private Class<T> clazz;
	private GsonParser parser;

	public JsonRequest(Class<T> clazz, Listener<T> listener, ErrorListener errorListener, String url) {
		super(Method.GET, url, errorListener);
		this.listener = listener;
		this.clazz = clazz;
		this.parser = new GsonParser();
	}
	
	@Override
	protected void deliverResponse(T response) {
		listener.onResponse(response);
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
			String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
			
			return Response.success(parser.getObject(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		}
	}
}
