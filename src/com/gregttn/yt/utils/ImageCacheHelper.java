package com.gregttn.yt.utils;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

import android.content.Context;

public class ImageCacheHelper {
	private ImageLoader imageLoader;
	
	private static class Holder {
		private final static ImageCacheHelper INSTANCE = new ImageCacheHelper();
	}
	
	private ImageCacheHelper() {
	}
	
	public static ImageCacheHelper getInstance() {
		return Holder.INSTANCE;
	}
	
	public void init(Context context, RequestQueue queue) {
		ImageLruCache cache = new ImageLruCache();
		imageLoader = new ImageLoader(queue, cache);
	}
	
	public ImageLoader getImageLoader() {
		return imageLoader;
	}
}
