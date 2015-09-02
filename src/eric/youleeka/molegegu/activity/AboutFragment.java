package eric.youleeka.molegegu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import eric.youleeka.molegegu.R;
import eric.youleeka.molegegu.adapter.SettingListItemAdapter;

public class AboutFragment extends Fragment implements OnClickListener{

	private ImageButton menu;//menu button
	private ListView listView = null;
	private TextView text_about_about_molegegu = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.about, container, false);
		menu = (ImageButton) view.findViewById(R.id.btn_header_left);
		menu.setOnClickListener(this);
		TextView headText = (TextView) view.findViewById(R.id.title_header_mid);
		headText.setText(R.string.text_menu_about);
		
		
		listView = (ListView) view.findViewById(R.id.setting_list);
		SettingListItemAdapter adapter=new SettingListItemAdapter(getActivity());
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(adapter);
		
		text_about_about_molegegu = (TextView) view.findViewById(R.id.text_about_about_molegegu);
		text_about_about_molegegu.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_header_left:
			MolegeguMainActivity attachExample = (MolegeguMainActivity) getActivity();
			attachExample.showMenu();
			break;
		case R.id.text_about_about_molegegu:
			Intent intent = new Intent(getActivity(),FunEveryDayActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("url", "http://www.molegegu.com");
			intent.putExtras(bundle);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}
	
}
