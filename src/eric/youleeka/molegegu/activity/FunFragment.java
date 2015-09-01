package eric.youleeka.molegegu.activity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import eric.youleeka.molegegu.R;
import eric.youleeka.molegegu.adapter.SmileAdapter;
import eric.youleeka.molegegu.mode.Content;
import eric.youleeka.molegegu.widget.PullToRefreshListView;

public class FunFragment extends Fragment implements
		OnClickListener {

	private ImageButton menu;
	private PullToRefreshListView listView = null;
	private SmileAdapter adapter;
	private List<Content> list;

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.list_stories,
				container, false);
		menu = (ImageButton) view
				.findViewById(R.id.btn_header_left);
		menu.setOnClickListener(this);

		TextView headText = (TextView) view
				.findViewById(R.id.title_header_mid);
		headText.setText(R.string.text_menu_fun);

		list = new ArrayList<Content>();
		listView = (PullToRefreshListView) view
				.findViewById(R.id.storis_list);

		adapter = new SmileAdapter(this.getActivity(), list);
		listView.setAdapter(adapter);
		listView.getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0,
					View arg1, int arg2, long arg3) {
				Intent intent = new Intent(getActivity(),
						FunEveryDayActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("url", list.get(arg2)
						.getUrl());
				intent.putExtras(bundle);
				startActivity(intent);

			}
		});

		new LoadDateThread(getActivity()).start();
		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_header_left:
			MolegeguMainActivity attachExample = (MolegeguMainActivity) getActivity();
			attachExample.showMenu();
			break;

		default:
			break;
		}
	}
	
	
	private List<Content> getFunList() {
		HttpClient client = new DefaultHttpClient();
		StringBuilder builder = new StringBuilder();
		HttpGet myget = new HttpGet("http://www.molegegu.com/im/fun/funlist.php");
		try {
			HttpResponse response = client.execute(myget);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			for (String s = reader.readLine(); s != null; s = reader.readLine()) {
				builder.append(s);
			}
			JSONObject jsonObject = new JSONObject(builder.toString());
			list.add(new Content(jsonObject.getString("fun1_title"),jsonObject.getString("fun1_info"),jsonObject.getString("fun1_url")));
			list.add(new Content(jsonObject.getString("fun2_title"),jsonObject.getString("fun2_info"),jsonObject.getString("fun2_url")));
			list.add(new Content(jsonObject.getString("fun3_title"),jsonObject.getString("fun3_info"),jsonObject.getString("fun3_url")));
			list.add(new Content(jsonObject.getString("fun4_title"),jsonObject.getString("fun4_info"),jsonObject.getString("fun4_url")));
			list.add(new Content(jsonObject.getString("fun5_title"),jsonObject.getString("fun5_info"),jsonObject.getString("fun5_url")));
			return list;
		} catch (Exception e) {
			Log.v("url response", "false");
			e.printStackTrace();
		}
		return list;
	}
	
	private Handler m_xebHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				adapter.notifyDataSetChanged();
			}
		}
	};
	class LoadDateThread extends Thread {

		private ProgressDialog pDialog;

		private Context mContext;

		public LoadDateThread(Context context) {
			this.mContext = context;
			pDialog = new ProgressDialog(context);
			pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					// finish();
				}
			});
			pDialog.setCancelable(true);
			pDialog.setMessage("loading……");
			pDialog.show();
		}

		public void run() {

			list = getFunList();
			Message msg = new Message();
			msg.obj = list;
			msg.what = 1;
			m_xebHandler.sendMessage(msg);

			if (pDialog != null)
				pDialog.dismiss();

		}
	}

}
