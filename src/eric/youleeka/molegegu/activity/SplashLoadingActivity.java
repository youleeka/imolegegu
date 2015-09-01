package eric.youleeka.molegegu.activity;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;
import eric.youleeka.molegegu.R;
import eric.youleeka.molegegu.utills.LoadImageTask;

/**
 * The loading activity
 * 
 * @author deng.jb
 * @createDate 2010-9-17
 * @version v0.1
 */
public class SplashLoadingActivity extends Activity{

	private String FACEIMAGE_PATH = "loading_image";
	private String URL_BG = "http://www.molegegu.com/bg.png";
	private Bitmap  m_faceBitmap = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_loading);
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.loading_bg);
		m_faceBitmap = BitmapFactory.decodeFile(getImageCachePath());
		if(m_faceBitmap != null){
			linearLayout.setBackgroundDrawable(new BitmapDrawable(m_faceBitmap));
		}
		init();
		loadImage(URL_BG);
	}

	private void init() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						 startActivity(new Intent(getBaseContext(),MolegeguMainActivity.class));
						 SplashLoadingActivity.this.finish();
					}
				});
			}
		}).start();
		return;
	}
	
	
	private String getImageCachePath(){
		StringBuilder sbCachePath = new StringBuilder();
		sbCachePath.append(getCacheDir().getAbsolutePath());
		sbCachePath.append(File.separator);
		sbCachePath.append(FACEIMAGE_PATH);
		return sbCachePath.toString(); 
	}
	
	private void loadImage(String url){
		LoadImageTask imgTask = new LoadImageTask(this,FACEIMAGE_PATH);
		imgTask.setNotReturnBitmap(true);
		imgTask.setUseCache(false);
		imgTask.execute(url);
		
	}


}