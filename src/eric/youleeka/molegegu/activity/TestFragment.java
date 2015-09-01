package eric.youleeka.molegegu.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import eric.youleeka.molegegu.R;
import eric.youleeka.molegegu.adapter.XingZuoListAdapter;

public class TestFragment extends Fragment implements OnClickListener{

	private ImageButton menu;
	private ImageView button_ok;
	private TextView answer;
	private List<String> list = null;
	private ListView listview = null;
	private XingZuoListAdapter xingZuoListAdapter = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.test, container, false);
		menu = (ImageButton) view.findViewById(R.id.btn_header_left);
		menu.setOnClickListener(this);
		
		TextView headText = (TextView) view.findViewById(R.id.title_header_mid);
		headText.setText(R.string.text_menu_test);
		
		button_ok = (ImageView) view.findViewById(R.id.button_ok);
		button_ok.setOnClickListener(this);
		answer = (TextView) view.findViewById(R.id.answer);
		list = new ArrayList<String>();
		list.add("1.你喜欢吃生鱼");
		list.add("2.你喜欢吃煮鱼");
		list.add("3.你喜欢吃烤鱼");
		list.add("4.你喜欢吃死鱼");
		listview = (ListView) view.findViewById(R.id.listview_xingzuo);
		xingZuoListAdapter = new XingZuoListAdapter(this.getActivity());
		listview.setAdapter(xingZuoListAdapter);
		xingZuoListAdapter.setList(list);
		xingZuoListAdapter.notifyDataSetChanged();
		listview.setOnItemClickListener(xingZuoListAdapter);
		listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_header_left:
			MolegeguMainActivity attachExample = (MolegeguMainActivity) getActivity();
			attachExample.showMenu();
			break;
		case R.id.button_ok:
			int key = listview.getCheckedItemPosition();
			switch (key) {
			case 0:
				answer.setText(getInfo(list.get(0)));
				break;
			case 1:
				answer.setText(getInfo(list.get(1)));
				break;
			case 2:
				answer.setText(getInfo(list.get(2)));
				break;
			case 3:
				answer.setText(getInfo(list.get(3)));
				break;

			default:
				answer.setText("亲。怎么不选，没有你想吃的鱼？");
				break;
			}
			break;
		default:
			break;
		}
	}
	
	private String getInfo(String key){
		String info = "";
		info = "让你惊呆的答案："+key.substring(1);
		return info;
	}
	
}
