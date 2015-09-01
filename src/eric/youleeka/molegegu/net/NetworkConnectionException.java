package eric.youleeka.molegegu.net;


/**
 * The Network Connection Exception Wrapper class
 * @author deng.jb
 * @createDate 2010-4-28
 * @version v0.3.4
 */
public class NetworkConnectionException extends Exception {
	private static final long serialVersionUID = -8058742813492735956L;
	
	private int msgType;
	/**
	 * The constructor
	 */
	public NetworkConnectionException() {
		super();
	}
	/**
	 * The constructor
	 * 
	 * @param error
	 */
	public NetworkConnectionException(int msgType) {
		this.msgType = msgType;
	}

	/**
	 * The constructor
	 * 
	 * @param message
	 */
	public NetworkConnectionException(String message) {
		super(message);
	}

	/**
	 * The constructor
	 * 
	 * @param error
	 * @param message
	 */
	public NetworkConnectionException(int msgType, String message) {
		super(message);
		this.msgType = msgType;
	}

	public int getMsgType() {
		return msgType;
	}
	
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", msgType = " + msgType;
	}
}