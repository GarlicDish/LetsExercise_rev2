package dto.club;

public class UserProfile {
	private int key;
	private int usernumber;
	private String uploadname;
	private String originname;

	@Override
	public String toString() {
		return "UserProfile [key=" + key + ", usernumber=" + usernumber + ", uploadname=" + uploadname + ", originname="
				+ originname + "]";
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getUsernumber() {
		return usernumber;
	}

	public void setUsernumber(int usernumber) {
		this.usernumber = usernumber;
	}

	public String getUploadname() {
		return uploadname;
	}

	public void setUploadname(String uploadname) {
		this.uploadname = uploadname;
	}

	public String getOriginname() {
		return originname;
	}

	public void setOriginname(String originname) {
		this.originname = originname;
	}

}
