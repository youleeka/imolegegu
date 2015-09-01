package eric.youleeka.molegegu.mode;

public class Content {

	private int id ;
	private String title = "";
	private String contentInfo = "";
	private String fromName = "";
	private String date = "";
	private String imagePath = "";
	private String des = "";
	private String url = "";
	
	public Content() {
		super();
	}
	public Content(String contentInfo) {
		super();
		this.contentInfo = contentInfo;

	}
	
	
	public Content(String title, String des, String url) {
		super();
		this.title = title;
		this.des = des;
		this.url = url;
	}
	public Content(int id, String title, String contentInfo, String fromName,
			String date, String imagePath, String des) {
		super();
		this.id = id;
		this.title = title;
		this.contentInfo = contentInfo;
		this.fromName = fromName;
		this.date = date;
		this.imagePath = imagePath;
		this.des = des;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the contentInfo
	 */
	public String getContentInfo() {
		return contentInfo;
	}
	/**
	 * @param contentInfo the contentInfo to set
	 */
	public void setContentInfo(String contentInfo) {
		this.contentInfo = contentInfo;
	}
	/**
	 * @return the fromName
	 */
	public String getFromName() {
		return fromName;
	}
	/**
	 * @param fromName the fromName to set
	 */
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}
	/**
	 * @param imagePath the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	/**
	 * @return the des
	 */
	public String getDes() {
		return des;
	}
	/**
	 * @param des the des to set
	 */
	public void setDes(String des) {
		this.des = des;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
