package eric.youleeka.molegegu.activity;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import eric.youleeka.molegegu.R;
import eric.youleeka.molegegu.SlidingMenu;

public class MolegeguMainActivity extends FragmentActivity implements OnClickListener {

	private SlidingMenu menu;
	private LinearLayout layout1;
	private LinearLayout layout2;
	private LinearLayout layout3;
	private LinearLayout layout4;
	private LinearLayout layout5;
	private ImageView menu_info1;
	private ImageView menu_info2;
	private ImageView menu_info3;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_frame);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, new HahaFragment()).commit();
		// configure the SlidingMenu
		menu = new SlidingMenu(this);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		menu.setMenu(R.layout.sidemenu);
		// menu.showContent();

		layout1 = (LinearLayout) findViewById(R.id.layout1);
		layout2 = (LinearLayout) findViewById(R.id.layout2);
		layout3 = (LinearLayout) findViewById(R.id.layout3);
		layout4 = (LinearLayout) findViewById(R.id.layout4);
		layout5 = (LinearLayout) findViewById(R.id.layout5);
		menu_info1 = (ImageView) findViewById(R.id.menu_info1);
		menu_info2 = (ImageView) findViewById(R.id.menu_info2);
		menu_info3 = (ImageView) findViewById(R.id.menu_info3);
		layout1.setOnClickListener(this);
		layout2.setOnClickListener(this);
		layout3.setOnClickListener(this);
		layout4.setOnClickListener(this);
		layout5.setOnClickListener(this);
		menu_info1.setOnClickListener(this);
		menu_info2.setOnClickListener(this);
		menu_info3.setOnClickListener(this);
		cleanAndSetChecked(layout1, true);

	}

	public void setMenuEnable(boolean enable) {
		menu.setSlidingEnabled(enable);
		menu.showContent();
	}

	public void showMenu() {
		menu.setSlidingEnabled(true);
		menu.showMenu();
	}

	@Override
	public void onBackPressed() {
		if (menu.isMenuShowing()) {
			menu.showContent();
		} else {
			super.onBackPressed();
		}
	}

	private long waitTime = 2000;
	private long touchTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN
				&& KeyEvent.KEYCODE_BACK == keyCode) {
			long currentTime = System.currentTimeMillis();
			if ((currentTime - touchTime) >= waitTime) {
				Toast.makeText(this.getApplicationContext(), "亲，再按一次会退出哦",
						Toast.LENGTH_LONG).show();
				touchTime = currentTime;
			} else {
				finish();
			}
			return true;
		} else if (event.getAction() == KeyEvent.ACTION_DOWN
				&& KeyEvent.KEYCODE_MENU == keyCode) {
			if (menu.isMenuShowing()) {
				menu.showContent();
			} else {
				showMenu();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout1:
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new HahaFragment()).commit();
			cleanAndSetChecked(layout1, true);
			setMenuEnable(true);
			break;
		case R.id.layout2:
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new StoryFragment()).commit();
			cleanAndSetChecked(layout2, true);
			setMenuEnable(true);
			break;
		case R.id.layout3:
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new FunFragment()).commit();
			cleanAndSetChecked(layout3, true);
			setMenuEnable(true);
			break;
		case R.id.layout4:
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new FlowViewsFragment()).commit();
			cleanAndSetChecked(layout4, true);
			setMenuEnable(true);
			break;
		case R.id.layout5:
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new AboutFragment()).commit();
			cleanAndSetChecked(layout5, true);
			setMenuEnable(true);
			break;
		case R.id.menu_info1:
			Toast.makeText(getApplicationContext(), R.string.text_menu_info1,
					1000).show();
			break;
		case R.id.menu_info2:
			Toast.makeText(getApplicationContext(), R.string.text_menu_info2,
					1000).show();
			break;
		case R.id.menu_info3:
			Toast.makeText(getApplicationContext(), R.string.text_menu_info3,
					1000).show();
			break;
		default:
			break;
		}

	}

	private void cleanAndSetChecked(View v, boolean checked) {
		setChecked(layout1, false);
		setChecked(layout2, false);
		setChecked(layout3, false);
		setChecked(layout4, false);
		setChecked(layout5, false);
		setChecked(v, checked);
	}

	private void setChecked(View v, boolean checked) {
		if (v == null) {
			return;
		}

		if (checked) {
			v.setBackgroundResource(R.color.sidemenu_color_pressed);
		} else {
			v.setBackgroundResource(R.color.gray_lnzf);
		}
	}

}
