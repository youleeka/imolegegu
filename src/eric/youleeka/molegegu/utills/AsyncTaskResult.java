package eric.youleeka.molegegu.utills;

import android.graphics.Bitmap;

/**
 * The {@link AsyncTaskResult} is used to encapsulate the AsyncTask request result
 * @author deng.jb
 * @createDate 2011-4-29
 * @version v0.3.3
 */
public class AsyncTaskResult {

    private Exception exception;
    private boolean success;
    private Bitmap mBitmap=null;
    private String info=null;
    private int mType = -1;
    private long id = -1;

	/**
     * The constructor
     * @param success
     */
    public AsyncTaskResult(boolean success) {
        this.success = success;
    }

    /**
     * The constructor
     * @param success
     * @param exception
     */
    public AsyncTaskResult(boolean success, Exception exception) {
        this.success = success;
        this.exception = exception;
    }    

    /**
     * Check the async task is success
     * @return
     */
    public boolean isSuccess() {
        return success;
    }


    /**
     * Set the exception
     * @param exception
     */
    public void setException(Exception exception) {
        this.exception = exception;
    }

    /**
     * Get the result
     * @return
     */

    /**
     * Get the exception
     * @return
     */
    public Exception getException() {
        return exception;
    }
    
    public Bitmap getBitmap() {
		return mBitmap;
	}

	public void setBitmap(Bitmap mBitmap) {
		this.mBitmap = mBitmap;
	}
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }
    
    public void setTaskId(long id){
    	this.id = id;
    }
    
    public long getTaskId(){
    	return this.id;
    }
}