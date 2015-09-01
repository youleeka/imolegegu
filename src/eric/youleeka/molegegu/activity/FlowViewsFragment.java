package eric.youleeka.molegegu.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import eric.youleeka.molegegu.R;
import eric.youleeka.molegegu.widget.ViewFlow;

public class FlowViewsFragment extends Fragment implements OnClickListener{

	private ImageButton menu;
	private LinearLayout mLayout;
	private ViewFlow mViewFlow;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.story, container, false);
		menu = (ImageButton) view.findViewById(R.id.btn_header_left);
		menu.setOnClickListener(this);
		
		TextView headText = (TextView) view.findViewById(R.id.title_header_mid);
		headText.setText(R.string.text_menu_tu);
		
		mLayout = (LinearLayout) view.findViewById(R.id.lin_autoplay);
		int[] imgIds = { R.drawable.girl4, R.drawable.girl3, R.drawable.girl2,
				R.drawable.girl1 };

		mViewFlow = new ViewFlow(this.getActivity(), imgIds);
		mLayout.addView(mViewFlow);
		mViewFlow.startAutoPlay();
		// mViewFlow.setViewFlowListener(new ViewFlowOnClickListener() {
		//
		// @Override
		// public void onItemClick(int position) {
		// Toast.makeText(SmileActivity.this, R.string.app_name, 2000)
		// .show();
		// }
		// });
		return view;
	}

	@Override
	public void onDestroyView() {
		mViewFlow.stopAutoPlay();
		super.onDestroyView();
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
	
}
