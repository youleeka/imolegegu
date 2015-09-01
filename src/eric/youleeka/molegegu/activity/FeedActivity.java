package eric.youleeka.molegegu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import eric.youleeka.molegegu.R;

public class FeedActivity extends Activity implements
		OnClickListener {
	private ImageButton btn_header_left;
	private ImageButton btn_header_back;
	private EditText edit_feedback = null;
	private Button btn_feedback = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_molegegu_feedback);

		btn_header_left = (ImageButton) findViewById(R.id.btn_header_left);
		btn_header_left.setVisibility(View.GONE);
		btn_header_back = (ImageButton) findViewById(R.id.btn_header_back);
		btn_header_back.setVisibility(View.VISIBLE);
		btn_header_back.setOnClickListener(this);

		TextView headText = (TextView) findViewById(R.id.title_header_mid);
		headText.setText("意见");

		edit_feedback = (EditText) findViewById(R.id.edit_feedback);
		btn_feedback = (Button) findViewById(R.id.btn_feedback);
		btn_feedback.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_header_back:
			finish();
			break;

		case R.id.btn_feedback:
			if (edit_feedback.getText().toString().equals("")) {
				Toast.makeText(this.getBaseContext(),
						"亲，随便写点吧", Toast.LENGTH_SHORT)
						.show();
			} else {
				Toast.makeText(this.getBaseContext(),
						"感谢参与", Toast.LENGTH_SHORT).show();
				finish();
			}
			break;

		default:
			break;
		}

	}

}
