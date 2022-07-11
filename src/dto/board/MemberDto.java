package dto.board;

public class MemberDto {

	private int usernumber;
	private String userid;
	private String userpw;
	private String nickname;
	private int areanumber;
	private String email;
	private String repwdate;
	private String joindate;
	// 0 남성, 1 여성
	private int gender;
	// 0 해당없음, 1 차단됨
	private int blackListed;
	// 0 일반회원, 1 소셜회원
	private int socialMem;
	// 0 활성화, 1 비활성화
	private int deActivated;

	// 프로필사진 작업
	private String StoredName;

	@Override
	public String toString() {
		return "MemberDto [usernumber=" + usernumber + ", userid=" + userid + ", userpw=" + userpw + ", nickname="
				+ nickname + ", areanumber=" + areanumber + ", email=" + email + ", repwdate=" + repwdate
				+ ", joindate=" + joindate + ", gender=" + gender + ", blackListed=" + blackListed + ", socialMem="
				+ socialMem + ", deActivated=" + deActivated + ", StoredName=" + StoredName + "]";
	}

	public int getUsernumber() {
		return usernumber;
	}

	public void setUsernumber(int usernumber) {
		this.usernumber = usernumber;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getAreanumber() {
		return areanumber;
	}

	public void setAreanumber(int areanumber) {
		this.areanumber = areanumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRepwdate() {
		return repwdate;
	}

	public void setRepwdate(String repwdate) {
		this.repwdate = repwdate;
	}

	public String getJoindate() {
		return joindate;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getBlackListed() {
		return blackListed;
	}

	public void setBlackListed(int blackListed) {
		this.blackListed = blackListed;
	}

	public int getSocialMem() {
		return socialMem;
	}

	public void setSocialMem(int socialMem) {
		this.socialMem = socialMem;
	}

	public int getDeActivated() {
		return deActivated;
	}

	public void setDeActivated(int deActivated) {
		this.deActivated = deActivated;
	}

	public String getStoredName() {
		return StoredName;
	}

	public void setStoredName(String storedName) {
		StoredName = storedName;
	}

}