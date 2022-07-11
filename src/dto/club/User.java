package dto.club;

public class User {

	private int userno;
	private String username;
	private String userpassword;
	private String userid;

	@Override
	public String toString() {
		return "User [userno=" + userno + ", username=" + username + ", userpassword=" + userpassword + ", userid="
				+ userid + "]";
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}