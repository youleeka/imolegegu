package eric.youleeka.molegegu.utills;


/**
 * The {@link IAsyncTaskHandler} define the method to handle the network request async task result
 * @author deng.jb
 * @createDate 2011-4-29
 * @version v0.3.3
 */
public interface IAsyncTaskHandler {

	/**
	 * Handle the async task result
	 * @param result
	 */
	void handleResult(AsyncTaskResult result);	
}