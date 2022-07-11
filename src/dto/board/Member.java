package dto.board;

public class Member {
	private String userid;
	private String userpw;
	private String usernick;
	private int userno;

	@Override
	public String toString() {
		return "Member [userid=" + userid + ", userpw=" + userpw + ", usernick=" + usernick + ", userno=" + userno
				+ "]";
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getUsernick() {
		return usernick;
	}

	public void setUsernick(String usernick) {
		this.usernick = usernick;
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

}
