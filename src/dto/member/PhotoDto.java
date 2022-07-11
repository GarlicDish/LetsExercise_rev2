package dto.member;

public class PhotoDto {
	private int usernumber;
	private int fileno;
	private String originname;
	private String storedname;

	@Override
	public String toString() {
		return "PhotoDto [usernumber=" + usernumber + ", fileno=" + fileno + ", originname=" + originname
				+ ", storedname=" + storedname + "]";
	}

	public int getUsernumber() {
		return usernumber;
	}

	public void setUsernumber(int usernumber) {
		this.usernumber = usernumber;
	}

	public int getFileno() {
		return fileno;
	}

	public void setFileno(int fileno) {
		this.fileno = fileno;
	}

	public String getOriginname() {
		return originname;
	}

	public void setOriginname(String originname) {
		this.originname = originname;
	}

	public String getStoredname() {
		return storedname;
	}

	public void setStoredname(String storedname) {
		this.storedname = storedname;
	}

}
