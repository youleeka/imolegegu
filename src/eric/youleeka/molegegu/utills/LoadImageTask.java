/*
 * Copyright (C) 2012 The Founder Mobile Media Technology Android EPaper Project
 * 
 * Founder Mobile Media PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package eric.youleeka.molegegu.utills;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;
import eric.youleeka.molegegu.net.NetworkConnectionException;
import eric.youleeka.molegegu.net.NetworkRequest;

/**
 * @author Administrator
 * @createDate 2013-8-28
 * @version v0.1
 */
public class LoadImageTask extends AsyncTask<String, Integer, AsyncTaskResult>{

	private Context m_ctx;
	private IAsyncTaskHandler m_callback;
	private String m_cacheFilename;
	private boolean m_bCache = true;
	private boolean m_notRetunBitmap = false;

	public LoadImageTask(Context context, IAsyncTaskHandler callback, String cacheFilename) {
		m_ctx = context;
		m_callback = callback;
		m_cacheFilename = cacheFilename;
		m_bCache = true;
	}
	
	public LoadImageTask(Context context, String cacheFilename) {
		m_ctx = context;
		m_cacheFilename = cacheFilename;
		m_bCache = true;
	}
	
	public LoadImageTask(Context context, IAsyncTaskHandler callback) {
		m_ctx = context;
		m_callback = callback;
		m_cacheFilename = null;
		m_bCache = false;
	}
	
	public LoadImageTask setUseCache(boolean bCache){
		if(TextUtils.isEmpty(m_cacheFilename)){
			m_bCache = false;
		}else{
			m_bCache = bCache;
		}
		return this;
	}
	
	public LoadImageTask setNotReturnBitmap(boolean bNoBitmap){
		m_notRetunBitmap = bNoBitmap;
		return this;
	}
	
	@Override
	protected AsyncTaskResult doInBackground(String... params) {
		
		Bitmap bmp = null;
		String filePath = getFullCacheFileName();
		if(m_bCache){
			if(m_notRetunBitmap){
				File file = new File(filePath);
				if(file.exists())
					return makeUpResult(true, m_cacheFilename, null, null);
			}else{
				bmp = BitmapFactory.decodeFile(filePath);
				if(bmp != null){
					return makeUpResult(true, m_cacheFilename, bmp, null);
				}
			}
		}
		
		NetworkRequest networkRequest = new NetworkRequest(m_ctx);
		InputStream is = null;
		try {
			is = networkRequest.buildRequestInputStream(params[0], NetworkRequest.HTTP_METHOD_GET, null);
		} catch (NetworkConnectionException e) {
			e.printStackTrace();
			return makeUpResult(false, m_cacheFilename, null, e);
		} catch (Exception e) {
			e.printStackTrace();
			return makeUpResult(false, m_cacheFilename,  null, e);
		}

		if (is == null) {
			return makeUpResult(false, m_cacheFilename,  null, new Exception());
		}

		if(m_notRetunBitmap){
			//if(m_bCache){
				saveCacheFile(is, filePath);
			//}
			return makeUpResult(true, m_cacheFilename, null, null);
		}
		
		if(m_bCache){
			saveCacheFile(is, filePath);
			bmp = BitmapFactory.decodeFile(filePath);
		}else{
			bmp = BitmapFactory.decodeStream(is);
		}

		if(bmp == null){
			return makeUpResult(true, m_cacheFilename, null, null);
		}
		
		return makeUpResult(true, m_cacheFilename, bmp, null);
	}

	@Override
	protected void onPostExecute(AsyncTaskResult result) {
		super.onPostExecute(result);
		//m_callback.handleResult(result);
	}
	
	
	private String getFullCacheFileName(){
		if(TextUtils.isEmpty(m_cacheFilename))
			return null;
		
		StringBuilder sbCachePath = new StringBuilder();
		sbCachePath.append(m_ctx.getCacheDir().getAbsolutePath());
		sbCachePath.append(File.separator);
		sbCachePath.append(m_cacheFilename);
		
		return sbCachePath.toString();
	}
	
	private void saveCacheFile(InputStream is,String filePath){
		if(TextUtils.isEmpty(filePath))
			return;
		try {
			File file = new File(filePath.toString());
			if (file.exists()) {
				file.delete();
				file = new File(filePath.toString());
			}

			file.createNewFile();
			FileOutputStream fos;
			fos = new FileOutputStream(file);

			byte[] buffer = new byte[2 * 1024];

			int len = 0;
			while ((len = is.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.flush();

			fos.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	private AsyncTaskResult makeUpResult(boolean isSuccess, String info,  Bitmap bmp, Exception e) {
		AsyncTaskResult result = new AsyncTaskResult(isSuccess);
		result.setInfo(info);
		result.setBitmap(bmp);
		result.setException(e);		
		return result;
	}
}
