/*
 * Copyright (C) 2012 The Founder Mobile Media Technology Android EPaper Project
 * 
 * Founder Mobile Media PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package eric.youleeka.molegegu.net;

/**
 * Define the handler message types
 * 
 * @author li.wy
 * @createDate 2011-12-5
 * @version v0.0.1
 */
public class MessageType {
	/**
	 * Sometimes do not required handle the message
	 */
	public final static int MSG_NOT_REQUIRED_HANDLE = -1;

	/**
	 * The default message type that the exception does not explicit.
	 */
	public final static int MSG_DEFAULT = 0;

	/**
	 * The url is incorrect
	 */
	public final static int MSG_MALFORMED_URL = 1;

	/**
	 * The network given type(currently mobile or WIFI) is unavailable
	 */
	public final static int MSG_NETWORK_UNAVAILABLE = 2;

	/**
	 * Attempt to connect failed
	 */
	public final static int MSG_NETWORK_CONNECTED_FAIL = 3;

	/**
	 * The host name can not be resolved
	 */
	public final static int MSG_NETWORK_UNKNOWN_HOST = 4;

	/**
	 * The http connection time out
	 */
	public final static int MSG_NETWORK_TIME_OUT = 5;

	/**
	 * The network switcher of settings option been closed
	 */
	public final static int MSG_PREFS_NETWORK_SWITCHER_CLOSED = 6;

	/**
	 * The settings option is wifi only, but the system wifi is
	 * unavailable(closed)
	 */
	public final static int MSG_PREFS_WIFI_ONLY_UNAVAILABLE = 7;

	/**
	 * Connection network fail, but unknown reason.
	 */
	public final static int MSG_HTTP_CONN_FAIL = 999;

	/**
	 * An io exception occurs while connection net work
	 */
	public final static int MSG_HTTP_CONN_IO_EXCEPTION = 1000;

	/**
	 * The http client exception, http response code 4xx
	 */
	public static final int MSG_HTTP_CLIENT_EXCEPTION = 1001;

	/**
	 * The http server exception, http response code 5xx
	 */
	public static final int MSG_HTTP_SERVER_EXCEPTION = 1002;

	/**
	 * The http redirection, http response code 3xx
	 */
	public static final int MSG_HTTP_REDIRECTION_EXCEPTION = 1003;

	/**
	 * The file already exist
	 */
	public static final int MSG_FILE_EXIST = 1004;

	/**
	 * The value of the response header field content-length is -1
	 */
	public static final int MSG_NO_RESPONSE_DATA = 1005;

	/**
	 * Data exception while download file
	 */
	public static final int MSG_FILE_DATA_EXCEPTION = 1006;

	/**
	 * The file not exist
	 */
	public static final int MSG_FILE_NOT_EXIST = 1007;

	/**
	 * The download task is duplicated
	 */
	public static final int MSG_DUPLICATE_DOWNLOAD_TASK = 1008;

	/**
	 * The exception while file read or write
	 */
	public static final int MSG_FILE_IO_EXCEPTION = 1009;

	/**
	 * The xeb file unavailable
	 */
	public static final int MSG_XEB_FILE_UNAVAILABLE = 1010;

	/**
	 * The file not found
	 */
	public static final int MSG_FILE_NOT_FOUND = 1011;

	/**
	 * The query result that not task
	 */
	public static final int MSG_QUERY_NO_TASK = 1012;

	/**
	 * The query result that has new task
	 */
	public static final int MSG_QUERY_HAS_TASK = 1013;

	/**
	 * The device low memory
	 */
	public static final int MSG_SDCARD_LOW_MEMORY_WARN = 1014;

	/**
	 * The external storage unavailable
	 */
	public static final int MSG_SDCARD_UNAVAILABLE = 1015;

	/**
	 * Parse xml exception
	 */
	public static final int MSG_PARSE_XML_EXCEPTION = 1016;

	/**
	 * post data null
	 */
	public static final int MSG_POST_DATA_NULL = 10117;

	/**
	 * load data fail
	 */
	public static final int MSG_LOAD_FAIL = 1018;

	/**
	 * query download task fail
	 */
	public static final int MSG_QUERY_FAIL = 1019;

	/**
	 * update fail
	 */
	public static final int MSG_UPDATE_FAIL = 1020;

	/**
	 * download paper fail
	 */
	public static final int MSG_DOWNLOAD_PAPER_FAIL = 1021;
}
