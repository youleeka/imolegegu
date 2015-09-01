package eric.youleeka.molegegu.activity;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new Thread(){
			@Override
			public void run(){
			startUrlCheck("eric", "youleeka");
			handler.sendEmptyMessage(0);
			}
			}.start();
	}


	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
		}
	};

	private void startUrlCheck(String username, String password) {
		HttpClient client = new DefaultHttpClient();
		StringBuilder builder = new StringBuilder();

		HttpGet myget = new HttpGet("http://www.molegegu.com/molegegu.php");
		try {
			HttpResponse response = client.execute(myget);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			for (String s = reader.readLine(); s != null; s = reader.readLine()) {
				builder.append(s);
			}
			JSONObject jsonObject = new JSONObject(builder.toString());
			String re_username = jsonObject.getString("username");
			String re_password = jsonObject.getString("password");
			int re_user_id = jsonObject.getInt("user_id");
			setTitle("�û�id_" + re_user_id);
			Log.v("url response", "true=" + re_username);
			Log.v("url response", "true=" + re_password);
		} catch (Exception e) {
			Log.v("url response", "false");
			e.printStackTrace();
		}
	}
}
