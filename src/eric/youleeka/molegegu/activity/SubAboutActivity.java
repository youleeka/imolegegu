package eric.youleeka.molegegu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import eric.youleeka.molegegu.R;

public class SubAboutActivity extends Activity implements OnClickListener{
	private ImageButton btn_header_left;
	private ImageButton btn_header_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_molegegu_about);
		
		btn_header_left = (ImageButton) findViewById(R.id.btn_header_left);
		btn_header_left.setVisibility(View.GONE);
		btn_header_back = (ImageButton) findViewById(R.id.btn_header_back);
		btn_header_back.setVisibility(View.VISIBLE);
		btn_header_back.setOnClickListener(this);
		
		TextView headText = (TextView) findViewById(R.id.title_header_mid);
		headText.setText("关于");
		
		
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_header_back:
			finish();
			break;

		default:
			break;
		}
		
	}

}
