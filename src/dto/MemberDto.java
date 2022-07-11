package dto;

import java.util.Date;

public class MemberDto {

	private  int userNumber;
	private  String userID;
	private  String userPW;
	private  String nickname;
	private  int areaNumber;
	private  String email;
	private  String rePwDate;
	private  String joinDate;
	//0 남성, 1 여성
	private  int gender;
	//0 해당없음, 1 차단됨
	private  int blackListed;
	//0 일반회원, 1 소셜회원
	private  int socialMem;
	//0 활성화, 1 비활성화
	private  int deActivated;
	
	//프로필사진 작업
	private String StoredName;
	
	@Override
	public String toString() {
		return "MemberDto [userNumber=" + userNumber + ", userID=" + userID + ", userPW=" + userPW + ", nickname="
				+ nickname + ", areaNumber=" + areaNumber + ", email=" + email + ", rePwDate=" + rePwDate
				+ ", joinDate=" + joinDate + ", gender=" + gender + ", blackListed=" + blackListed + ", socialMem="
				+ socialMem + ", deActivated=" + deActivated + ", StoredName=" + StoredName  + "]";
	}
	public int getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPW() {
		return userPW;
	}
	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getAreaNumber() {
		return areaNumber;
	}
	public void setAreaNumber(int areaNumber) {
		this.areaNumber = areaNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public void setStoredName(String StoredName) {
		this.StoredName = StoredName;
	}
	public String getRePwDate() {
		return rePwDate;
	}
	public void setRePwDate(String rePwDate) {
		this.rePwDate = rePwDate;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	
	
	
	
}