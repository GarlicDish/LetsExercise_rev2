package dto.member;

public class FindpassDto {

	private String userid;
	private String email;

	@Override
	public String toString() {
		return "FindpassDto [userid=" + userid + ", email=" + email + "]";
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
