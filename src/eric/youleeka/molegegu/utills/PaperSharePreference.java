package eric.youleeka.molegegu.utills;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Interface for accessing and modifying preference data 
 * @author li.wy
 * @createDate 2011-11-26
 * @version v0.0.1
 */
public class PaperSharePreference {
	private Context mContext;
	private final String fileName = "loadingbg";
	
	public PaperSharePreference(Context mContext) {
		this.mContext = mContext;
	}
	
	/**
	 * Set float value into SharePreference
	 * 
	 * @param fileName
	 * @param key
	 * @param value
	 */
	public void saveStringValueToSharePreferences(String key, String value) {
		SharedPreferences sharePre = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharePre.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	public String getStringValueByKey(String key) {
		SharedPreferences sharePre = mContext.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		return sharePre.getString(key, "");
	}
	
}
