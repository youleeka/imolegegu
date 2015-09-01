package eric.youleeka.molegegu.widget;

import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import eric.youleeka.molegegu.R;
import eric.youleeka.molegegu.widget.ScrollLayout.OnScreenChangeListener;

public class ViewFlow extends LinearLayout implements Runnable, OnTouchListener {
	private LayoutInflater mInflater;
	private Context mContext;

	private int[] imageIds = { R.color.black, R.color.black,
			R.color.black };
	private String[]  imageUrl = {};
	private ScrollLayout mScrollLayout;
	private int currentItem;
	private int duration = 2500; // 间隔秒数
	private boolean isBack; // 是否回滚
	private boolean isAutoPlay = false;
	private ArrayList<Bitmap> imgLists = null;

	public ViewFlow(Context context) {
		super(context);
		mContext = context;
		initView();
	}

	public ViewFlow(Context context, int[] imageIds) {
		super(context);
		mContext = context;
		this.imageIds = imageIds;
		initView();
	}

	public ViewFlow(Context context, ArrayList<Bitmap> imgLists) {
		super(context);
		mContext = context;
		this.imgLists = imgLists;
		initViewBitmap();
	}

	public ViewFlow(Context context, ImageView[] imgs) {
		super(context);
		mContext = context;
		initViewImageView(imgs);
	}

	public void initView() {
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mInflater.inflate(R.layout.viewflow, this, true);
		mScrollLayout = (ScrollLayout) findViewById(R.id.scrollview);

		for (int i = 0; i < imageIds.length; i++) {
			ImageView img = new ImageView(mContext);
			img.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));
			img.setScaleType(ScaleType.FIT_XY);
			img.setImageResource(imageIds[i]);
//			XimengImageLoaderManager.getImageLoaderInstance().displayImage(
//					Utils.getDrawableUri(imageIds[i]) ,img,
//					new XimengImageLoaderManager().getMengOption());
			mScrollLayout.addView(img);
		}
		
		for (int i = 0; i < imageIds.length; i++) {
			ImageView img = new ImageView(mContext);
			img.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));
			img.setScaleType(ScaleType.FIT_XY);
			img.setImageResource(imageIds[i]);
//			XimengImageLoaderManager.getImageLoaderInstance().displayImage(
//					Utils.getDrawableUri(imageIds[i]) ,img,
//					new XimengImageLoaderManager().getMengOption());
			mScrollLayout.addView(img);
		}

		mScrollLayout.setOnTouchListener(this);

		/**
		 * 监听滑动到第几页
		 */
		mScrollLayout.setOnScreenChangeListener(new OnScreenChangeListener() {

			@Override
			public void onScreenChange(int currentIndex) {
				currentItem = currentIndex;
			}
		});
	}

	public void initViewBitmap() {
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mInflater.inflate(R.layout.viewflow, this, true);
		mScrollLayout = (ScrollLayout) findViewById(R.id.scrollview);
		if (imgLists != null) {
			for (Iterator<Bitmap> iterator = imgLists.iterator(); iterator
					.hasNext();) {
				Bitmap bmp = (Bitmap) iterator.next();
				ImageView img = new ImageView(mContext);
				img.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
						LayoutParams.MATCH_PARENT));
				img.setImageBitmap(bmp);
				img.setScaleType(ScaleType.FIT_XY);
				mScrollLayout.addView(img);

			}
		}
		mScrollLayout.setOnTouchListener(this);

		/**
		 * 监听滑动到第几页
		 */
		mScrollLayout.setOnScreenChangeListener(new OnScreenChangeListener() {

			@Override
			public void onScreenChange(int currentIndex) {
				currentItem = currentIndex;
			}
		});
	}

	public void initViewImageView(ImageView[] imgs) {
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mInflater.inflate(R.layout.viewflow, this, true);
		mScrollLayout = (ScrollLayout) findViewById(R.id.scrollview);
		if (imgs != null) {
			for (int i = 0; i < imgs.length; i++) {
				ImageView img = imgs[i];
				img.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
						LayoutParams.MATCH_PARENT));
				img.setScaleType(ScaleType.FIT_XY);
				mScrollLayout.addView(img);

			}
		}
		mScrollLayout.setOnTouchListener(this);

		/**
		 * 监听滑动到第几页
		 */
		mScrollLayout.setOnScreenChangeListener(new OnScreenChangeListener() {

			@Override
			public void onScreenChange(int currentIndex) {
				currentItem = currentIndex;
			}
		});
	}

	public void startAutoPlay() {
		isAutoPlay = true;
		mScrollLayout.postDelayed(this, duration);
	}

	public void stopAutoPlay() {
		isAutoPlay = false;
		mScrollLayout.removeCallbacks(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: // 按下时停止图片轮播
			mScrollLayout.removeCallbacks(this);
			break;
		case MotionEvent.ACTION_CANCEL:

			break;
		case MotionEvent.ACTION_UP: // 抬起时图片继续轮播
			mScrollLayout.postDelayed(this, duration);
			break;
		default:
			break;
		}
		return false;
	}

	@Override
	public void run() {
		if (isAutoPlay) {
			if (currentItem == 2*imageIds.length - 1)
				isBack = true;
			else if (currentItem == 0)
				isBack = false;
			if (isBack) {
				currentItem--;
			} else {
				currentItem++;
			}

			mScrollLayout.snapToScreen(currentItem);
			mScrollLayout.postDelayed(this, duration);
		}

		
		
	}

	public int getCurrentItem(){
		return currentItem;
	}
	
	public void setViewFlowListener(ViewFlowOnClickListener listener) {
		if (mScrollLayout != null)
			mScrollLayout.setViewFlowListener(listener);
	}
}
