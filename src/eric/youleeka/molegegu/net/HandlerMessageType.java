package eric.youleeka.molegegu.net;


/**
 * Define the handler message types
 * @author li.wy
 * @createDate 2011-12-5
 * @version v0.0.1
 */
public class HandlerMessageType extends MessageType {
		
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
	 * query download task  fail
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
