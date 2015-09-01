package eric.youleeka.molegegu.adapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import eric.youleeka.molegegu.R;
import eric.youleeka.molegegu.activity.FeedActivity;
import eric.youleeka.molegegu.activity.FunEveryDayActivity;
import eric.youleeka.molegegu.activity.SubAboutActivity;

public class SettingListItemAdapter extends BaseAdapter implements
		OnItemClickListener {

	private static final int[] TITLE_IDS = { R.string.text_about_about,
			R.string.text_about_net, R.string.text_about_feedback ,R.string.text_about_update};

	private static final int[] ITEM_ICON_IDS = {
			R.drawable.setting_top_selector,
			R.drawable.setting_middle_selector,
			R.drawable.setting_middle_selector,
			R.drawable.setting_down_selector };


	private final LayoutInflater mInflater;

	private final Context mContext;

	public SettingListItemAdapter(Context context) {
		mContext = context;
		mInflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		return TITLE_IDS.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item_more_settings,
					parent, false);
		}


		LinearLayout item = (LinearLayout) convertView
				.findViewById(R.id.setting_list_item);
		item.setBackgroundResource(ITEM_ICON_IDS[position]);

		TextView txtTitle = (TextView) convertView
				.findViewById(R.id.txt_setting_title);
		txtTitle.setText(TITLE_IDS[position]);


		return convertView;
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (TITLE_IDS[position]) {
		case R.string.text_about_about:
			Intent aboutIntent = new Intent(mContext, SubAboutActivity.class);
			mContext.startActivity(aboutIntent);
			break;
		case R.string.text_about_net:
			Intent intent = new Intent(mContext,FunEveryDayActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("url", "http://www.molegegu.com");
			intent.putExtras(bundle);
			mContext.startActivity(intent);
			break;
		case R.string.text_about_feedback:
			Intent feedBackIntent = new Intent(mContext, FeedActivity.class);
			mContext.startActivity(feedBackIntent);
			break;
		case R.string.text_about_update:
			new Thread() {
				@Override
				public void run() {
					String newversion = startUrlCheck();
					String version;
					try {
						version = getVersionName();
						if (version.equals(newversion)) {
							handler.sendEmptyMessage(0);
						} else {
							handler.sendEmptyMessage(1);
						}
					} catch (NameNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
			break;
		default:
			break;
		}
	}
	
	class CheckVersion extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... params) {
			// delay 10sec
			try {
				Thread.sleep(10000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			String newversion = startUrlCheck();
			String version;
			try {
				version = getVersionName();
				if (version.equals("yes")) {
					handler.sendEmptyMessage(0);
				} else {
					handler.sendEmptyMessage(1);
				}
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				String url = "http://www.molegegu.com/molegegu_beta.apk";
				Bundle data = new Bundle();
				data.putString("url", url);

				Intent intent = new Intent();
				intent.setAction("android.intent.action.VIEW");
				Uri content_url = Uri.parse(url);
				intent.setData(content_url);
				mContext.startActivity(intent);
			} else {
				Toast.makeText(mContext,
						R.string.text_menu_noupdate, 1000).show();
			}
			super.handleMessage(msg);
		}
	};

	private String startUrlCheck() {
		HttpClient client = new DefaultHttpClient();
		StringBuilder builder = new StringBuilder();
		String version = "";
		HttpGet myget = new HttpGet("http://www.molegegu.com/version.php");
		try {
			HttpResponse response = client.execute(myget);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			for (String s = reader.readLine(); s != null; s = reader.readLine()) {
				builder.append(s);
			}
			JSONObject jsonObject = new JSONObject(builder.toString());
			version = jsonObject.getString("version");
			return version;
		} catch (Exception e) {
			Log.v("url response", "false");
			e.printStackTrace();
		}
		return version;
	}

	private String getVersionName() throws NameNotFoundException {
		// 获取packagemanager的实例
		PackageManager packageManager = mContext.getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo = packageManager.getPackageInfo(mContext.getPackageName(),
				0);
		String version = packInfo.versionName;
		return version;
	}
}