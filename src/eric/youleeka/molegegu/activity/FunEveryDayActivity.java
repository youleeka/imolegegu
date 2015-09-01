package eric.youleeka.molegegu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import eric.youleeka.molegegu.R;

public class FunEveryDayActivity extends Activity implements OnClickListener{
	private WebView mWebView;
	private ImageButton btn_header_left;
	private ImageButton btn_header_back;
	private ProgressBar mProgressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fun_everyday);
		
		btn_header_left = (ImageButton) findViewById(R.id.btn_header_left);
		btn_header_left.setVisibility(View.GONE);
		btn_header_back = (ImageButton) findViewById(R.id.btn_header_back);
		btn_header_back.setVisibility(View.VISIBLE);
		btn_header_back.setOnClickListener(this);
		
		TextView headText = (TextView) findViewById(R.id.title_header_mid);
		headText.setText("正文");
		
		Intent intent = getIntent();
		String url = intent.getStringExtra("url");
		
		mWebView = (WebView) findViewById(R.id.sub_xeb_webview);
		mWebView.loadUrl(url);
		mWebView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
					view.loadUrl(url);
				return true;
			}
			
		});
		
		mProgressBar = (ProgressBar) findViewById(R.id.loadProgress);
		mWebView = (WebView) findViewById(R.id.sub_xeb_webview);
		mWebView.loadUrl(url);
		mWebView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

		});
		// set the webview
		WebSettings settings = mWebView.getSettings();
		settings.setSupportZoom(true);
		settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		settings.setDefaultTextEncodingName("GBK");
		settings.setTextSize(TextSize.NORMAL);
		settings.setJavaScriptEnabled(true);
		settings.setUseWideViewPort(true);
		mWebView.setInitialScale(80);
		mWebView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				if (newProgress > 0 && newProgress < 100) {
					mProgressBar.setVisibility(View.VISIBLE);
					mProgressBar.setProgress(newProgress);
				} else if (newProgress == 100) {
					mProgressBar.setVisibility(View.GONE);
				}
			}
		});
		mWebView.requestFocusFromTouch();
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
