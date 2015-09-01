package eric.youleeka.molegegu.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import eric.youleeka.molegegu.R;

public class StorysFragment extends Fragment implements OnClickListener{

	private ImageButton menu;
	private LinearLayout mLayout;
	private WebView mWebView;
	private ProgressBar mProgressBar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fun_everyday, container, false);
		menu = (ImageButton) view.findViewById(R.id.btn_header_left);
		menu.setOnClickListener(this);
		
		TextView headText = (TextView) view.findViewById(R.id.title_header_mid);
		headText.setText(R.string.text_menu_story);
		
		
		String url = "http://www.molegegu.com/im/webview.htm";
		
		mWebView = (WebView) view.findViewById(R.id.sub_xeb_webview);
		mWebView.loadUrl(url);
		mWebView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
					view.loadUrl(url);
				return true;
			}
			
		});
		
		mProgressBar = (ProgressBar) view.findViewById(R.id.loadProgress);
		mWebView = (WebView) view.findViewById(R.id.sub_xeb_webview);
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
	
}
