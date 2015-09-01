package eric.youleeka.molegegu.net;

import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.content.Context;
import android.text.TextUtils;

/**
 * The network request control class, used to build the http connection
 * 
 * @author deng.jb
 * @createDate 2011-4-29
 * @version v0.3.3
 */
public class NetworkRequest {

	
	public static final String POSTDATA_ENCODING = "UTF-8";
	public static final String HTTP_METHOD_GET = "GET";
	public static final String HTTP_METHOD_POST = "POST";
    private int defaultTimeOut = 20*1000;

	private Context context;

	/**
	 * The constructor
	 * 
	 * @param context
	 */
	public NetworkRequest(Context context) {
		this.context = context;
	}
	
	public InputStream buildRequestInputStream(String url, String method, String data) 
			throws NetworkConnectionException {
		return buildHttpClientInputSream(url, method, data, null);
	}
	
	
	public InputStream buildHttpClientInputSream(String baseUrl, String method, String content, String range)
			throws NetworkConnectionException {
		InputStream is = null;
		HttpResponse resp = buildHttpClientResponse(baseUrl, method, content, range);
		
		if(resp == null) {
			throw new NetworkConnectionException(HandlerMessageType.MSG_NETWORK_CONNECTED_FAIL);
		}
		
		int respCode = resp.getStatusLine().getStatusCode();
		if(respCode / 100 == 2) {
			try {
				is = resp.getEntity().getContent();
			} catch (Exception e) {
				e.printStackTrace();
				throw new NetworkConnectionException(HandlerMessageType.MSG_NETWORK_CONNECTED_FAIL);
			}
			return is;
		} else if(respCode / 100 == 3) {
			throw new NetworkConnectionException(HandlerMessageType.MSG_HTTP_REDIRECTION_EXCEPTION);
		} else if(respCode / 100 == 4) {
			throw new NetworkConnectionException(HandlerMessageType.MSG_HTTP_CLIENT_EXCEPTION);
		} else if(respCode / 100 == 5) {
			throw new NetworkConnectionException(HandlerMessageType.MSG_HTTP_SERVER_EXCEPTION);
		} else {
			throw new NetworkConnectionException(HandlerMessageType.MSG_NETWORK_CONNECTED_FAIL);
		}
		
	}
	
	public HttpResponse buildHttpClientResponse(String baseUrl, String method, String content, String range) 
			throws NetworkConnectionException {
		
		String reqUrl = baseUrl;
		if(TextUtils.isEmpty(reqUrl)) {
			throw new NetworkConnectionException(HandlerMessageType.MSG_MALFORMED_URL);
		}
		
		HttpResponse resp = null;
		HttpRequestBase req = null;
		
		try {
			if(method.equals(HTTP_METHOD_GET)) {
				req = new HttpGet(reqUrl);
				resp = buildBaseHttpClient().execute(req);}
		} catch (UnknownHostException e) {
			e.printStackTrace();
			throw new NetworkConnectionException(HandlerMessageType.MSG_NETWORK_UNKNOWN_HOST);
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			throw new NetworkConnectionException(HandlerMessageType.MSG_NETWORK_TIME_OUT);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NetworkConnectionException(HandlerMessageType.MSG_NETWORK_CONNECTED_FAIL);
		}
		
		return resp;
	}
	
	private HttpClient buildBaseHttpClient() {
		HttpParams params = new BasicHttpParams();
		
		HttpConnectionParams.setConnectionTimeout(params, defaultTimeOut);
		HttpConnectionParams.setSoTimeout(params, defaultTimeOut);
		HttpConnectionParams.setSocketBufferSize(params, 10240);
		
		HttpClientParams.setRedirecting(params, true);
		
		HttpClient httpClient = new DefaultHttpClient(params);
		
		return httpClient;
	}

}